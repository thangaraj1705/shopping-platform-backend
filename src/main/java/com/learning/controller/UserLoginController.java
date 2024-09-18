package com.learning.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.jwt.JwtUtils;
import com.learning.model.SignUp;
import com.learning.model.SignIn;
import com.learning.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:8085")
public class UserLoginController {

	@Autowired
	UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signup")
	public ResponseEntity<Map<String,String>> signUp(@RequestBody SignUp signUp){
		Map<String,String> response=new HashMap<>();

		if(userService.findByUsername(signUp.getUsername())!=null) {
			response.put("status", "error");
			response.put("message", "Error ! User name already taken!!!!");
			return ResponseEntity.badRequest().body(response);
		}
		else if(userService.findByEmailAddress(signUp.getEmail())!=null) {
			response.put("status", "error");
			response.put("message", "Error ! Email Address already taken!!!!");
			return ResponseEntity.badRequest().body(response);
		}

		userService.save(signUp);
		response.put("status", "success");
		response.put("message", "User Registered Sucessfully!!!!");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/signin")
	public ResponseEntity<Map<String,String>> signIn(@RequestBody SignIn signIn){
		Map<String,String> response=new HashMap<>();
		SignUp existingUser=userService.existingUserDetails(signIn.getUsernameOrEmail());
		SignUp unlockWhenTimeExpired=userService.unlockTimeExpired(existingUser);

		if(unlockWhenTimeExpired !=null && unlockWhenTimeExpired.getLockFlag()=='Y') {
			response.put("status", "error");
			response.put("message", userService.msg);
			return ResponseEntity.ok(response);
		}
		else {

			Boolean authenticate=userService.authenticate(signIn.getUsernameOrEmail(), signIn.getPassword());
			if(authenticate && unlockWhenTimeExpired !=null && unlockWhenTimeExpired.getLockFlag() !='Y') {
				String token=jwtUtils.generateJwt(existingUser);
				response.put("status", "success");
				response.put("token", token);
				response.put("userrole", existingUser.getUserRole());
				response.put("message", "Login Successfull!!!");
				return ResponseEntity.ok(response);
			}
			else {

				String msgs=(unlockWhenTimeExpired !=null && unlockWhenTimeExpired.getLockFlag()=='Y') ? userService.msg:"Invalid username or password???";
				response.put("status", "error");
				response.put("message",msgs);
				return ResponseEntity.ok(response);
			}


		}
	}

}
