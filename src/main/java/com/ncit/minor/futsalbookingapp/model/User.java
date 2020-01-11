package com.ncit.minor.futsalbookingapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "fullname",unique = true)
	private String fullname;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "phoneNo")
	private String phoneNo;

	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	@Column(name = "userRole")
	private String userRole;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Booking> bookings;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Futsal> futsal=new ArrayList<>();

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public long getId() {
		return id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	
}
