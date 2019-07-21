package com.revolut.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revolut.payment.dto.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> accountNotFoundExceptionHandler(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setError(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(LowBalanceException.class)
	public ResponseEntity<ErrorResponse> lowBalanceExceptionHandler(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setError(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccountCreationException.class)
	public ResponseEntity<ErrorResponse> accountCreationExceptionHandler(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setError(ex.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> allExceptionHandler(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setError("We are facing technical issue. Please try again later.");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
