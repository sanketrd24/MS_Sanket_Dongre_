package com.morningstar.bookshop.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Users {
	
	
	
	public Users() {
		super();
	}
	
	
	

	public Users(int userId, @NotBlank(message = "Please Provide Fisrt Name") String fname,
			@NotBlank(message = "Please Provide Last Name") String lname,
			@Email(message = "Please Provide Valid Email") String email,
			@Pattern(regexp = "[0-9]{10}", message = "Plase Provide Valid Phone No.") String phoneNo,
			@NotBlank(message = "Please Provide Password") String password) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank(message="Please Provide Fisrt Name")
	private String fname;
	
	@NotBlank(message="Please Provide Last Name")
	private String lname;
	
	@Email(message="Please Provide Valid Email")
	private String email;
	
	@Pattern(regexp="[0-9]{10}",message="Plase Provide Valid Phone No.")
	private String phoneNo;
	
	@NotBlank(message="Please Provide Password")
	private String password;
	
//	@OneToMany(mappedBy = "user")
//	private List<Orders> orders;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phoneNo="
				+ phoneNo + ", password=" + password + "]";
	}

//	public List<Orders> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Orders> orders) {
//		this.orders = orders;
//	}

	
	
	

}
