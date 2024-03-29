package com.revolut.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2546468333647909876L;

	public AccountNotFoundException(String message) {
		super(message);
	}

}
