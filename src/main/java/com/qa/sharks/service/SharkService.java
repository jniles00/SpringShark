package com.qa.sharks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.sharks.entity.Shark;
import com.qa.sharks.repo.SharkRepo;

@Service
public class SharkService implements ServiceMethods<Shark>{
	
	// creating a SharkRepo variable 
	private SharkRepo repo;
	
	public SharkService(SharkRepo repo) {
		this.repo = repo;
	}
	
	// create a new shark object
	@Override
	public Shark create(Shark shark) {
		return this.repo.save(shark);
	}

	@Override
	public List<Shark> readAll() {
		return this.repo.findAll();
	}

	// finds shark based on it's ID
	@Override
	public Shark readById(long id) {
		Optional<Shark> getShark = this.repo.findById(id);
		
		// checks if an ID is present if so then it will return that Shark
		if(getShark.isPresent()) {
			return getShark.get();
		}
		return null;
	}

	// updates shark information
	@Override
	public Shark update(long id, Shark shark) {
		Optional<Shark> existingShark = this.repo.findById(id);
		if(existingShark.isPresent()) {
			Shark exist = existingShark.get();
			// DO NOT change ID
			exist.setAge(shark.getAge());
			exist.setType(shark.getType());
			exist.setHabitat(shark.getHabitat());
			exist.setName(shark.getName());
			
			return this.repo.saveAndFlush(exist);
		}
		return null;
	}

	// Delete ID, check if still exist by trying to return it, if it deleted then it should return true
	@Override
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}