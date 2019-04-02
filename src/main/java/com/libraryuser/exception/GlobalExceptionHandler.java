package com.libraryuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.libraryuser.bean.ResultBean;
import com.libraryuser.exception.BadRequestException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
//	final static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * BadRequestException Handler function
	 * @param 
	 * @return ResultBean
	 * @throws 
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "Bad Request URL")
	public @ResponseBody ResultBean handleBadRequestException() {
		ResultBean resultBean = new ResultBean();
		resultBean.setStatusCode(3001);
		resultBean.setStatusMessage("Bad Request");
		return resultBean;
	}
	
	/**
	 * Exception Handler function
	 * @param Exception
	 * @return ResultBean
	 * @throws 
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ResultBean handleException(Exception ex) {
		ResultBean resultBean = new ResultBean();
		resultBean.setStatusCode(3000);
		resultBean.setStatusMessage("Unknown Exception");
		return resultBean;
	}

}
