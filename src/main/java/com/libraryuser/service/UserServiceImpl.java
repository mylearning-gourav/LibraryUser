/**
 * 
 */
package com.libraryuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryuser.dao.UserDao;
import com.libraryuser.model.User;

/**
 * @author User
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	public List getUsers(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUsers(user);
	}

}
