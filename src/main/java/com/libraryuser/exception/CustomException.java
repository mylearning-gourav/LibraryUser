package com.libraryuser.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends Exception {
	
	protected String errorMessage;
	
	public CustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public CustomException() {
        super();
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
