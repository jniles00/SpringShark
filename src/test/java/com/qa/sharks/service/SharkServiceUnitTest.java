package com.qa.sharks.service;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.sharks.entity.Shark;
import com.qa.sharks.repo.SharkRepo;

@SpringBootTest
public class SharkServiceUnitTest {

	// Tells Spring to insert this object into the class
	@Autowired
	private SharkService service;

	// Mocks the repository class because we don't want to rely on the class itself
	@MockBean
	private SharkRepo repo;

	@Test
	public void createSharkTest() {

		// input should not have ID
		Shark sharkInput = new Shark(6, "Jojo", "Morioh", "Stand");
		// output should have ID
		Shark sharkOutput = new Shark(19, 6, "Jojo", "Morioh", "Stand");

		// This is testing the actual method (within the create method in the
		// SharkService class)
		Mockito.when(this.repo.save(sharkInput)).thenReturn(sharkOutput);

		// This is checking the expected value (sharkOutput) is the same as the value
		// that gets output when the create method in the service class is run
		assertEquals(sharkOutput, this.service.create(sharkInput));

		// Verifies that the repo is run 1 time before saving the input (sharkInput)
		Mockito.verify(this.repo, Mockito.times(1)).save(sharkInput);
	}

	@Test
	public void readByIdTest() {

		// An optional is used because one is used in the readById method within the
		// SharkService class
		Optional<Shark> optionalOutput = Optional.of(new Shark(19, 6, "Jojo", "Morioh", "Stand"));
		Shark sharkOutput = new Shark(19, 6, "Jojo", "Morioh", "Stand");

		// Mockingto.anyLong()checks that the type of data used is a Long, no matter
		// what the number is
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOutput);

		assertEquals(sharkOutput, this.service.readById(Mockito.anyLong()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void readAll() {
		List<Shark> sharkList = new ArrayList<Shark>();
		Shark sharkOutput = new Shark(19, 6, "Jojo", "Morioh", "Diamond");

		sharkList.add(sharkOutput);

		Mockito.when(this.repo.findAll()).thenReturn(sharkList);

		assertEquals(sharkList, this.service.readAll());

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	public void updateTest() {
		
	Shark sharkInput = new Shark(6, "Jojo", "Morioh", "Diamond");
	Shark sharkOutput = new Shark(19, 6, "Jojo", "Morioh", "Diamond");
	Optional<Shark> optionalOutput = Optional.of(new Shark(20, 6, "Jolyne", "Stone Ocean", "Butterfly"));

	Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOutput);
	Mockito.when(this.repo.saveAndFlush(sharkInput)).thenReturn(sharkOutput);

	assertEquals(sharkOutput, this.service.update(Mockito.anyLong(), sharkOutput));

	Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
	Mockito.verify(this.repo, Mockito.times(1)).save(sharkOutput);
	}
	
	@Test
	public void deleteTrueTest() {

		// checks to see if the object with a specific ID is not there
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		// if it is then it will delete that object based on it's ID
		assertTrue(this.service.delete(1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
	@Test
	public void deleteFalseTest() {

		// checks to see if the object with a specific ID is still there
		Mockito.when(this.repo.existsById(1L)).thenReturn(true);
		
		// if not then it
		assertFalse(this.service.delete(1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
}