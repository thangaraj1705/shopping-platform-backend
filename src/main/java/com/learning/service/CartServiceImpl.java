package com.learning.service;

import org.springframework.stereotype.Component;

import com.learning.dto.CartRequestDTO;

@Component
public interface CartServiceImpl {
	

	public void addItemToCart(CartRequestDTO cartRequestDTO, Long userId);

}
