package com.revolut.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LowBalanceException extends RuntimeException {

	private static final long serialVersionUID = -5435103745866809322L;
	
	public LowBalanceException(String message) {
		super(message);
	}

}
