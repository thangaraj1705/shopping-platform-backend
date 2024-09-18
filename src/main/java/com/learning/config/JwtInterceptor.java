package com.learning.config;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.learning.jwt.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends WebRequestHandlerInterceptorAdapter {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

	@Autowired
	private JwtUtils jwtUtils;

	public JwtInterceptor(WebRequestInterceptor requestInterceptor) {
		super(requestInterceptor);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    String requestURI = request.getRequestURI();
	    String authHeader = request.getHeader("Authorization");

	    if (!(requestURI.contains("signup") || requestURI.contains("signin"))) {
	        
	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            logger.warn("Missing or invalid Authorization header");
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
	            return false;
	        }
	        
	        String token = authHeader.substring(7); 

	        try {
	            jwtUtils.verifyToken(token);
	            logger.info("Token is valid");
	        } catch (Exception e) {
	            logger.error("Token verification failed: {}", e.getMessage());
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
	            return false;
	        }
	    }

	    return super.preHandle(request, response, handler);
	}




}
