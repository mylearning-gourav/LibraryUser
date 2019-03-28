package com.userservice.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.libraryuser.controller.UserController;
import com.libraryuser.model.User;
import com.libraryuser.service.UserService;

/*
 * Test Class for User Controller Test
 */
@RunWith(MockitoJUnitRunner.class)
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
//		ReflectionTestUtils.setField( mockMvc, "userService", userService );
	}
	
	/*@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        // this must be called for the @Mock annotations above to be processed.
        MockitoAnnotations.initMocks(this);
    }*/
	
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
