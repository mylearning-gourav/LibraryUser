package com.userservice.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.libraryuser.controller.UserController;
import com.libraryuser.model.User;
import com.libraryuser.service.UserService;


import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.http.*;

public class UserControllerUnitTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
					.standaloneSetup(userController)
					.build();
	}
	
	@Test
	public void testGetUserSuccess() throws Exception {
		
		List<User> users = Arrays.asList(
				new User(1, "Gourav Singh", "gouravsingh@gmail.com", true, 1),
				new User(2, "Sonali Singh", "sonalisingh@gmail.com", true, 1)
				);
		User user = new User();
		when(userService.getUsers(user)).thenReturn(users);
		
		mockMvc.perform(post("/user/getusers"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(0)));
	}

}
