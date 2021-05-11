package com.superops.bms.util;

import com.superops.bms.entity.TheatreScreen1Entity;
import com.superops.bms.model.CommonResponse;
import com.superops.bms.model.ErrorResponse;

public interface UtilityInterface {

	static void setShowForDB(TheatreScreen1Entity theatreEntity, String input, String value) {
		if (input.equalsIgnoreCase("SHOW-1")) {
			theatreEntity.setShow1(value);
		} else if (input.equalsIgnoreCase("SHOW-2")) {
			theatreEntity.setShow2(value);
		} else if (input.equalsIgnoreCase("SHOW-3")) {
			theatreEntity.setShow3(value);
		} else {
			theatreEntity.setShow4(value);
		}
	}

	static String getShowForQuery(String input) {
		if (input.equalsIgnoreCase("SHOW-1")) {
			return "show1";
		} else if (input.equalsIgnoreCase("SHOW-2")) {
			return "show2";
		} else if (input.equalsIgnoreCase("SHOW-3")) {
			return "show3";
		} else {
			return "show4";
		}
	}
	
	static void prepareAndAddError(CommonResponse response, String message, String uri) {
		ErrorResponse error = new ErrorResponse(message);
		error.setRequestURI(uri);
		response.getErrList().add(error);
	}
}
