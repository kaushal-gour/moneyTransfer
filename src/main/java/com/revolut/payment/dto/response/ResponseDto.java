package com.revolut.payment.dto.response;

public class ResponseDto<T> {
	
	T body;
	ResponseHeader header;
	
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public ResponseHeader getHeader() {
		return header;
	}
	public void setHeader(ResponseHeader header) {
		this.header = header;
	}

}
