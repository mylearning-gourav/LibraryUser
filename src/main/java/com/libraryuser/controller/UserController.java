package com.libraryuser.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libraryuser.bean.ResultBean;
import com.libraryuser.exception.BadRequestException;
import com.libraryuser.model.User;
import com.libraryuser.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * Get Users
	 * @param user
	 * @return ResultBean
	 * @throws Exception
	 */
	@RequestMapping(value="/getusers", method=RequestMethod.POST)
	public ResultBean getUsers(User user) throws Exception {

		ResultBean resultBean = new ResultBean();
		HashMap<String, List> userResultMap = new HashMap<String, List>();
		List<User> users = userService.getUsers(user);
		userResultMap.put("Users", users);
		resultBean.setResult(userResultMap);
		return resultBean;
	}
	
	/**
	 * Test User Service
	 * @Param 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="/testuser", method=RequestMethod.GET)
	public String testUser() throws Exception {
		throw new Exception();
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
