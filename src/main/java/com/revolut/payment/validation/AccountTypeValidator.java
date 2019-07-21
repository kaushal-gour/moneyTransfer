package com.revolut.payment.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class AccountTypeValidator {
	
	public void validate(String accountType, Errors errors) {
		if(!"SAVING".equalsIgnoreCase(accountType) || "CURRENT".equalsIgnoreCase(accountType)) {
			errors.reject("Invalid account type. Please provide account type as SAVING or CURRENT");
		}
	}

}
