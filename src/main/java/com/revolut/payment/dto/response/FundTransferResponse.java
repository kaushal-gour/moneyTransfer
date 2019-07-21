package com.revolut.payment.dto.response;

import com.revolut.payment.model.Account;

public class FundTransferResponse {
	
	private Account to;
	private Account from;
	public Account getTo() {
		return to;
	}
	public void setTo(Account to) {
		this.to = to;
	}
	public Account getFrom() {
		return from;
	}
	public void setFrom(Account from) {
		this.from = from;
	}
	
	

}
