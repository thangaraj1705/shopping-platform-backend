package com.learning.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.model.NutritionInfo;
import com.learning.model.ProductFeatures;

@Component
public class ProductRequestDTO {


	private String productName;

	private String productDescription;

	private double productPrice;

	private double productDiscount;

	private String productImgPath;

	private double productRating;	

	private double quantityInStock;
	
	@JsonProperty("productFeaturesDTO")
	private ProductFeaturesDTO productFeaturesDTO;

	@JsonProperty("nutritionInfoDTO")
	private NutritionInfoDTO nutritionInfoDTO;

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

	public double getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(double quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public ProductFeaturesDTO getProductFeaturesDTO() {
		return productFeaturesDTO;
	}

	public void setProductFeaturesDTO(ProductFeaturesDTO productFeaturesDTO) {
		this.productFeaturesDTO = productFeaturesDTO;
	}

	public NutritionInfoDTO getNutritionInfoDTO() {
		return nutritionInfoDTO;
	}

	public void setNutritionInfoDTO(NutritionInfoDTO nutritionInfoDTO) {
		this.nutritionInfoDTO = nutritionInfoDTO;
	}


}
