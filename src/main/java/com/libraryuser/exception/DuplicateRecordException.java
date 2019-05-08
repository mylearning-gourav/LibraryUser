package com.libraryuser.exception;

import org.springframework.stereotype.Component;

@Component
public class DuplicateRecordException extends CustomException {
	
	private static final long serialVersionUID = 100L;
	
	public DuplicateRecordException(String message) {
		super(message);
	}
	
	public DuplicateRecordException() {
		super();
	}
}
