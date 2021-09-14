package com.myapp.spring.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.User;
import com.myapp.spring.repository.UserRepository;

@SpringBootTest

@AutoConfigureMockMvc(addFilters = false)
public class UserIntegrationTest {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
private static File DATA_JSON= Paths.get("src","test","resources","users.json").toFile();
	
	
	@BeforeEach
	public void setUp() throws JsonParseException, JsonMappingException, IOException {
		
	User users[]=new ObjectMapper().readValue(DATA_JSON, User[].class);
	
	// save each product to database
	Arrays.stream(users).forEach(repository::save);	
		
		
	}
	
	@AfterEach
	public void cleanUp() {
		repository.deleteAll();
		
	}
	
    @Test
	@DisplayName("Test User by email - GET /api/v1/users/")
	public void testGetUsersByEmail() throws Exception {
		
		
		// Perform GET Request
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
		// Validate Status should be 200 OK and JSON response received
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		// Validate Response Body
		
		.andExpect(jsonPath("$[0].pnr", is(123)))
		.andExpect(jsonPath("$[0].seatNo", is("abc")))
		.andExpect(jsonPath("$[0].baggageWeight", is(15)))
		.andExpect(jsonPath("$[0].email", is("abc@gmail.com")))
		.andExpect(jsonPath("$[0].firstName", is("Shivangi")))
		.andExpect(jsonPath("$[0].lastName", is("Katiyar")));
		
}
	
	@Test
	@DisplayName("Test All Users /api/v1/users/")
	public void testGetAllUsers() throws Exception {
		
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
		// Validate Status should be 200 OK and JSON response received
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		// Validate Response Body
		
		.andExpect(jsonPath("$[0].pnr", is(123)))
		.andExpect(jsonPath("$[0].seatNo", is("abc")))
		.andExpect(jsonPath("$[0].baggageWeight", is(15)))
		.andExpect(jsonPath("$[0].email", is("abc@gmail.com")))
		.andExpect(jsonPath("$[0].firstName", is("Shivangi")))
		.andExpect(jsonPath("$[0].lastName", is("Katiyar"))) 
		
		.andExpect(jsonPath("$[1].pnr", is(124)))
		.andExpect(jsonPath("$[1].seatNo", is("def")))
		.andExpect(jsonPath("$[1].baggageWeight", is(10)))
		.andExpect(jsonPath("$[1].email", is("def@gmail.com")))
		.andExpect(jsonPath("$[1].firstName", is("Rahul")))
		.andExpect(jsonPath("$[1].lastName", is("Vamsi")));
		
		
		
		
	}
	
	@Test
	@DisplayName("Test All Users By BaggageWeight /api/v1/users/{baggageWeight}")
	public void testGetAllUsersByBaggageWeight() throws Exception {
		
	
		// Prepare Mock Service Method
		double baggageWeight =200;
		
		
		// Perform GET Request
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
		// Validate Status should be 200 OK and JSON response received
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		// Validate Response Body
		
		.andExpect(jsonPath("$[0].pnr", is(123)))
		.andExpect(jsonPath("$[0].seatNo", is("abc")))
		.andExpect(jsonPath("$[0].baggageWeight", is(15)))
		.andExpect(jsonPath("$[0].email", is("abc@gmail.com")))
		.andExpect(jsonPath("$[0].firstName", is("Shivangi")))
		.andExpect(jsonPath("$[0].lastName", is("Katiyar"))) 
		
		.andExpect(jsonPath("$[1].pnr", is(124)))
		.andExpect(jsonPath("$[1].seatNo", is("def")))
		.andExpect(jsonPath("$[1].baggageWeight", is(10)))
		.andExpect(jsonPath("$[1].email", is("def@gmail.com")))
		.andExpect(jsonPath("$[1].firstName", is("Rahul")))
		.andExpect(jsonPath("$[1].lastName", is("Vamsi")))
		
		.andExpect(jsonPath("$[2].pnr", is(125)))
		.andExpect(jsonPath("$[2].seatNo", is("ghi")))
		.andExpect(jsonPath("$[2].baggageWeight", is(10)))
		.andExpect(jsonPath("$[2].email", is("ghi@gmail.com")))
		.andExpect(jsonPath("$[2].firstName", is("Sunny")))
		.andExpect(jsonPath("$[2].lastName", is("Mahanth")));
		
		
		
	}
	
	@Test
	@DisplayName("Test All Users By Pnr /api/v1/users?pnr=&email")
	public void testGetAllUsersByPnrOrEmail() throws Exception {
		
		
		
		// Prepare Mock Service Method
		Integer pnr =123;
		String email="abc@gmail.com";
		
	
		
		
		// Perform GET Request
		
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/users/findByPnrOrEmail")
				.queryParam("email",email)
				.queryParam("pnr", pnr.toString()))
		// Validate Status should be 200 OK and JSON response received
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		// Validate Response Body
		
		.andExpect(jsonPath("$[0].pnr", is(123)))
		.andExpect(jsonPath("$[0].seatNo", is("abc")))
		.andExpect(jsonPath("$[0].baggageWeight", is(15)))
		.andExpect(jsonPath("$[0].email", is("abc@gmail.com")))
		.andExpect(jsonPath("$[0].firstName", is("Shivangi")))
		.andExpect(jsonPath("$[0].lastName", is("Katiyar"))) ;
		

		
		
		
		
	}
	
	
	
	
	@Test
	@DisplayName("Test Add New User")
	public void testAddNewUser() throws Exception {
		
		// Prepare Mock Product
		User newuser = new User(50,"abc",8, "abc@gmail.com", "Shivangi","Katiyar");
		
		User mockuser = new User(50,"abc",8, "abc@gmail.com", "Shivangi","Katiyar");
		//mockuser.setPnr(50);
		// Prepare Mock Service Method
		
		
		
		// Perform GET Request
		
		mockMvc.perform(post("/api/v1/users")
		// Validate Status should be 200 OK and JSON response received
		
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newuser)))
		
		
		// Validate Response Body
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.pnr", is(50)))
		.andExpect(jsonPath("$.seatNo", is("abc")))
		.andExpect(jsonPath("$.baggageWeight", is(8)))
        .andExpect(jsonPath("$.email", is("abc@gmail.com")))
		.andExpect(jsonPath("$.firstName", is("Shivangi")))
		.andExpect(jsonPath("$.lastName", is("Katiyar")));
		
		
	}
	
	

}
