package com.superops.bms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TheatreMovieResponse {

	@JsonInclude(value = Include.NON_NULL)
	private Long movieId;

	@JsonInclude(value = Include.NON_NULL)
	private String movieName;

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
}
