package com.superops.bms.util;

import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.superops.bms.exception.AuthorizationException;
import com.superops.bms.exception.ConcurrentBookingException;
import com.superops.bms.exception.PaymentFailureException;
import com.superops.bms.model.CommonResponse;
import com.superops.bms.model.ErrorResponse;

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody CommonResponse handleInvalidRequestException(MethodArgumentNotValidException exception,
			HttpServletRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		exception.getBindingResult().getAllErrors().stream().forEach(err -> {
			ErrorResponse error = new ErrorResponse(err.getDefaultMessage(), request.getRequestURI(),
					((FieldError) err).getField());
			commonResponse.getErrList().add(error);
		});
		return commonResponse;
	}

	@ExceptionHandler(ConcurrentBookingException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody CommonResponse genereicExceptionHandler(ConcurrentBookingException exception,
			HttpServletRequest request, HttpServletResponse response) {
		List<Throwable> throwableList = ExceptionUtils.getThrowableList(exception);
		Optional<Throwable> concurrentExceptionOptional = throwableList.stream()
				.filter(exp -> exp instanceof PersistenceException || exp instanceof OptimisticLockException
						|| exp instanceof CannotAcquireLockException)
				.findFirst();

		Optional<Throwable> paymentFailureOptional = throwableList.stream()
				.filter(exp -> exp instanceof PaymentFailureException).findFirst();
		CommonResponse commonResponse = new CommonResponse();
		if (concurrentExceptionOptional.isPresent()) {
			ErrorResponse error = new ErrorResponse(
					"The Seats you are looking for are currently in Blocked or Booked. Pleased refresh and try again");
			error.setRequestURI(request.getRequestURI());
			commonResponse.getErrList().add(error);
		} else if (paymentFailureOptional.isPresent()) {
			ErrorResponse error = new ErrorResponse("Payment Failure, Please refresh and try booking the seats again");
			error.setRequestURI(request.getRequestURI());
			commonResponse.getErrList().add(error);
		}
		return commonResponse;
	}
	
	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody CommonResponse genericAuthorizationException(AuthorizationException exception,
			HttpServletRequest request, HttpServletResponse response) {
		CommonResponse commonResponse = new CommonResponse();
		ErrorResponse error = new ErrorResponse(exception.getMessage());
		error.setRequestURI(request.getRequestURI());
		commonResponse.getErrList().add(error);
		return commonResponse;
	}

}
