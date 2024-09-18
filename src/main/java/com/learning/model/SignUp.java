package com.learning.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="user_credential")
public class SignUp {

	@Id
	@Column(name="S.No")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="FIRSTNAME",nullable=false)
	private String firstName;
	
	@Column(name="LASTNAME",nullable=false)
	private String lastName;

	@Column(name="USERNAME",nullable=false,unique=true)
	private String username;

	@Column(name="EMAIL",unique=true)
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	
	@Column(name="MOBILE_NUMBER",unique=true)
	private String mobileNumber;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="FAILED_ATTEMPT")
	private Integer failedAttempt;

	@Column(name="LOCK_TIME")
	private Date lockTime;
	
	@Column(name="Lock_Flag")
	private char lockFlag;
	
	@Column(name="USER_ROLE")
	private String userRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(Integer failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public char getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(char lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	
	
}
