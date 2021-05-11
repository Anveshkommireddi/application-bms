package com.superops.bms.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.superops.bms.exception.ConcurrentBookingException;
import com.superops.bms.model.MovieListResponse;
import com.superops.bms.model.MovieResponse;
import com.superops.bms.model.TheatreListResponse;
import com.superops.bms.model.TicketsBookingRequest;
import com.superops.bms.model.TicketsBookingResponse;
import com.superops.bms.service.BookTicketsServiceHandler;

@RestController
public class TicketBookingRestController {

	private BookTicketsServiceHandler bookTicketsServiceHandler;
	
	public TicketBookingRestController(BookTicketsServiceHandler bookTicketsServiceHandler) {
		this.bookTicketsServiceHandler = bookTicketsServiceHandler;
	}

	@PutMapping("/bookTickets")
	public ResponseEntity<TicketsBookingResponse> bookTickets(@Valid @RequestBody TicketsBookingRequest request)
			throws ConcurrentBookingException {
		TicketsBookingResponse response = bookTicketsServiceHandler.bookTickets(request);
		ResponseEntity<TicketsBookingResponse> responseEntity = new ResponseEntity<TicketsBookingResponse>(response,
				HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/moviesLst")
	public MovieListResponse getMoviesList() {
		return bookTicketsServiceHandler.getMoviesList();
	}

	@GetMapping("/moviesLst/{movieName}")
	public MovieResponse getMovieByName(@PathVariable(name = "movieName") String movieName) {
		return bookTicketsServiceHandler.getSpecificMovie(movieName);
	}

	@GetMapping("/theatres/{location}")
	public TheatreListResponse getTheatresByLocation(@PathVariable(name = "location") String location) {
		return bookTicketsServiceHandler.getTheatresByLocation(location);
	}
}
