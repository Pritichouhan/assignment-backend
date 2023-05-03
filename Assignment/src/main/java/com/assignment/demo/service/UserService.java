package com.assignment.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.assignment.demo.exception.InvalidUserException;
import com.assignment.demo.exception.UserAlreadyExistsException;
import com.assignment.demo.model.LoginRequestDto;
import com.assignment.demo.model.User;
import com.assignment.demo.model.UserDetails;
import com.assignment.demo.model.UserDetailsDto;

public interface UserService {

	void userProfile(String username, UserDetails userDetails) throws InvalidUserException;

	User signUpUser(User user) throws UserAlreadyExistsException;

	Optional<LoginRequestDto> login(LoginRequestDto loginRequest) throws InvalidUserException;

	UserDetailsDto getUserData(String userName);
	
	void saveFile(String username, MultipartFile file) throws IOException ;

}
