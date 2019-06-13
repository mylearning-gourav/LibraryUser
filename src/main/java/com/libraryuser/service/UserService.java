package com.libraryuser.service;

import java.util.List;

import com.libraryuser.model.User;

/**
 * @author User
 * User Service Interface
 */
public interface UserService {
	
	public List getUsers(User user) throws Exception;
	
	public void addUsers(User user) throws Exception;
	
	public boolean checkDupicateUser(String email) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public void updatePassword(User user) throws Exception;

	public void updateActiveStatus(List<User> userList) throws Exception;
	/*public void registerNewUser(User user) throws Exception;

	public void updateUser(User user) throws Exception;

	public void updateActiveStatus(int userId, boolean active) throws Exception;
	
	public void updateRole(int userId, int roleId) throws Exception;

	public boolean authenticateUser(String email, String password) throws Exception;
	
	public boolean checkDuplicateEmail(String email) throws Exception;*/

}
