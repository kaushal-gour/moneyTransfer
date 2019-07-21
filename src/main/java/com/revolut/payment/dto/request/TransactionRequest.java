package com.revolut.payment.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class TransactionRequest {
	
	@NotNull(message="amount can not be null")
	private double amount;
	
	@NotNull(message="currency can not be null")
	@NotEmpty(message="currency can not be empty")
	private String currency;
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	

}
