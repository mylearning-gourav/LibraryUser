package com.libraryuser.bean;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResultBean {
	
	private int statusCode;
	private String statusMessage;
	private Map<String, List> result;
	
	private ResultBean() {
		statusCode = 2000;
		statusMessage = "Success";
	}
	
	private static ResultBean resultBean;
    
    public static ResultBean getInstance() {
    	/*if(resultBean == null) {
    		resultBean = new ResultBean();
    	}
    	return resultBean;*/
    	return new ResultBean();
    }
	
	/*public ResultBean(int statusCode, String statusMessage, HashMap<String, List> result) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.result = result;
	}*/
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Map<String, List> getResult() {
		return result;
	}

	public void setResult(Map<String, List> result) {
		this.result = result;
	}
}
