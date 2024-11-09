package com.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
		
	@Bean
	public PasswordEncoder passwordEncoder() {		
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
	        .csrf().disable()
	        .authorizeRequests()
	        .requestMatchers(HttpMethod.PUT, "/update-product/*").permitAll() 
	        .requestMatchers("/signup", "/signin", "/dashboard/statics", "/addproduct", "/advertisements",
	                         "/offerProducts", "/uploadAd", "/uploadOffer", "/delete", "/listProducts",
	                         "/search", "/filter-offers-by-starting-price", "/listuserdetails", "/deleteuser","/add-to-cart",
	                         "/ads/*", "/product-details/*", "/populate-product/*", "/update-ad/*")
	            .permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .exceptionHandling()
	            .authenticationEntryPoint((request, response, authException) -> {
	                System.out.println("Unauthorized request: " + request.getRequestURI());
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	            })
	            .accessDeniedHandler((request, response, accessDeniedException) -> {
	                System.out.println("Access denied: " + request.getRequestURI());
	                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
	            });

	    return http.build();
      
	        
	    }
}
