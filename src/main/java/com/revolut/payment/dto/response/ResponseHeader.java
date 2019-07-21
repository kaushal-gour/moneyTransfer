package com.revolut.payment.dto.response;

public class ResponseHeader {
	
	private String status;
	private String operation;
	
	public ResponseHeader() {
	}
	public ResponseHeader(String operation) {
		this.operation = operation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
