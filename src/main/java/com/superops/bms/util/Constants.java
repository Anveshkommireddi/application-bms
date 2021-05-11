package com.superops.bms.util;

public class Constants {

	public static final String CONST_PAYMENT_PASSWORD = "payment-password";
	public static final String CONST_PAYMENT_USERID = "payment-user";
	public static final String CONST_PAYMENT_TIMEOUT_PROP = "payment-timeout";
	public static final String CONST_SUCCESS = "SUCCESS";
	public static final String CONST_FAILURE = "FAILURE";
	public static final String CONST_BOOKED = "BOOKED";
	public static final String CONST_BLOCKED = "BLOCKED";
	public static final String CONST_SELECT_TICKETS_PART_1 = "SELECT theatreScreen1Entity FROM TheatreScreen1Entity theatreScreen1Entity WHERE theatreScreen1Entity.seatNo IN (:seatNo) AND theatreScreen1Entity.";
	public static final String CONST_SELECT_TICKETS_PART_2 = " is null AND theatreScreen1Entity.theatreEntity.theatreId=:theatreId";
	public static final String CONST_SEAT_NO = "seatNo";
	public static final String CONST_THEATRE_ID = "theatreId";
}
