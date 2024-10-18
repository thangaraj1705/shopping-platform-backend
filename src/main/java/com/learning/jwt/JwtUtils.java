package com.learning.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.learning.model.SignUp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static final long Duration=30*60;
	
	@Value("${secret.key}")
	private String jwtSecret;

	public String generateJwt(SignUp signUp) {

		long milliTime=System.currentTimeMillis();
		long expiryTime=milliTime+Duration*1000;

		Date issueAt= new Date(milliTime);
		Date expiryAt=new Date(expiryTime);

		Map<String, Object> claims = new HashMap<>();
		claims.put("username",signUp.getUsername());
		claims.put("email",signUp.getEmail());


		String token=Jwts.builder()
				.setClaims(claims)		
				.setIssuer("onine shopping")
				.setIssuedAt(issueAt)
				.setExpiration(expiryAt)
				.signWith(SignatureAlgorithm.HS512,jwtSecret)
				.compact();

		return token;
	}


	public void verifyToken(String auth) throws Exception {

		try {

			Jws<Claims> jwsClaims = Jwts.parser()
					.setSigningKey(jwtSecret)
					.parseClaimsJws(auth);
		}
		catch(Exception e) {
			throw new AccessDeniedException("Access Denied");
		}


	}

}
