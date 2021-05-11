package com.superops.bms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class MovieResponse extends CommonResponse {

	@JsonInclude(value = Include.NON_NULL)
	private Long movieId;

	@JsonInclude(value = Include.NON_NULL)
	private String movieName;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<MovieTheatreResponse> theatresList = new ArrayList<>();

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public List<MovieTheatreResponse> getTheatresList() {
		return theatresList;
	}
}
