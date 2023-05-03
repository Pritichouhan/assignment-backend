package com.assignment.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.demo.model.LoginRequestDto;
import com.assignment.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {	
	
	Optional<LoginRequestDto> findByUsername(String username);
	
	Optional<User> findUserByUsername(String username);
	
    boolean existsByUsername(String username);
}
