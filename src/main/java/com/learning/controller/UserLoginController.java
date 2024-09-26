package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.jwt.JwtUtils;
import com.learning.model.SignUp;
import com.learning.model.SignIn;
import com.learning.service.UserService;

import jakarta.validation.constraints.Min;

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

	@GetMapping("/listuserdetails")
	public ResponseEntity<List<SignUp>> listOfUsers(){

		try {
			List<SignUp> userDetails= userService.listOfUser();
			if(userDetails.isEmpty()) {
				return ResponseEntity.noContent().build();     //204  users not found
			}
			return ResponseEntity.ok(userDetails);  //200 data found

		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();   //500 server error
		}

	}
	
	@DeleteMapping("/deleteuser")
	public ResponseEntity<String> deleteUser(@RequestParam ("userId") @Min(1) Long userId){
		
		try {			
			userService.deleteUserId(userId);
			return ResponseEntity.ok("User has been deleted successfully!!!!!!");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

}
