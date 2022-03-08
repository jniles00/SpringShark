package com.qa.sharks.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Shark {

	// auto generates the ID and puts it into the ID column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@Min(1) // Min number allowed
	@Max(20) // Max number allowed
	private int age;

	// name has to contain a string and must be unique
	@Column(unique = true, nullable = false)
	private String name;

	@Column
	private String habitat;

	@Column
	private String type;
	
	// Default Constructor
	public Shark() {}
	
	// Constructor for creating Shark objects
	public Shark(@Min(1) @Max(20) int age, String name, String habitat, String type) {
		super();
		this.age = age;
		this.name = name;
		this.habitat = habitat;
		this.type = type;
	}

	// Getters and Setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// End of Getters and Setters

	@Override
	public String toString() {
		return "Shark [id=" + id + ", age=" + age + ", name=" + name + ", habitat=" + habitat + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, habitat, id, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shark other = (Shark) obj;
		return age == other.age && Objects.equals(habitat, other.habitat) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(type, other.type);
	}
}