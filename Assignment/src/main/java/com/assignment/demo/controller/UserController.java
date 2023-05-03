package com.assignment.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.demo.exception.InvalidUserException;
import com.assignment.demo.exception.UserAlreadyExistsException;
import com.assignment.demo.model.LoginRequestDto;
import com.assignment.demo.model.User;
import com.assignment.demo.model.UserDetails;
import com.assignment.demo.model.UserDetailsDto;
import com.assignment.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody User user) throws UserAlreadyExistsException {
		return ResponseEntity.ok().body(userService.signUpUser(user));
	}

	@PostMapping("/login")
	public ResponseEntity<Optional<LoginRequestDto>> login(@RequestBody LoginRequestDto loginRequestDto)
			throws UserAlreadyExistsException, InvalidUserException {
		System.out.print(loginRequestDto.getUsername());
		return ResponseEntity.ok().body(userService.login(loginRequestDto));
	}

	@PostMapping(value="/userdetails/{username}" , consumes = { "multipart/form-data" })
	public ResponseEntity<UserDetails> userdetails(@PathVariable String username,@RequestBody UserDetails userDetails)
			throws InvalidUserException {
		userService.userProfile(username, userDetails);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/userdetailphoto/{username}")
    public ResponseEntity<String> uploadMultipart(@PathVariable String username,@RequestParam("file") MultipartFile file) {
        try {
        	userService.saveFile(username, file);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

	@GetMapping("/userdetails/{username}")
	public ResponseEntity<UserDetailsDto> userdetailsdata(@PathVariable String username) {
		return ResponseEntity.ok().body(userService.getUserData(username));

	}
}
