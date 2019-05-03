/**
 * 
 */
package com.libraryuser.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryuser.dao.UserDao;
import com.libraryuser.model.User;

/**
 * @author User
 * User Service Impl Class
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;

	/**
	 * Get Users Service
	 * @param user
	 * @return List
	 * @throws Exception
	 */
	public List getUsers(User user) throws Exception {
		logger.info("Get Users Service");
		return userDao.getUsers(user);
	}

	/**
	 * Add Users Service
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public void addUsers(User user) throws Exception {
		logger.info("Add User Service");
	}

}
