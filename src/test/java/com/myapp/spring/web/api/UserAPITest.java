package com.myapp.spring.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.User;
import com.myapp.spring.repository.UserRepository;

@SpringBootTest

@AutoConfigureMockMvc(addFilters = false)
public class UserAPITest {
	
	@MockBean
	private UserRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	//@Test
	@DisplayName("Test User by Pnr - GET /api/v1/users/")
	public void testGetsByPnr() throws Exception {
		
		// Prepare Mock Product
		User user = new User(123,"abc",15, "abc@gmail.com", "Shivangi","Katiyar");
		
		
		// Prepare Mock Service Method
		
		doReturn(Optional.of(user)).when(repository).findByPnr(user.getPnr());
		
		// Perform GET Request
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{pnr}",123))
		// Validate Status should be 200 OK and JSON response received
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		// Validate Response Body
		
		.andExpect(jsonPath("$.pnr", is(123)))
		.andExpect(jsonPath("$.seatNo", is("abc")))
		.andExpect(jsonPath("$.baggageWeight", is(15)))
        .andExpect(jsonPath("$.email", is("abc@gmail.com")))
		.andExpect(jsonPath("$.firstName", is("Shivangi")))
		.andExpect(jsonPath("$.lastName", is("Katiyar")));
		
		
		
	}
	
	@Test
	@DisplayName("Test All Users /api/v1/users/")
	public void testGetAllUsers() throws Exception {
		
		// Prepare Mock Product
		User user1 = new User(123,"abc",10, "abc@gmail.com", "Shivangi","Katiyar");
		//product1.setProductId(35);
		
		User user2 = new User(124,"def",13, "def@gmail.com", "Rahul","Vamsi");
		//product2.setProductId(36);
		
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		
		// Prepare Mock Service Method
		
		doReturn(users).when(repository).findAll();
		
		// Perform GET Request
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
		// Validate Status should be 200 OK and JSON response received
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		// Validate Response Body
		
		.andExpect(jsonPath("$[0].pnr", is(123)))
		.andExpect(jsonPath("$[0].seatNo", is("abc")))
		.andExpect(jsonPath("$[0].baggageWeight", is(10)))
		.andExpect(jsonPath("$[0].email", is("abc@gmail.com")))
		.andExpect(jsonPath("$[0].firstName", is("Shivangi")))
		.andExpect(jsonPath("$[0].lastName", is("Katiyar"))) 
		
		.andExpect(jsonPath("$[1].pnr", is(124)))
		.andExpect(jsonPath("$[1].seatNo", is("def")))
		.andExpect(jsonPath("$[1].baggageWeight", is(13)))
		.andExpect(jsonPath("$[1].email", is("def@gmail.com")))
		.andExpect(jsonPath("$[1].firstName", is("Rahul")))
		.andExpect(jsonPath("$[1].lastName", is("Vamsi")));

		
		
		
		
}
	
	@Test
	@DisplayName("Test All Users By BaggageWeight /api/v1/users/{baggageWeight}")
	public void testGetAllUsersByBaggageWeight() throws Exception {
		
		// Prepare Mock Product
		User user1 = new User(123,"abc",10, "abc@gmail.com", "Shivangi","Katiyar");
		
		User user2 = new User(124,"def",13, "def@gmail.com", "Rahul","Vamsi");
		
		User user3 = new User(125,"ghi",13, "ghi@gmail.com", "Sunny","Mahanth");
		
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		// Prepare Mock Service Method
		Integer baggageWeight =9;
		
		doReturn(Optional.of(users)).when(repository)
		.findByBaggageWeightGreaterThanEqual(baggageWeight);
		
		// Perform GET Request
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/findByBaggageWeight/{BaggageWeight}",baggageWeight))
		// Validate Status should be 200 OK and JSON response received
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		
		// Validate Response Body
		
		.andExpect(jsonPath("$[0].pnr", is(123)))
		.andExpect(jsonPath("$[0].seatNo", is("abc")))
		.andExpect(jsonPath("$[0].baggageWeight", is(10)))
		.andExpect(jsonPath("$[0].email", is("abc@gmail.com")))
		.andExpect(jsonPath("$[0].firstName", is("Shivangi")))
		.andExpect(jsonPath("$[0].lastName", is("Katiyar"))) 
		
		.andExpect(jsonPath("$[1].pnr", is(124)))
		.andExpect(jsonPath("$[1].seatNo", is("def")))
		.andExpect(jsonPath("$[1].baggageWeight", is(13)))
		.andExpect(jsonPath("$[1].email", is("def@gmail.com")))
		.andExpect(jsonPath("$[1].firstName", is("Rahul")))
		.andExpect(jsonPath("$[1].lastName", is("Vamsi")))
		
		.andExpect(jsonPath("$[2].pnr", is(125)))
		.andExpect(jsonPath("$[2].seatNo", is("ghi")))
		.andExpect(jsonPath("$[2].baggageWeight", is(13)))
		.andExpect(jsonPath("$[2].email", is("ghi@gmail.com")))
		.andExpect(jsonPath("$[2].firstName", is("Sunny")))
		.andExpect(jsonPath("$[2].lastName", is("Mahanth")));

		
		
	}
	
