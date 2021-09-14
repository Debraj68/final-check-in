package com.myapp.spring.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.User;
//import com.myapp.spring.repository;
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
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
	
//	@Test
//	@DisplayName("Test user not found for a non existing pnr")
//	public void testUserNotFoundForNonExistingPnr() {
//		
//		// given three products in the database
//		
//		// when we retrieve a product using non existing id
//		User user=repository.findByPnr(100).orElseGet(()-> new User());
//		
//		// Then perform Assert Conditions To validate
//		Assertions.assertNull(user.getPnr(), 
//				"User With Pnr 100 should not exist");
//		
//		}
	
	
	@Test
	@DisplayName("Test user saved sucessfully")
	public void testUserSavedSucessfully() {
		
		// given a mock product
		User user = new User(20,"uvw",15,"pqr@gmail.com","Diksha","Gurjar");
		//user.setPnr(20);
		
		// when we retrieve a product using non existing id
		User savedUser=repository.save(user);
		
		// Then perform Assert Conditions To validate
		Assertions.assertNotNull(savedUser, 
				"New User should be saved");
		
		Assertions.assertNotNull(savedUser.getPnr(), 
				"New User should have pnr");
		Assertions.assertEquals(user.getFirstName(), 
				savedUser.getFirstName());
		
		
		}
	
	@Test
	@DisplayName("Test user updated sucessfully")
	public void testUserUpdatedSucessfully() {
		
		// given a mock product
		User user = new User(11,"abc",8, "abc@gmail.com", "Shivangi","Katiyar");
		//user.setPnr(11);
		
		// when we retrieve a product using non existing id
		User updatedUser=repository.save(user);
		
		
		Assertions.assertEquals(user.getBaggageWeight(), 
				updatedUser.getBaggageWeight());
		
		
		}
	

}
