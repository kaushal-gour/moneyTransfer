package com.revolut.payment.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CurrencyValidator {
	
	public void validate(String currency, Errors errors) {
		if(!"EUR".equalsIgnoreCase(currency)) {
			errors.reject("Invalid currency. Please provide currency value as EUR");
		}
	}

}
