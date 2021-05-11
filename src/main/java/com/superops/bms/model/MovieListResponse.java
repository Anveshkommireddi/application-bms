package com.superops.bms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class MovieListResponse extends CommonResponse{

	@JsonInclude(value = Include.NON_EMPTY)
	private List<MovieResponse> moviesList = new ArrayList<>();

	public List<MovieResponse> getMoviesList() {
		return moviesList;
	}
}
