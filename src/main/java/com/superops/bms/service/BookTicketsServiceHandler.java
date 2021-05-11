package com.superops.bms.service;

import static com.superops.bms.util.Constants.CONST_BOOKED;
import static com.superops.bms.util.Constants.CONST_PAYMENT_PASSWORD;
import static com.superops.bms.util.Constants.CONST_PAYMENT_TIMEOUT_PROP;
import static com.superops.bms.util.Constants.CONST_PAYMENT_USERID;
import static com.superops.bms.util.Constants.CONST_SUCCESS;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.superops.bms.converter.BMSObjectsConverter;
import com.superops.bms.entity.MovieEntity;
import com.superops.bms.entity.TheatreEntity;
import com.superops.bms.entity.TheatreScreen1Entity;
import com.superops.bms.exception.ConcurrentBookingException;
import com.superops.bms.exception.PaymentFailureException;
import com.superops.bms.model.CommonResponse;
import com.superops.bms.model.MovieListResponse;
import com.superops.bms.model.MovieResponse;
import com.superops.bms.model.TheatreListResponse;
import com.superops.bms.model.TheatreMovieResponse;
import com.superops.bms.model.TheatreResponse;
import com.superops.bms.model.TicketsBookingRequest;
import com.superops.bms.model.TicketsBookingResponse;
import com.superops.bms.repository.MovieRepository;
import com.superops.bms.repository.PaymentCallable;
import com.superops.bms.repository.TheatreRepository;
import com.superops.bms.repository.TheatreScreen1Repository;
import com.superops.bms.repository.TicketsBookingDaoHandler;
import com.superops.bms.util.UtilityInterface;

@Service
public class BookTicketsServiceHandler {

	private TicketsBookingDaoHandler tickBookingDaoHandler;

	private TheatreRepository theatreRepository;

	private MovieRepository movieRepository;

	private TheatreScreen1Repository theatreScreen1Repository;
	
	private BMSObjectsConverter bmsObjectsConverter;

	private Environment environment;

	public static final ExecutorService executorService = Executors.newFixedThreadPool(10);

	public BookTicketsServiceHandler(TicketsBookingDaoHandler tickBookingDaoHandler,
			TheatreRepository theatreRepository, MovieRepository movieRepository,
			TheatreScreen1Repository theatreScreen1Repository, BMSObjectsConverter bmsObjectsConverter,
			Environment environment) {
		this.tickBookingDaoHandler = tickBookingDaoHandler;
		this.theatreRepository = theatreRepository;
		this.movieRepository = movieRepository;
		this.theatreScreen1Repository = theatreScreen1Repository;
		this.bmsObjectsConverter = bmsObjectsConverter;
		this.environment = environment;
	}

	@Transactional(rollbackOn = ConcurrentBookingException.class)
	public TicketsBookingResponse bookTickets(TicketsBookingRequest request) throws ConcurrentBookingException {
		TicketsBookingResponse response = new TicketsBookingResponse();

		Optional<TheatreEntity> theatreOptional = checkIfValidTheatre(request, response);

		checkIfValidMovie(request, response, theatreOptional.get());

		List<TheatreScreen1Entity> movieBookingsList = bookTickets(request, response, theatreOptional);

		if (movieBookingsList.isEmpty() || null == movieBookingsList) {
			UtilityInterface.prepareAndAddError(response, "Selected Seats are not available for Booking. Please refresh and Try again", "/bookTickets");
		}

		boolean isFailure;
		try {
			isFailure = askPayment(movieBookingsList, request.getShowNumber(), response);
		} catch (PaymentFailureException exception) {
			throw new ConcurrentBookingException(exception);
		}

		if (!isFailure) {
			response.setMovieName(request.getMovieName());
			response.setMessage("Seats Booked SuccessFully :)");
			response.setScreenName(request.getScreenName());
			response.setSeatNumbers(request.getSeatNumbers());
			response.setShowNumber(request.getShowNumber());
			response.setTheatreName(request.getTheatreName());
		}
		return response;
	}

	public boolean askPayment(List<TheatreScreen1Entity> movieBookingsList, String requestShowName,
			TicketsBookingResponse response) throws PaymentFailureException {

		if (response.hasErrors())
			return true;

		boolean isPaymentFailure = false;

		try {
			Future<String> futureResponse = executorService.submit(new PaymentCallable(120,
					environment.getProperty(CONST_PAYMENT_USERID), environment.getProperty(CONST_PAYMENT_PASSWORD),
					environment.getProperty(CONST_PAYMENT_TIMEOUT_PROP)));
			String status = futureResponse.get(2, TimeUnit.SECONDS);
			
			if (!CONST_SUCCESS.equals(status)) {
				isPaymentFailure = true;
				throw new PaymentFailureException("Payment Failed Randomly Due with Failure Message");
			}
			
			movieBookingsList.stream().forEach(seat -> UtilityInterface.setShowForDB(seat, requestShowName, CONST_BOOKED));
			theatreScreen1Repository.saveAll(movieBookingsList);
			return isPaymentFailure;
		} catch (Exception exception) {
			// unblocking the tickets for booking again
			movieBookingsList.stream().forEach(seat -> UtilityInterface.setShowForDB(seat, requestShowName, null));
			theatreScreen1Repository.saveAll(movieBookingsList);
			throw new PaymentFailureException(exception);
		} 
	}

