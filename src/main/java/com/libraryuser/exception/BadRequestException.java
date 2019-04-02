package com.libraryuser.exception;

import org.springframework.stereotype.Component;

@Component
public class BadRequestException extends CustomException {

	private static final long serialVersionUID = 100L;
	
	public BadRequestException() {
		
	}
}
