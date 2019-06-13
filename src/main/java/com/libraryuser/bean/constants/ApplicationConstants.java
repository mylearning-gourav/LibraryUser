package com.libraryuser.bean.constants;

public interface ApplicationConstants {
	
	public static final String HEALTH_CHECK = "/userservice/healthcheck";
	public static final String GET_USER = "/userservice/get";
	public static final String ADD_USER = "/userservice/add";
	public static final String UPDATE_USER = "/userservice/update";
	public static final String UPDATE_PASSWORD = "/userservice/updatepassword";
	public static final String UPDATE_ACTIVE_STATUS = "/userservice/updatestatus";
	
	public static final String CONSTANT_TRUE = "true";
	public static final String CONSTANT_FALSE = "false";
	
	public static final Integer DUPLICATE_RECORD_ERROR_CODE = 3001;
	public static final String DUPLICATE_USER_ERROR_MESSAGE = "User Already Exists!";
	public static final Integer USER_NOT_FOUND_ERROR_CODE = 3002;
	public static final String USER_NOT_FOUND_ERROR_MESSAGE = "User Does not Exist!";
//	public static final String GET_ALL_USER = "/userservice/users";
	/*public static final String CREATE_EMP = "/rest/emp/create";
	public static final String DELETE_EMP = "/rest/emp/delete/{id}";*/

}
