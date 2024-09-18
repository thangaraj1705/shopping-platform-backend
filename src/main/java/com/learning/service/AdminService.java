package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.model.Admin;
import com.learning.repository.AdminRepo;

@Service
public class AdminService {
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	PasswordEncoder  passwordEncoder;
	
	
	public Admin findByEmailAddress(String email) {
		return adminRepo.findByEmail(email).orElse(null);
	}

	@Transactional
	public Admin save(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		admin.setFailedAttempt(0);
		admin.setLockFlag('N');
		return adminRepo.save(admin);
	}
}
