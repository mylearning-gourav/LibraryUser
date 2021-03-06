package com.libraryuser.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryuser.bean.constants.ApplicationConstants;
import com.libraryuser.config.AppConfiguration;
import com.libraryuser.controller.UserController;
import com.libraryuser.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
@WebAppConfiguration
public class UserIntegrationTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	UserController userController;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
						.build();
	}
	
	@Test
	public void integrationTestAFindController() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("userController"));
	}
	
	/******************************************Get User Test Cases*****************************************/
	
	/*
	 * Test Case Success for all users
	 */
	@Test
	public void integrationTestBAGetAllUsersSuccess() throws Exception {
		
		this.mockMvc.perform(get("/userservice/get"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(5)));
		
	}
	
	/*
	 * Test Case Success for searching user by id
	 */
	@Test
	public void integrationTestBBGetUserByIdSuccess() throws Exception {
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("id", "2"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(1)));
	}
	
	/*
	 * Test Case Success for searching user by name
	 */
	@Test
	public void integrationTestBCGetUserByNameSuccess() throws Exception {
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("name", "Gourav"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(1)));
	}
	
	/*
	 * Test Case Success for searching user by email
	 */
	@Test
	public void integrationTestBDGetUserByEmailSuccess() throws Exception {
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("email", "gouravsingh@gmail.com"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(1)));
	}
	
	/*
	 * Test Case Success for searching user by Role
	 */
	@Test
	public void integrationTestBEGetUserByRoleSuccess() throws Exception {
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("role", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(3)));
	}
	
	/*
	 * Test Case Success for searching user by Role
	 */
	@Test
	public void integrationTestBFGetUserByActiveSuccess() throws Exception {
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("active", "true"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(4)));
	}
	
	/******************************************Add User Test Cases*****************************************/

	/*
	 * Test Case Success for adding new user
	 */
	@Test
	public void integrationTestCAAddUserSuccess() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")));
	}
	
	/*
	 * Test Case Exception for add duplicate user
	 */
	@Test
	public void integrationTestCBAddDuplicateUserError() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(3001)))
			.andExpect(jsonPath("$.status", is("CONFLICT")));
	}
	
	/******************************************Update User Test Cases*****************************************/
	/*
	 * Test Case Exception for update user with id null
	 */
	@Test
	public void integrationTestCCUpdateUserIdValidationError() throws Exception {

		mockMvc.perform(put(ApplicationConstants.UPDATE_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Exception for update user with name null
	 */
	@Test
	public void integrationTestCDUpdateUserNameValidationError() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_USER)
				.param("userId", "1")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Exception for update user with email null
	 */
	@Test
	public void integrationTestCEUpdateUserEmailValidationError() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_USER)
				.param("userId", "1")
				.param("name", "Mani Babu")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Exception for update user with role null
	 */
	@Test
	public void integrationTestCFUpdateUserRoleValidationError() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_USER)
				.param("userId", "1")
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Exception for update user not found
	 */
	@Test
	public void integrationTestCGUpdateUserNotFoundError() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_USER)
				.param("userId", "10")
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(3002)))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Success for update user
	 */
	@Test
	public void integrationTestCHUpdateUser() throws Exception {

		mockMvc.perform(put(ApplicationConstants.UPDATE_USER)
				.param("userId", "21")
				.param("name", "Mani Babu1")
				.param("email", "mani_babu1@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")));
	}

	/******************************************Update Password Test Cases*****************************************/
	/*
	 * Test Case Exception for update password with password null
	 */
	@Test
	public void integrationTestDAUpdatePasswordValidationError() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_PASSWORD)
				.param("userId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Exception for update password with user id null
	 */
	@Test
	public void integrationTestDBUpdatePasswordUserIdValidationError() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_PASSWORD)
				.param("password", "pada_padiba"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Exception for update password with user id negative or 0
	 */
	@Test
	public void integrationTestDCUpdatePasswordUserIdZeroValidationError() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_PASSWORD)
				.param("userId", "0")
				.param("password", "pada_padiba"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Exception for update password with wrong user id
	 */
	@Test
	public void integrationTestDDUpdatePasswordUserNotFoundError() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_PASSWORD)
				.param("userId", "12")
				.param("password", "pada_padiba"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(3002)))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Success for update password
	 */
	@Test
	public void integrationTestDEUpdatePasswordSuccess() throws Exception {

		mockMvc.perform(put(ApplicationConstants.UPDATE_PASSWORD)
				.param("userId", "21")
				.param("password", "pada_padiba1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")));
	}
	
	/******************************************Update Active Status Test Cases*****************************************/
	/*
	 * Test Case Exception for update active status with user[] null
	 */
	@Test
	public void integrationTestEAUpdateActiveValidationError() throws Exception {
		
		List<User> users = new ArrayList<User>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String requestString = objectMapper.writeValueAsString(users);
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_ACTIVE_STATUS)
				.contentType(MediaType.APPLICATION_JSON)
                .content(requestString))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}

	/*
	 * Test Case Exception for update active status with invalid user id
	 */
	@Test
	public void integrationTestEBUpdateActiveValidationError() throws Exception {
		User user1 = new User(0, null, null, false, 0);
		User user2 = new User(3, null, null, false, 0);
		
		List<User> users = new ArrayList<User>();
		
		users.add(user1);
		users.add(user2);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String requestString = objectMapper.writeValueAsString(users);
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_ACTIVE_STATUS)
				.contentType(MediaType.APPLICATION_JSON)
                .content(requestString))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case Exception for update active status with wrong user id
	 */
	@Test
	public void integrationTestECUpdateActiveWrongUserError() throws Exception {
		User user1 = new User(3, null, null, false, 0);
		User user2 = new User(91, null, null, false, 0);
		
		List<User> users = new ArrayList<User>();
		
		users.add(user1);
		users.add(user2);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String requestString = objectMapper.writeValueAsString(users);
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_ACTIVE_STATUS)
				.contentType(MediaType.APPLICATION_JSON)
                .content(requestString))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.errorCode", is(ApplicationConstants.USER_NOT_FOUND_ERROR_CODE)))
				.andExpect(jsonPath("$.status", is("BAD_REQUEST")))
				.andExpect(jsonPath("$.message", is(ApplicationConstants.USER_NOT_FOUND_ERROR_MESSAGE)));
	}
	
	/*
	 * Test Case success for update active status
	 */
	@Test
	public void integrationTestEDUpdateActiveValidationSuccess() throws Exception {
		User user1 = new User(1, null, null, false, 0);
		User user2 = new User(3, null, null, false, 0);
		
		List<User> users = new ArrayList<User>();
		
		users.add(user1);
		users.add(user2);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String requestString = objectMapper.writeValueAsString(users);
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_ACTIVE_STATUS)
				.contentType(MediaType.APPLICATION_JSON)
                .content(requestString))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.statusCode", is(2000)))
				.andExpect(jsonPath("$.statusMessage", is("Success")));
	}
	
	/******************************************Login User Test Cases*****************************************/
	
	/*
	 * Test Case failure for login user invalid input
	 */
	@Test
	public void integrationTestFALoginValidationFailure() throws Exception {
		mockMvc.perform(post(ApplicationConstants.USER_LOGIN)
				.param("email", "gouravsingh@gmail.com"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case failure for login user invalid input
	 */
	@Test
	public void integrationTestFBLoginValidationFailure() throws Exception {
		mockMvc.perform(post(ApplicationConstants.USER_LOGIN)
				.param("password", "gouravsingh"))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(HttpStatus.BAD_REQUEST.value())))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case failure for Invalid email
	 */
	@Test
	public void integrationTestFCLoginWrongEmailFailure() throws Exception {
		mockMvc.perform(post(ApplicationConstants.USER_LOGIN)
				.param("email", "aaaa@aaaa.com")
				.param("password", "aaaaaaaaa"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(3002)))
			.andExpect(jsonPath("$.status", is("BAD_REQUEST")));
	}
	
	/*
	 * Test Case failure for Invalid password
	 */
	@Test
	public void integrationTestFDLoginWrongPasswordFailure() throws Exception {
		mockMvc.perform(post(ApplicationConstants.USER_LOGIN)
				.param("email", "mani_babu1@gmail.com")
				.param("password", "aaaaaaaaa"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(3003)))
			.andExpect(jsonPath("$.status", is("UNAUTHORIZED")));
	}
	
	/*
	 * Test Case Success for Login
	 */
	@Test
	public void integrationTestFELoginSuccess() throws Exception {
		mockMvc.perform(post(ApplicationConstants.USER_LOGIN)
				.param("email", "mani_babu1@gmail.com")
				.param("password", "pada_padiba1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")));
	}
}
