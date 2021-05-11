package com.superops.bms.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TicketsBookingResponse extends CommonResponse{

	@JsonInclude(value = Include.NON_NULL)
	private String theatreName;

	@JsonInclude(value = Include.NON_NULL)
	private String screenName;

	@JsonInclude(value = Include.NON_NULL)
	private String showNumber;
	
	@JsonInclude(value = Include.NON_NULL)
	private String movieName;
	
	//add date field
	
	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> seatNumbers;

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

	public String getShowNumber() {
		return showNumber;
	}
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public void setShowNumber(String showNumber) {
		this.showNumber = showNumber;
	}

	public List<String> getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(List<String> seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

}
