package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.CartRequestDTO;
import com.learning.jwt.JwtUtils;
import com.learning.service.CartServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
public class CartController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@PostMapping("/add-to-cart")
	public ResponseEntity<?> addProductCart(@RequestBody CartRequestDTO cartRequestDTO,@RequestHeader ("Authorization") String tokenHeader){
		
		 try {
			 String token=tokenHeader.replace("Bearer", "");
			 Jws<Claims> claims=jwtUtils.verifyToken(token);
			 
			 Long userId=Long.parseLong(claims.getBody().get("userId").toString());
			 cartServiceImpl.addItemToCart(cartRequestDTO,userId);
			 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		
		
		return ResponseEntity.ok("Product Added in cart successfully");
	}

}
