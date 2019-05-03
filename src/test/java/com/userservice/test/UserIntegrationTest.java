package com.userservice.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.libraryuser.bean.constants.ApplicationConstants;
import com.libraryuser.config.AppConfiguration;
import com.libraryuser.controller.UserController;
import com.libraryuser.model.User;

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
	public void integrationTestFindController() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("userController"));
	}
	
	/*
	 * Test Case Success for all users
	 */
	@Test
	public void integrationTestGetAllUsersSuccess() throws Exception {
		
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
	public void integrationTestGetUserByIdSuccess() throws Exception {
		
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
	public void integrationTestGetUserByNameSuccess() throws Exception {
		
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
	public void integrationTestGetUserByEmailSuccess() throws Exception {
		
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
	public void integrationTestGetUserByRoleSuccess() throws Exception {
		
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
	public void integrationTestGetUserByActiveSuccess() throws Exception {
		
		mockMvc.perform(get(ApplicationConstants.GET_USER).param("active", "true"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.statusCode", is(2000)))
			.andExpect(jsonPath("$.statusMessage", is("Success")))
			.andExpect(jsonPath("$.result.Users", hasSize(4)));
	}

}
