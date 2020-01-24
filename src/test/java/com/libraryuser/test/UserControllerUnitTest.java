package com.libraryuser.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.libraryuser.bean.constants.ApplicationConstants;
import com.libraryuser.controller.UserController;
import com.libraryuser.exception.DuplicateRecordException;
import com.libraryuser.exception.GlobalExceptionHandler;
import com.libraryuser.exception.RequestValidationException;
import com.libraryuser.model.User;
import com.libraryuser.service.UserService;
import com.libraryuser.validator.UpdateRequestValidator;

/*
 * Test Class for User Controller Test
 */
//@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@Mock
	private UpdateRequestValidator validator;
	
	@InjectMocks
	private UserController userController;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
					.standaloneSetup(userController)
					.setControllerAdvice(new GlobalExceptionHandler())
					.build();

	}
	
	/******************************************Add User Test Cases*****************************************/
	/*
	 * Test Case Success for add user
	 */
	@Test
	public void testAddUsersSuccess() throws Exception {
		
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
	 * Test Case Success for add user without Roll
	 */
	@Test
	public void testAddUsersSuccessNoRole() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")));
	}
	
	/*
	 * Test Case Success for add user without Active
	 */
	@Test
	public void testAddUsersSuccessNoActive() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")));
	}
	
	/*
	 * Test Case Success for add user without Roll and Active
	 */
	@Test
	public void testAddUsersSuccessNoRollActive() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")));
	}
	
	/*
	 * Test Case Failure for add user without name param
	 */
	@Test
	public void testAddUsersFailNoName() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test Case Failure for add user small name param
	 */
	@Test
	public void testAddUsersFailSmallName() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "M")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test Case Failure for add user without email param
	 */
	@Test
	public void testAddUsersFailNoEmail() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test Case Failure for add user small email param
	 */
	@Test
	public void testAddUsersFailSmallEmail() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani@g.c")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test Case Failure for add user without password param
	 */
	@Test
	public void testAddUsersFailNoPassword() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test Case Failure for add user small name param
	 */
	@Test
	public void testAddUsersFailSmallPassword() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test Case Fail for add user without Param
	 */
	@Test
	public void testAddUsersFailNoParam() throws Exception {
		
		mockMvc.perform(post(ApplicationConstants.ADD_USER))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test Case Exception for add duplicate user
	 */
	@Test
	public void testAddDuplicateUserError() throws Exception {
		
		doThrow(DuplicateRecordException.class)
	      .when(userService).addUsers(isA(User.class));
		
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
	
	/*************************
	 * 
	 * 
	 * /*************************
	 */
	/******************************************Update User Test Cases*****************************************/
	/*
	 * Test Case Exception for update user with id null
	 */
	/*@Test
	public void testUpdateUserIdValidationError() throws Exception {

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
	}*/
	
	/*
	 * Test Case Exception for update user with name null
	 */
	/*@Test
	public void testUpdateUserNameValidationError() throws Exception {
		
		doThrow(DuplicateRecordException.class)
	      .when(userService).addUsers(isA(User.class));
		
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
	}*/
	
	/*
	 * Test Case Exception for update user with email null
	 */
	/*@Test
	public void testUpdateUserEmailValidationError() throws Exception {
		
		doThrow(DuplicateRecordException.class)
	      .when(userService).addUsers(isA(User.class));
		
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
	}*/
	
	/*
	 * Test Case success for update user 
	 */
	@Test
	public void testUpdateUserValidationSuccess() throws Exception {
		
		mockMvc.perform(put(ApplicationConstants.UPDATE_USER)
				.param("userId", "1")
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
	
	/*************************
	 * 
	 * 
	 * /*************************
	 */
	
	/******************************************Get User Test Cases*****************************************/
	/*
	 * Test Case Success for all users
	 */
	@Test
	public void testGetAllUsersSuccess() throws Exception {
		
		List<User> users = Arrays.asList(
				new User(1, "Gourav Singh", "gouravsingh@gmail.com", true, 1),
				new User(2, "Sonali Singh", "sonalisingh@gmail.com", true, 1)
				);
		when(userService.getUsers(isA(User.class))).thenReturn(users);
		
		mockMvc.perform(get(ApplicationConstants.GET_USER))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(2)))
			.andExpect(jsonPath("$.result.Users[0].name", is("Gourav Singh")))
			.andExpect(jsonPath("$.result.Users[1].name", is("Sonali Singh")))
			.andExpect(jsonPath("$.result.Users[0].email", is("gouravsingh@gmail.com")))
			.andExpect(jsonPath("$.result.Users[1].email", is("sonalisingh@gmail.com")));
	}
	
	/*
	 * Test Case Success for searching user by id
	 */
	@Test
	public void testGetUserByIdSuccess() throws Exception {
		
		List<User> users = Arrays.asList(
				new User(2, "Sonali Singh", "sonalisingh@gmail.com", true, 1)
				);
		when(userService.getUsers(isA(User.class))).thenReturn(users);
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("id", "2"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(1)))
			.andExpect(jsonPath("$.result.Users[0].name", is("Sonali Singh")))
			.andExpect(jsonPath("$.result.Users[0].email", is("sonalisingh@gmail.com")));
	}
	
	/*
	 * Test Case Success for searching user by name
	 */
	@Test
	public void testGetUserByNameSuccess() throws Exception {
		
		List<User> users = Arrays.asList(
				new User(2, "Sonali Singh", "sonalisingh@gmail.com", true, 1)
				);
		when(userService.getUsers(isA(User.class))).thenReturn(users);
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("name", "Sonali Singh"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(1)))
			.andExpect(jsonPath("$.result.Users[0].name", is("Sonali Singh")))
			.andExpect(jsonPath("$.result.Users[0].email", is("sonalisingh@gmail.com")));
	}
	
	/*
	 * Test Case Success for searching user by email
	 */
	@Test
	public void testGetUserByEmailSuccess() throws Exception {
		
		List<User> users = Arrays.asList(
				new User(2, "Sonali Singh", "sonalisingh@gmail.com", true, 1)
				);
		when(userService.getUsers(isA(User.class))).thenReturn(users);
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("email", "sonalisingh@gmail.com"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(1)))
			.andExpect(jsonPath("$.result.Users[0].name", is("Sonali Singh")))
			.andExpect(jsonPath("$.result.Users[0].email", is("sonalisingh@gmail.com")));
	}
	
	/*
	 * Test Case Success for searching user by Role
	 */
	@Test
	public void testGetUserByRoleSuccess() throws Exception {
		
		List<User> users = Arrays.asList(
				new User(2, "Sonali Singh", "sonalisingh@gmail.com", true, 1)
				);
		when(userService.getUsers(isA(User.class))).thenReturn(users);
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("role", "1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(1)))
			.andExpect(jsonPath("$.result.Users[0].name", is("Sonali Singh")))
			.andExpect(jsonPath("$.result.Users[0].email", is("sonalisingh@gmail.com")));
	}

	/*
	 * Test Case Success for searching user by Active
	 */
	@Test
	public void testGetUserByActiveSuccess() throws Exception {
		
		List<User> users = Arrays.asList(
				new User(2, "Sonali Singh", "sonalisingh@gmail.com", true, 1)
				);
		when(userService.getUsers(isA(User.class))).thenReturn(users);
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("active", "true"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(1)));
	}
	
	/******************************************Wrong URL Test Cases*****************************************/
	/*
	 * Test case for wrong get URL
	 */
	@Test
	public void testNoRequestFoundGet() throws Exception {	
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user");
		this.mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test case for wrong post URL
	 */
	@Test
	public void testNoRequestFoundPost() throws Exception {	
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user");
		this.mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test case for bad get url requests
	 */
	@Test
	public void testBadGetRequest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user/badurl");
		this.mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test case for bad post url requests
	 */
	@Test
	public void testBadPostRequest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/badurl");
		this.mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	/*
	 * Test case for health check service
	 */
	@Test
	public void testTestGetRequest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ApplicationConstants.HEALTH_CHECK);
		this.mockMvc.perform(requestBuilder)
			.andExpect(status().isOk());
	}

}
