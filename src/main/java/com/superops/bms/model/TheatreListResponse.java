package com.superops.bms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TheatreListResponse extends CommonResponse {

	@JsonInclude(value = Include.NON_EMPTY)
	private List<TheatreResponse> theatresList = new ArrayList<>();

	public List<TheatreResponse> getTheatresList() {
		return theatresList;
	}
}
