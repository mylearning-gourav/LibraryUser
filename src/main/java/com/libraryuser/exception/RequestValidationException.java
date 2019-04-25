package com.libraryuser.exception;

import org.springframework.stereotype.Component;

@Component
public class RequestValidationException extends CustomException {
	
	private static final long serialVersionUID = 100L;
	
	public RequestValidationException(String message) {
		super(message);
	}
	
	public RequestValidationException() {
		super();
	}

}