	private List<TheatreScreen1Entity> bookTickets(TicketsBookingRequest request, TicketsBookingResponse response,
			Optional<TheatreEntity> theatreOptional) throws ConcurrentBookingException {

		if (response.hasErrors())
			return null;

		List<TheatreScreen1Entity> movieBookingsList = tickBookingDaoHandler.bookTickets(request.getShowNumber(),
				request.getSeatNumbers(), theatreOptional.get().getTheatreId());

		return movieBookingsList;
	}

	private Optional<MovieEntity> checkIfValidMovie(TicketsBookingRequest request, TicketsBookingResponse response,
			TheatreEntity theatreEntity) {
		if (response.hasErrors())
			return Optional.empty();

		Optional<MovieEntity> movieOptional = findMovieByMovieName(request.getMovieName(), response);

		if (response.hasErrors())
			return Optional.empty();

		List<TheatreEntity> theatresList = movieOptional.get().getTheatresList();
		Optional<TheatreEntity> theatreOptional = theatresList.stream()
				.filter(mvThtr -> mvThtr.getTheatreId().equals(theatreEntity.getTheatreId())).findFirst();

		if (theatreOptional.isEmpty()) {
			UtilityInterface.prepareAndAddError(response, "Selected Theatre and Movie combination does not exist", "/bookTickets");
		}

		return movieOptional;
	}

	private Optional<MovieEntity> findMovieByMovieName(String movieName, CommonResponse response) {
		Optional<MovieEntity> movieOptional = movieRepository.findByMovieName(movieName);
		if (movieOptional.isEmpty()) {
			UtilityInterface.prepareAndAddError(response, "Movie Name provided in the request is currently not available", "/bookTickets");
		}
		return movieOptional;
	}

	private Optional<TheatreEntity> checkIfValidTheatre(TicketsBookingRequest request,
			TicketsBookingResponse response) {
		Optional<TheatreEntity> theatreOptional = theatreRepository
				.findByTheatreNameAndScreenName(request.getTheatreName(), request.getScreenName());

		if (theatreOptional.isEmpty()) {
			UtilityInterface.prepareAndAddError(response, "Incorrect Theatre Name and Screen Number combination is provided", "/bookTickets");
		}

		return theatreOptional;
	}

	public MovieListResponse getMoviesList() {
		List<MovieEntity> movieEntityList = movieRepository.findAll();
		MovieListResponse response = new MovieListResponse();
		if (null == movieEntityList || movieEntityList.isEmpty()) {
			UtilityInterface.prepareAndAddError(response, "No Movies exist.", "/moviesLst");
		}
		movieEntityList.stream().forEach(mveEnty -> {
			MovieResponse mveResponse = bmsObjectsConverter.prepareMovieResponse(mveEnty);
			response.getMoviesList().add(mveResponse);
		});
		return response;
	}

	public MovieResponse getSpecificMovie(String movieName) {
		MovieResponse response = new MovieResponse();
		Optional<MovieEntity> movieOptional = findMovieByMovieName(movieName, response);
		if (!response.hasErrors()) {
			response = bmsObjectsConverter.prepareMovieResponse(movieOptional.get());
		}
		return response;
	}

	public TheatreListResponse getTheatresByLocation(String location) {
		TheatreListResponse response = new TheatreListResponse();
		Optional<List<TheatreEntity>> theatreOptional = theatreRepository.findByLocationName(location);
		if (theatreOptional.isEmpty()) {
			UtilityInterface.prepareAndAddError(response, "NO Theatres in the Location", "/theatres/{location}");
		}

		if (!response.hasErrors()) {
			theatreOptional.get().stream().forEach(theatre -> {
				TheatreResponse theatreResponse = bmsObjectsConverter.prepareTheatreResponse(theatre);
				MovieEntity movieEntity = theatre.getMovieEntity();
				TheatreMovieResponse movieResponse = bmsObjectsConverter.prepareTheatreMovieResponse(movieEntity);
				theatreResponse.setMovieDetails(movieResponse);
			});
		}

		return response;
	}
}
