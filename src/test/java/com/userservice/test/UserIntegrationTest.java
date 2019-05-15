package com.userservice.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
	public void integrationTestBGetAllUsersSuccess() throws Exception {
		
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
	public void integrationTestCGetUserByIdSuccess() throws Exception {
		
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
	public void integrationTestDGetUserByNameSuccess() throws Exception {
		
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
	public void integrationTestEGetUserByEmailSuccess() throws Exception {
		
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
	public void integrationTestFGetUserByRoleSuccess() throws Exception {
		
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
	public void integrationTestGGetUserByActiveSuccess() throws Exception {
		
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
	public void integrationTestHAddUserSuccess() throws Exception {
		
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
	public void integrationTestIAddDuplicateUserError() throws Exception {
		
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
	 * Test Case Exception for add duplicate user
	 */
	@Test
	public void integrationTestJUpdateUserValidationError() throws Exception {

		mockMvc.perform(put(ApplicationConstants.UPDATE_USER)
				.param("name", "Mani Babu")
				.param("email", "mani_babu@gmail.com")
				.param("password", "pada_padiba")
				.param("active", "1")
				.param("roleId", "1"))
			.andDo(print())
			.andExpect(status().isOk());
			/*.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.errorCode", is(3001)))
			.andExpect(jsonPath("$.status", is("CONFLICT")));*/
	}

}
