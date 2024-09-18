package com.learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
	
	@PostMapping("/addcart")
	public ResponseEntity<?> addProductCart(String productname){
		
		 
		
		return ResponseEntity.ok("");
	}

}
