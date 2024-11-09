package com.learning.dto;

import org.springframework.stereotype.Component;

@Component
public class CartRequestDTO {
	
	private Long productId;
	
	private Long itemQuantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	

}
