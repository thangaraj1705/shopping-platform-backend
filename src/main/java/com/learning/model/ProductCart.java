package com.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="USER_CART")
public class ProductCart {

	@Id
	@Column(name="CART_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cartId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="PRODUCT_ID",referencedColumnName="S.NO")
	private Product product;

	@Column(name="ITEM_QUANTITY")
	private Long itemQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private SignUp signUp;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public SignUp getSignUp() {
		return signUp;
	}

	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
	}

	public ProductCart(Product product, Long itemQuantity, SignUp signUp) {
		super();
		this.product = product;
		this.itemQuantity = itemQuantity;
		this.signUp = signUp;
	}

	public ProductCart() {
		super();
	}




}
