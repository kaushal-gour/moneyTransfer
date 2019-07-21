package com.revolut.payment.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class NewAccountRequest {

	@NotNull(message = "type can not be null")
	@NotEmpty(message = "type can not be empty")
	private String type;
	@NotNull(message = "currency can not be null")
	@NotEmpty(message = "currency can not be empty")
	private String currency;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
