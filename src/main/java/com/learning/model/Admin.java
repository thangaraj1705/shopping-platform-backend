package com.learning.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin_Credential")
public class Admin {

	@Id
	@Column(name="S.NO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="FIRST_NAME",nullable=false)
	private String firstname;

	@Column(name="SECOND_NAME",nullable=false)
	private String secondname;

	@Column(name="GENDER",nullable=false)
	private String gender;

	@Column(name="AGE",nullable=false)
	private int age;

	@Column(name="EMAIL",nullable=false)
	private String email;

	@Column(name="PASSWORD",nullable=false)
	private String password;

	@Column(name="ADDRESS",nullable=false)
	private String address;

	@Column(name="FAILED_ATTEMPT")
	private Integer failedAttempt;

	@Column(name="LOCK_TIME")
	private Date lockTime;

	@Column(name="Lock_Flag")
	private char lockFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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





}
