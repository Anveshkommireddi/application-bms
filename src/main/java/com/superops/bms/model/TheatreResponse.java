package com.superops.bms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TheatreResponse extends CommonResponse {

	@JsonInclude(value = Include.NON_NULL)
	private Long theatreId;

	@JsonInclude(value = Include.NON_NULL)
	private String theatreName;

	@JsonInclude(value = Include.NON_NULL)
	private String screenName;

	@JsonInclude(value = Include.NON_NULL)
	private String locationName;

	@JsonInclude(value = Include.NON_NULL)
	private TheatreMovieResponse movieDetails;

	public Long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public TheatreMovieResponse getMovieDetails() {
		return movieDetails;
	}

	public void setMovieDetails(TheatreMovieResponse movieDetails) {
		this.movieDetails = movieDetails;
	}
}
