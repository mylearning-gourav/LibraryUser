package com.libraryuser.exception;

import org.springframework.stereotype.Component;

@Component
public class UserNotFoundException extends CustomException {
	
	private static final long serialVersionUID = 100L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException() {
		super();
	}
}
