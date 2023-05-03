package com.assignment.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.demo.exception.InvalidUserException;
import com.assignment.demo.exception.UserAlreadyExistsException;
import com.assignment.demo.model.LoginRequestDto;
import com.assignment.demo.model.User;
import com.assignment.demo.model.UserDetails;
import com.assignment.demo.model.UserDetailsDto;
import com.assignment.demo.repository.UserDetailsRepository;
import com.assignment.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public User signUpUser(User user) throws UserAlreadyExistsException {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserAlreadyExistsException("Username already exists.");
		} else {
			return userRepository.save(user);
		}
	}
	
	@Override
	public Optional<LoginRequestDto> login(LoginRequestDto loginRequest) throws InvalidUserException {
		Optional<LoginRequestDto> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
		if (optionalUser.isPresent()) {
			System.out.print(optionalUser.get().getUsername());
			LoginRequestDto user = optionalUser.get();
			
			if (user.getPassword().equals(loginRequest.getPassword())) {
				return optionalUser;
			} else {
				throw new InvalidUserException("Invalid password.");
			}
		}
		return optionalUser;

	}
	@Override
	public void saveFile(String username, MultipartFile file) throws IOException {
		UserDetails userDetails = userDetailsRepository.findUserByUsername(username).get();
		System.out.print("Image");
		if(username.equals(userDetails.getUsername())) {
			userDetails.setPhoto(file.getOriginalFilename());
			userDetailsRepository.save(userDetails);
		}
		
    }

	@Override
	public void userProfile(String username, UserDetails userDetails) throws InvalidUserException {
		Optional<User> optionalUser = userRepository.findUserByUsername(username);
		if (optionalUser.isPresent()) {
			userDetails.setUser(optionalUser.get());
			userDetails.setUsername(username);
			userDetailsRepository.save(userDetails);
		}
	}

	@Override
	public UserDetailsDto getUserData(String userName) {
		return userDetailsRepository.findByUsername(userName).get();
	}
	
	
	
	
	


}
