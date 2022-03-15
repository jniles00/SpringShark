package com.qa.sharks.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.sharks.entity.Shark;

// Boots the context
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Using Random_Port select a random port to run the test on
@AutoConfigureMockMvc										 // Creates the MockMvc object
@ActiveProfiles("test")										 // Sets current profile to "test"
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:shark-schema.sql",
				"classpath:shark-data.sql"})
public class SharkControllerIntergrationTest {
	
	@Autowired												// Tells Spring to insert this object into the class
	private MockMvc mvc;									// Object for running the fake requests

	@Autowired
	private ObjectMapper mapper;							// This is the object that Spring uses to convert JSON to Java
	
//	@Test
//	public void test() {
//		assertEquals(2, 1 + 1);
//	}
	
	@Test
	public void testCreate() throws Exception {
		
		//URL, Body, Header, Method
		Shark testShark = new Shark(1,3, "Inu", "Sea", "Great White");
		String testSharkAsJSON = this.mapper.writeValueAsString(testShark);
		RequestBuilder req = post("/shark/create").content(testSharkAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		Shark testSavedShark = new Shark(1,3, "Inu", "Sea", "Great White");
		String testSavedSharkAsJSON = this.mapper.writeValueAsString(testSavedShark);
		//this will check the status code of my response
		ResultMatcher checkStatus = status().isCreated();
		// this will check the body of the response
		ResultMatcher checkBody = content().json(testSavedSharkAsJSON);
		
		// run the request and check both matchers
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testCreate2() throws Exception {
		Shark testShark = new Shark(2, 19, "Kappa", "Lake", "Killer");
		String testSharkAsJSON = this.mapper.writeValueAsString(testShark);
		RequestBuilder req = post("/shark/create").content(testSharkAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		Shark testSavedShark = new Shark(2, 19, "Kappa", "Lake", "Killer");
		String testSavedSharkAsJSON = this.mapper.writeValueAsString(testSavedShark);
		//this will check the status code of my response
		ResultMatcher checkStatus = status().isCreated();
		// this will check the body of the response
		ResultMatcher checkBody = content().json(testSavedSharkAsJSON);
		
		// run the request and check both matchers
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReadAll() throws Exception {
		
		Shark entryShark = new Shark(1, 6, "niku", "unknown", "deep dweller");
		List<Shark> sharkList = new ArrayList<Shark>();
		sharkList.add(entryShark);
		String sharkListOutputAsJson = this.mapper.writeValueAsString(sharkList);
		
		this.mvc.perform(get("/shark/readAll").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(sharkListOutputAsJson));
		
//		RequestBuilder req = get("/shark/readAll");
//				
//		ResultMatcher checkStatus = status().isOk();
//		
//		List<Shark> testSharkList = new ArrayList<Shark>();
//
//		ResultMatcher checkBody = content().json(testSharkList);
	}
	
	@Test
	public void testReadById() throws Exception {
		RequestBuilder req = get("/shark/readById/1");
		
		ResultMatcher checkStatus = status().isOk();
		
		Shark savedShark = new Shark(1, 6, "niku", "unknown", "deep dweller");
		String savedSharkAsJSON = this.mapper.writeValueAsString(savedShark);
		
		ResultMatcher checkBody = content().json(savedSharkAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testUpdate() throws Exception {
			
//		Shark testShark = new Shark(2, 19, "Kappa", "Lake", "Killer");
//		String testSharkAsJSON = this.mapper.writeValueAsString(testShark);
//		RequestBuilder req = post("/shark/create").content(testSharkAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		Shark updateShark = new Shark(2, 12, "Paul", "Lake", "Friendly");
		String updateSharkAsJSON = this.mapper.writeValueAsString(updateShark);
		
		RequestBuilder reqUpdate = put("/shark/update").content(updateSharkAsJSON).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(updateSharkAsJSON);
		
		this.mvc.perform(reqUpdate).andExpect(checkStatus).andExpect(checkBody);	
	}
	
//	public void testDelete() throws Exception {
//		
//		this.mvc.perform(get("/shark/readAll").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk());
//	}	
}