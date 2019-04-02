package com.libraryuser.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends Exception {
	
	protected int errorCode;
	protected String errorMessage;
	
	public CustomException() {
		this.errorCode = 1001;
		this.errorMessage = "Error Message";
	}
	
	public CustomException(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
