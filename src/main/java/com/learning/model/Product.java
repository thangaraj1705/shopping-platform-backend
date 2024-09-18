package com.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Master_Product")
public class Product {

	@Id
	@Column(name="S.NO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long productId;

	@Column(name="PRODUCT_NAME",nullable=false)
	private String productName;

	@Column(name="DESCRIPTION",nullable=false)
	private String productDescription;

	@Column(name="PRICE",nullable=false)
	private double productPrice;

	@Column(name="DISCOUNT")
	private double productDiscount;

	@Column(name="IMAGE_PATH")
	private String productImgPath;

	@Column(name="RATING")
	private double productRating;



	public Product() {
		super();
	}

	public Long getProductId() {
		return productId;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}



	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}



	public double getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}



	public double getProductDiscount() {
		return productDiscount;
	}



	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}



	public String getProductImgPath() {
		return productImgPath;
	}



	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}



	public double getProductRating() {
		
		return productRating;
	}



	public void setProductRating(double productRating) {
		this.productRating = productRating;
	}

	

}
