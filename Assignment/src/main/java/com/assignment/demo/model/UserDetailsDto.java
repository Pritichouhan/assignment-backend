package com.assignment.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsDto {
	
	
	private String username;
	
	private String mobileNumber;
	
	private String address;
	
	private String skills;
	
	private String hobbies;
	
	private String photo;

}
