package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.model.Admin;
import com.learning.repository.AdminRepo;
import com.learning.service.AdminService;



@RestController
public class AdminLoginController {

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	AdminService adminService;

	@PostMapping("/admin/signup")
	public ResponseEntity<?> adminRegistration(@RequestBody Admin admin){

		if(adminService.findByEmailAddress(admin.getEmail())!=null) {
			return ResponseEntity.badRequest().body("Error ! Email Address already taken!!!!");
		}
		adminRepo.save(admin);		
		
		return ResponseEntity.ok("Admin Registered Sucessfully!!!!");
	}
	
	

}
