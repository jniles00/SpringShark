package com.qa.sharks.entity;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SharkTest {

	// Make sure to import the equalsverifier dependency
	@Test
	public void testEquals() {
		// 
		EqualsVerifier.forClass(Shark.class).usingGetClass().verify();
	}
	
	@Test
	public void getAndSetTest() {
		
		// Creates an empty shark object
		Shark shark = new Shark();
		
		// Uses the setter methods to add values to each filed, so we can check the setters work
		shark.setId(1);
		shark.setAge(3);
		shark.setHabitat("Water");
		shark.setName("Jake");
		shark.setType("Hammerhead");
		
		// Make sure after the setters, they actually set the values and are not null
		assertNotNull(shark.getId());
		assertNotNull(shark.getAge());
		assertNotNull(shark.getHabitat());
		assertNotNull(shark.getName());
		assertNotNull(shark.getType());
		
		// Make sure that when we use the getters, they get the correct values
		assertEquals(shark.getId(), 1);
		assertEquals(shark.getAge(), 3);
		assertEquals(shark.getHabitat(), "Water");
		assertEquals(shark.getName(), "Jake");
		assertEquals(shark.getType(), "Hammerhead");
	}

	// Another check for a Shark object 
	@Test
	public void allArgsConstructor() {

		Shark shark = new Shark(19 ,6, "Jojo", "Morioh", "Stand");
		
		assertNotNull(shark.getId());
		assertNotNull(shark.getAge());
		assertNotNull(shark.getHabitat());
		assertNotNull(shark.getName());
		assertNotNull(shark.getType());
		
		assertEquals(shark.getId(), 19);
		assertEquals(shark.getAge(), 6);
		assertEquals(shark.getHabitat(), "Morioh");
		assertEquals(shark.getName(), "Jojo");
		assertEquals(shark.getType(), "Stand");
	}
}