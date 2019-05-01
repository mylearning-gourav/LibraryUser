package com.libraryuser.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

/**
 * @author Gourav
 * Controller class for all user services
 */
@RestController
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
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
								@RequestParam(required=false) Boolean active, 
								@RequestParam(required=false) Integer role) throws Exception {
		
		logger.info("Get Users Controller");

		ResultBean resultBean = new ResultBean();
		HashMap<String, List> userResultMap = new HashMap<String, List>();
		
		/*Set user object with param values*/
		User user = new User();
		if(id != null && id != 0)
			user.setUserId(id);
		if(name != null && !name.isEmpty())
			user.setName(name);
		if(email != null && !email.isEmpty())
			user.setEmail(email);
		if(active != null && (active == true || active == false))
			user.setActive(active);
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
			/*System.out.println("Res 1 : " + result.getAllErrors().stream());
			System.out.println("Res 1 : " + result.getAllErrors().size());
			System.out.println("Res 1 : " + result.getAllErrors().get(0).getDefaultMessage());
			System.out.println("Res 2 : " + result.getAllErrors().get(1).getObjectName());
			System.out.println("Res 3 : " + result.getAllErrors().get(2).getObjectName());
			System.out.println("Res 4 : " + result.getAllErrors().get(3).getObjectName());*/
			logger.info("Add User Request Param Error: " + result.toString());
			throw new RequestValidationException("Request Params Not Valid");
//			throw new MethodArgumentNotValidException(null, result);
		}
		else {
			logger.info("Add User No Error");
			ResultBean resultBean = new ResultBean();
			return resultBean;
		}
	}
	
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
