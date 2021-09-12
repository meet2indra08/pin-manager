package com.indra.pin.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("Server Error", errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PinLimitExceedException.class)
	public final ResponseEntity<Object> handlePinLimitAlreadyExistsException(PinLimitExceedException ex,
			WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("Pin Limit Exceeded", errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidPinAttemptsException.class)
	public final ResponseEntity<Object> handlePinLimitAlreadyExistsException(InvalidPinAttemptsException ex,
																			 WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("Invalid PIN Attempts", errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(DataNotFoundException.class)
	public final ResponseEntity<Object> handleMSISDNNotFoundException(DataNotFoundException ex, WebRequest request) {
       log.info("Data Not Found exception");
		List<String> errors = new ArrayList<>();
		errors.add(ex.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("MSISDN Not Found", errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errors.add(error.getDefaultMessage());
		}

		ErrorResponse errorResponse = new ErrorResponse("Validation Failed", errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}



}
