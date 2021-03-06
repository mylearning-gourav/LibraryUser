/**
 * 
 */
package com.libraryuser.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryuser.dao.UserDao;
import com.libraryuser.exception.DuplicateRecordException;
import com.libraryuser.exception.UserNotFoundException;
import com.libraryuser.model.User;
import com.libraryuser.util.CommonUtil;

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
		if(this.checkDuplicateUser(user.getEmail())) {
			logger.info("Duplicate User");
			throw new DuplicateRecordException();
		}
		else {
			user.setPassword(CommonUtil.getEncryptPassword(user.getPassword()));
			userDao.addUsers(user);
			logger.info("User Added");
		}
	}

	/**
	 * Update Users Service
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		logger.info("Update User Service");
		User inUser = new User();
		inUser.setUserId(user.getUserId());
		if(userDao.getUsers(inUser).size() == 1) {
			userDao.updateUser(user);
		}
		else {
			logger.error("User Not Found");
			throw new UserNotFoundException();
		}
		
	}

	/**
	 * Check Duplicate User
	 * @param email
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean checkDuplicateUser(String email) throws Exception {
		logger.info("Check Duplicate User Service");
		return userDao.checkDupicateUser(email);
	}

	/**
	 * Update Users Password
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public void updatePassword(User user) throws Exception {
		logger.info("Update Password Service");
		User inUser = new User();
		inUser.setUserId(user.getUserId());
		if(userDao.getUsers(inUser).size() == 1) {
			user.setPassword(CommonUtil.getEncryptPassword(user.getPassword()));
			userDao.updatePassword(user);
		}
		else {
			logger.error("User Not Found");
			throw new UserNotFoundException();
		}
	}

	/**
	 * Update Users Active
	 * @param users[]
	 * @return 
	 * @throws Exception
	 */
	public void updateActiveStatus(List<User> userList) throws Exception {
		logger.info("Update Active Status Service");
		userDao.updateActiveStatus(userList);
	}

	/**
	 * Login User
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	public boolean loginUser(User user) throws Exception {
		logger.info("Login User Service");
		User inUser = new User();
		inUser.setEmail(user.getEmail());
		if(userDao.getUsers(inUser).size() == 1) {
			String password = userDao.loginUser(user);
			return this.validateLoginPassword(user.getPassword(), password);
		}
		else {
			logger.error("User Not Found");
			throw new UserNotFoundException();
		} 
	}
	
	/**
	 * Validate Login Password
	 * @param userPass
	 * @param dbPass
	 * @return boolean
	 * @throws Exception
	 */
	private boolean validateLoginPassword(String userPass, String dbPass) {
		logger.info("Validate Login Password");
		return CommonUtil.matchPassword(userPass, dbPass) ? true : false;
	}

}
