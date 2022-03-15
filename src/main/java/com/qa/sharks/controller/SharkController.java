package com.qa.sharks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.sharks.entity.Shark;
import com.qa.sharks.service.SharkService;

@RestController
@RequestMapping("/shark")
public class SharkController {
	
	private SharkService service;
	
	@Autowired // instructs Spring to insert the DuckService object
	private SharkController(SharkService service) {
		this.service = service;
	}
	
	// Create - used to enter data
	@PostMapping("/create")
	// @RequestBody allows us to pass through an object/data when the request is made
	public ResponseEntity<Shark> createShark(@RequestBody Shark shark){
		return new ResponseEntity<Shark>(this.service.create(shark), HttpStatus.CREATED);
	}
	
	// Read All - returns a list of all the sharks
	@GetMapping("/readAll")
	public ResponseEntity<List<Shark>> readAll(){
		return new ResponseEntity<List<Shark>>(this.service.readAll(), HttpStatus.OK);
	}

	// Read By ID
	@GetMapping("/readById/{id}")
	public ResponseEntity<Shark> readById(@PathVariable long id){
			return new ResponseEntity<Shark>(this.service.readById(id), HttpStatus.OK);
	}
	
	// Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Shark> updateShark(@PathVariable long id, @RequestBody Shark shark){
		return new ResponseEntity<Shark>(this.service.update(id,shark), HttpStatus.ACCEPTED);	
	}
	
	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteShark(@PathVariable long id){
		// If shark object is deleted then it will return "NO_CONTENT"
		return(this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT):
		// Else it will return "NOT_FOUND"
			new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}
}