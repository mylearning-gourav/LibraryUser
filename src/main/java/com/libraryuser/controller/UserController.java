package com.libraryuser.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryuser.bean.ResultBean;
import com.libraryuser.bean.constants.ApplicationConstants;
import com.libraryuser.exception.BadRequestException;
import com.libraryuser.exception.RequestValidationException;
import com.libraryuser.model.User;
import com.libraryuser.service.UserService;
import com.libraryuser.validator.UpdatePasswordValidator;
import com.libraryuser.validator.UpdateRequestValidator;

/**
 * @author Gourav
 * Controller class for all user services
 */
@RestController
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UpdateRequestValidator updateUserValidator;
	
	@Autowired
	UpdatePasswordValidator updatePasswordValidator;
	
	/**
	 * Get Users
	 * @param user
	 * @return ResultBean
	 * @throws Exception
	 */
	@RequestMapping(value=ApplicationConstants.GET_USER, method=RequestMethod.GET)
	public ResultBean getUsers(@RequestParam(required=false) Integer id,
								@RequestParam(required=false) String name,
								@RequestParam(required=false) String email,
								@RequestParam(required=false) String active, 
								@RequestParam(required=false) Integer role) throws Exception {
		
		logger.info("Get Users Controller");

		ResultBean resultBean = ResultBean.getInstance();
		HashMap<String, List> userResultMap = new HashMap<String, List>();
		
		/*Set user object with param values*/
		User user = new User();
		if(id != null && id != 0)
			user.setUserId(id);
		if(name != null && !name.isEmpty())
			user.setName(name);
		if(email != null && !email.isEmpty())
			user.setEmail(email);
		if(active != null && !active.isEmpty()) {
			if(active.equalsIgnoreCase(ApplicationConstants.CONSTANT_TRUE))
				user.setActive(true);
			else if(active.equalsIgnoreCase(ApplicationConstants.CONSTANT_FALSE))
				user.setActive(false);
			else
				user.setActive(null);
		}
		if(role != null && role != 0)
			user.setRoleId(role);

		List<User> users = userService.getUsers(user);
		userResultMap.put("Users", users);
		resultBean.setResult(userResultMap);
		return resultBean;
	}
	
	/**
	 * Add Users
	 * @param user
	 * @return ResultBean
	 * @throws Exception
	 */
	@RequestMapping(value=ApplicationConstants.ADD_USER, method=RequestMethod.POST)
	public ResultBean addUser(@Valid @ModelAttribute("user") User user, BindingResult result) throws Exception {
		logger.info("Add User Controller");
		if(result.hasErrors()) {
			logger.info("Add User Request Param Error: " + result.toString());
			throw new RequestValidationException("Request Params Not Valid");
		}
		else {
			logger.info("Add User No Error");
			ResultBean resultBean = ResultBean.getInstance();
			if(user.isActive() == null) {
				user.setActive(true);
			}
			if(user.getRoleId() == 0) {
				user.setRoleId(1);
			}
			userService.addUsers(user);
			return resultBean;
		}
	}
	
	/**
	 * Update Users
	 * @param user
	 * @return ResultBean
	 * @throws Exception
	 */
	@RequestMapping(value=ApplicationConstants.UPDATE_USER, method=RequestMethod.PUT)
	public ResultBean updateUser(@ModelAttribute("user") User user, 
			BindingResult result ) throws Exception {
		logger.info("Update User Controller");
		updateUserValidator.validate(user, result);
		
		if(result.hasErrors()) {
			logger.error("Update user requests not valid");
			throw new RequestValidationException("Update user requests not valid");
		}
		else {
			logger.info("Valid Request");
			userService.updateUser(user);
		}
		
		ResultBean resultBean = ResultBean.getInstance();
		return resultBean;
	}
	
	/**
	 * Update Password
	 * @param user
	 * @return ResultBean
	 * @throws Exception
	 */
	@RequestMapping(value=ApplicationConstants.UPDATE_PASSWORD, method=RequestMethod.PUT)
	public ResultBean updatePassword(@ModelAttribute("user") User user, 
			BindingResult result) throws Exception {
		logger.info("Update Password Controller");
		updatePasswordValidator.validate(user, result);
		
		if(result.hasErrors()) {
			logger.error("Update password request not valid");
			throw new RequestValidationException("Update password request not valid");
		}
		else {
			logger.info("Valid Update Password Request");
			userService.updatePassword(user);
		}
		
		ResultBean resultBean = ResultBean.getInstance();
		return resultBean;
	}
	
	
	/***************************************************************************************************/
	@RequestMapping(value="/nana/nani", method=RequestMethod.PUT)
	public ResultBean updateUser1(@ModelAttribute("user") User user) throws Exception {
			throw new RequestValidationException("Update user requests not valid");
	}
	/***************************************************************************************************/
	
	
	
	/**
	 * Health Check Service
	 * @Param 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value=ApplicationConstants.HEALTH_CHECK, method=RequestMethod.GET)
	public void userServiceHealthCheck() throws Exception {
		logger.info("Health Check Controller");
//		throw new Exception();
	}
	
	
	/**
	 * Get Users
	 * @param user
	 * @return ResultBean
	 * @throws Exception
	 */
	/*@RequestMapping(value="/user/testuser", method=RequestMethod.GET)
	public String testUser throws Exception {

		throw new Exception();
	}*/
	
	/**
	 * All Other Request
	 * @param
	 * @return ResultBean
	 * @throws Exception
	 */
	@RequestMapping(value="/**", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
	public void allOtherAccessTypeOtherRequest() throws Exception {
//		logger.debug("Bad Get Request Controller");
		logger.info("Wrong Controller Method Called");
		throw new BadRequestException();
	}

	/*@ExceptionHandler({Exception.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFountGlobal1(){
        System.out.println("----------CaughtApplicationException-----------");
    }*/
}
