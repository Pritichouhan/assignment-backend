package com.assignment.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userdetails")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userdetails_id;
	@Column(name = "username")
	private String username;
	@Column(name = "mobile_number")
	private String mobileNumber;
	@Column(name = "address")
	private String address;
	@Column(name = "skills")
	private String skills;
	@Column(name = "hobbies")
	private String hobbies;
	@Column(name = "photo")
	private String photo;
	@OneToOne
    @JoinColumn(name = "user_id",  referencedColumnName = "id")
    private User user;

}
