package com.libraryuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.libraryuser.bean.ResultBean;

@EnableWebMvc
@ControllerAdvice(basePackages = {"com.libraryuser.controller"} )
public class GlobalExceptionHandler {
	
	/**
	 * Exception Handler function
	 * @param Exception
	 * @return ResultBean
	 * @throws 
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ResultBean handleException(Exception ex) {
//		logger.error("Exception : " + ex.getMessage());
		ResultBean resultBean = new ResultBean();
		resultBean.setStatusCode(3000);
		resultBean.setStatusMessage("Unknown Exception");
		return resultBean;
	}
	
	/**
	 * BadRequestException Handler function
	 * @param 
	 * @return ResultBean
	 * @throws 
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "Bad Request URL")
	public @ResponseBody ResultBean handleBadRequestException() {
//		logger.error("BadRequestException : Wrong URL");
		ResultBean resultBean = new ResultBean();
		resultBean.setStatusCode(3001);
		resultBean.setStatusMessage("Bad Request");
		return resultBean;
	}

}
