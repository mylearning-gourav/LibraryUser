package com.libraryuser.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libraryuser.bean.ResultBean;
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
		userResultMap.put("Result", userService.getUsers(user));
		resultBean.setResult(userResultMap);
		return resultBean;
	}

}
