package com.qa.sharks.service;

import java.util.List;

public interface ServiceMethods<T> {
	
	// create method of type T
	T create(T shark);
	
	// read all object T in a list
	List<T> readAll();
	
	// read by ID
	T readById(long id);
	
	// update object T which is found by it's ID
	T update(long id, T shark);
	
	// delete object based on ID
	boolean delete(long id);
}