	@Test
	@DisplayName("Test All Users By Pnr /api/v1/users?pnr=&email")
	public void testGetAllProductsByPnrOrEmail() throws Exception {
		
		// Prepare Mock Product
		User user1 = new User(123,"abc",10, "abc@gmail.com", "Shivangi","Katiyar");
		
		User user2 = new User(124,"def",13, "def@gmail.com", "Rahul","Vamsi");
	
		
		
		
		List<User> users = new ArrayList<>();
	    users.add(user1);
		users.add(user2);
		
		
		// Prepare Mock Service Method
		Integer pnr =123;
		String email="abc@gmail.com";
		
		doReturn(Optional.of(users)).when(repository)
		.findByPnrOrEmail(pnr,email);
		
		
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
		.andExpect(jsonPath("$[0].baggageWeight", is(10)))
		.andExpect(jsonPath("$[0].email", is("abc@gmail.com")))
		.andExpect(jsonPath("$[0].firstName", is("Shivangi")))
		.andExpect(jsonPath("$[0].lastName", is("Katiyar"))) 
		
		.andExpect(jsonPath("$[1].pnr", is(124)))
		.andExpect(jsonPath("$[1].seatNo", is("def")))
		.andExpect(jsonPath("$[1].baggageWeight", is(13)))
		.andExpect(jsonPath("$[1].email", is("def@gmail.com")))
		.andExpect(jsonPath("$[1].firstName", is("Rahul")))
		.andExpect(jsonPath("$[1].lastName", is("Vamsi")));		
		
		
		
	}
	
	
	
	
	@Test
	@DisplayName("Test Add New User")
	public void testAddNewUser() throws Exception {
		
		// Prepare Mock Product
		User newuser = new User(123,"abc",10, "abc@gmail.com", "Shivangi","Katiyar");
		
		User mockuser = new User(123,"abc",10, "abc@gmail.com", "Shivangi","Katiyar");
		
		// Prepare Mock Service Method
		
		doReturn(mockuser).when(repository).save(ArgumentMatchers.any());
		
		// Perform GET Request
		
		mockMvc.perform(post("/api/v1/users")
		// Validate Status should be 200 OK and JSON response received
		
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(newuser)))
		
		
		// Validate Response Body
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.pnr", is(123)))
		.andExpect(jsonPath("$.seatNo", is("abc")))
		.andExpect(jsonPath("$.baggageWeight", is(10)))
		.andExpect(jsonPath("$.email", is("abc@gmail.com")))
		.andExpect(jsonPath("$.firstName", is("Shivangi")))
		.andExpect(jsonPath("$.lastName", is("Katiyar"))) ;		
		
}
	//@Test
	@DisplayName("Test Update Existing User")
	public void testUpdateExistingUser() throws Exception {
		
		// Prepare Mock Product
		
		User mockuser = new User(123,"abc",10, "abc@gmail.com", "Shivangi","Katiyar");
		
		User userToBeUpdated = new User(123,"abc",10, "abc@gmail.com", "Shivangi","Katiyar");
		userToBeUpdated.setPnr(126);
		
		
		mockuser.setPnr(126);
		// Prepare Mock Service Method
		
		doReturn(Optional.of(mockuser)).when(repository).findByPnr(126);
		
		doReturn(mockuser).when(repository).save(ArgumentMatchers.any());
		
		// Perform GET Request
		
		mockMvc.perform(put("/api/v1/users/{pnr}",126)
		// Validate Status should be 200 OK and JSON response received
		
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new ObjectMapper().writeValueAsString(userToBeUpdated)))
		
		
		// Validate Response Body
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.pnr", is(126)))
		.andExpect(jsonPath("$.seatNo", is("abc")))
		.andExpect(jsonPath("$.baggageWeight", is(10)))
		.andExpect(jsonPath("$.email", is("abc@gmail.com")))
		.andExpect(jsonPath("$.firstName", is("Shivangi")))
		.andExpect(jsonPath("$.lastName", is("Katiyar"))) ;	
		
		
	}
	

}
