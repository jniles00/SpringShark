package com.qa.sharks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.sharks.entity.Shark;

@Repository
public interface SharkRepo extends JpaRepository<Shark, Long>{
}