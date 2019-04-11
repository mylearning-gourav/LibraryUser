package com.libraryuser.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryuser.bean.ResultBean;
import com.libraryuser.bean.constants.ApplicationConstants;
import com.libraryuser.exception.BadRequestException;
import com.libraryuser.model.User;
import com.libraryuser.service.UserService;

/**
 * @author Gourav
 * Controller class for all user services
 */
@RestController
public class UserController {
	
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

		ResultBean resultBean = new ResultBean();
		HashMap<String, List> userResultMap = new HashMap<String, List>();
		
		/*Set user object with param values*/
		User user = new User();
		if(id != null && id != 0)
			user.setUserId(id);
		if(name != null && !name.isEmpty())
			user.setName(name);
		if(email != null && !email.isEmpty())
			user.setName(email);
		if(active != null && (active == true || active == false))
			user.setActive(active);
		if(role != null && role != 0)
			user.setRoleId(role);
		System.out.println("Nameqq = " + user.getName());
		List<User> users = userService.getUsers(user);
		userResultMap.put("Users", users);
		resultBean.setResult(userResultMap);
		return resultBean;
	}
	
	/**
	 * Health Check Service
	 * @Param 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value=ApplicationConstants.HEALTH_CHECK, method=RequestMethod.GET)
	public void userServiceHealthCheck() throws Exception {
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
		throw new BadRequestException();
	}

	/*@ExceptionHandler({Exception.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFountGlobal1(){
        System.out.println("----------CaughtApplicationException-----------");
    }*/
}
