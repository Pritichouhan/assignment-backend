package com.assignment.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.demo.model.UserDetails;
import com.assignment.demo.model.UserDetailsDto;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {	
	Optional<UserDetailsDto> findByUsername(String username);
	
	Optional<UserDetails> findUserByUsername(String username);
	
}
