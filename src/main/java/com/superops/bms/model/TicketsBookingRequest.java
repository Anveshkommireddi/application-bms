package com.superops.bms.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TicketsBookingRequest {

	private static final String NOT_NULL_MSG = "Should Not be Null";

	@NotNull(message = NOT_NULL_MSG)
	private String theatreName;

	@NotNull(message = NOT_NULL_MSG)
	private String screenName;

	@NotNull(message = NOT_NULL_MSG)
	private String showNumber;

	@NotNull(message = NOT_NULL_MSG)
	private String movieName;

	@Size(min = 1, max = 6, message = "User can book a Minimum of 1 and a Maximum of 6 Tickets.")
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
