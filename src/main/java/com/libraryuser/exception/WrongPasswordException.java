package com.libraryuser.exception;

import org.springframework.stereotype.Component;

@Component
public class WrongPasswordException extends CustomException {
	
	private static final long serialVersionUID = 100L;
	
	public WrongPasswordException(String message) {
		super(message);
	}
	
	public WrongPasswordException() {
		super();
	}
}
