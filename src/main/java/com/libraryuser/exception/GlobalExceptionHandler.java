package com.libraryuser.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.libraryuser.bean.ApiErrorResponse;
import com.libraryuser.bean.constants.ApplicationConstants;
import com.libraryuser.dao.UserDaoImpl;

@EnableWebMvc
@ControllerAdvice(basePackages = {"com.libraryuser.controller"} )
public class GlobalExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * Exception Handler function
	 * @param Exception
	 * @return ResponseEntity
	 * @throws 
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> exceptionHandler(Exception ex) {
		logger.info("Exception: " + ex.getMessage());
		ApiErrorResponse error = ApiErrorResponse.getInstance();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ApiErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	/*@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ResultBean handleException(Exception ex) {
//		logger.error("Exception : " + ex.getMessage());
		ResultBean resultBean = new ResultBean();
		resultBean.setStatusCode(3000);
		resultBean.setStatusMessage("Unknown Exception");
		return resultBean;
	}*/
	
	/**
	 * BadRequestException Handler function
	 * @param 
	 * @return ResponseEntity
	 * @throws 
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiErrorResponse> badRequestExceptionHandler(Exception ex) {
		logger.info("Bad Request Exception: " + ex.getMessage());
		ApiErrorResponse error = ApiErrorResponse.getInstance();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ApiErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
	
	/**
	 * RequestValidationException Handler function
	 * @param 
	 * @return ResponseEntity
	 * @throws 
	 */
	@ExceptionHandler(RequestValidationException.class)
	public ResponseEntity<ApiErrorResponse> requestValidationExceptionHandler(Exception ex) {
		logger.info("Bad Request Exception: " + ex.getMessage());
		ApiErrorResponse error = ApiErrorResponse.getInstance();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ApiErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
	
	/**
	 * DuplicateRecordException Handler function
	 * @param 
	 * @return ResponseEntity
	 * @throws 
	 */
	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<ApiErrorResponse> duplicateRecordExceptionHandler() {
		logger.info("Duplicate Record Exception ");
		ApiErrorResponse error = ApiErrorResponse.getInstance();
		error.setStatus(HttpStatus.CONFLICT);
		error.setErrorCode(ApplicationConstants.DUPLICATE_RECORD_ERROR_CODE);
        error.setMessage(ApplicationConstants.DUPLICATE_USER_ERROR_MESSAGE);
        return new ResponseEntity<ApiErrorResponse>(error, HttpStatus.OK);
	}
	
	/**
	 * UserNotFoundException Handler function
	 * @param 
	 * @return ResponseEntity
	 * @throws 
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> userNotFoundExceptionHandler(Exception ex) {
		logger.info("User Not Found Exception ");
		ApiErrorResponse error = ApiErrorResponse.getInstance();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setErrorCode(ApplicationConstants.USER_NOT_FOUND_ERROR_CODE);
        error.setMessage(ApplicationConstants.USER_NOT_FOUND_ERROR_MESSAGE);
        return new ResponseEntity<ApiErrorResponse>(error, HttpStatus.OK);
	}
	
	/*@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "Bad Request URL")
	public @ResponseBody ResultBean handleBadRequestException() {
//		logger.error("BadRequestException : Wrong URL");
		ResultBean resultBean = new ResultBean();
		resultBean.setStatusCode(3001);
		resultBean.setStatusMessage("Bad Request");
		return resultBean;
	}*/
	
	/**
	 * Exception Handler function
	 * @param NoHandlerFoundException
	 * @return ResponseEntity
	 * @throws 
	 */
	/*@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiErrorResponse> noHandlerFoundExceptionHandler(Exception ex) {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		ApiErrorResponse error = new ApiErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ApiErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
	
	

}
