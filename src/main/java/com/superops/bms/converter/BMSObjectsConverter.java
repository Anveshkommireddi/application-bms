package com.superops.bms.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.superops.bms.entity.MovieEntity;
import com.superops.bms.entity.TheatreEntity;
import com.superops.bms.model.CommonResponse;
import com.superops.bms.model.ErrorResponse;
import com.superops.bms.model.MovieResponse;
import com.superops.bms.model.MovieTheatreResponse;
import com.superops.bms.model.TheatreMovieResponse;
import com.superops.bms.model.TheatreResponse;

@Component
public class BMSObjectsConverter {
	
	public void prepareMovieThreatreResponse(MovieResponse mveResponse, TheatreEntity theatre) {
		MovieTheatreResponse mtRspnse = new MovieTheatreResponse();
		mtRspnse.setLocationName(theatre.getLocationName());
		mtRspnse.setScreenName(theatre.getScreenName());
		mtRspnse.setTheatreId(theatre.getTheatreId());
		mtRspnse.setTheatreName(theatre.getTheatreName());
		mveResponse.getTheatresList().add(mtRspnse);
	}
	
	public MovieResponse prepareMovieResponse(MovieEntity mveEnty) {
		MovieResponse mveResponse = new MovieResponse();
		mveResponse.setMovieId(mveEnty.getMovieId());
		mveResponse.setMovieName(mveEnty.getMovieName());
		List<TheatreEntity> theatresList = mveEnty.getTheatresList();
		theatresList.stream().forEach(theatre -> {
			prepareMovieThreatreResponse(mveResponse, theatre);
		});
		return mveResponse;
	}
	
	public TheatreMovieResponse prepareTheatreMovieResponse(MovieEntity movieEntity) {
		TheatreMovieResponse movieResponse = new TheatreMovieResponse();
		movieResponse.setMovieId(movieEntity.getMovieId());
		movieResponse.setMovieName(movieEntity.getMovieName());
		return movieResponse;
	}
	
	public TheatreResponse prepareTheatreResponse(TheatreEntity theatre) {
		TheatreResponse theatreResponse = new TheatreResponse();
		theatreResponse.setLocationName(theatre.getLocationName());
		theatreResponse.setScreenName(theatre.getScreenName());
		theatreResponse.setTheatreId(theatre.getTheatreId());
		theatreResponse.setTheatreName(theatre.getTheatreName());
		return theatreResponse;
	}

}
