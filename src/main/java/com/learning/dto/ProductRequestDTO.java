package com.learning.dto;

import org.springframework.stereotype.Component;

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
	
	private ProductFeatures productFeatures;
	
	private NutritionInfo nutritionInfo;
	
	private String calories;
	
	private String fats;
	
	private String proteins;
	
	private String carbohydrates;
	
	private String sugar;
	
	private String flavour;
	
	private String productLife;
	
	private String storageInstructions;
	
	private String veg;
	
	private String nonVeg;

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

	public ProductFeatures getProductFeatures() {
		return productFeatures;
	}

	public void setProductFeatures(ProductFeatures productFeatures) {
		this.productFeatures = productFeatures;
	}

	public NutritionInfo getNutritionInfo() {
		return nutritionInfo;
	}

	public void setNutritionInfo(NutritionInfo nutritionInfo) {
		this.nutritionInfo = nutritionInfo;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getFats() {
		return fats;
	}

	public void setFats(String fats) {
		this.fats = fats;
	}

	public String getProteins() {
		return proteins;
	}

	public void setProteins(String proteins) {
		this.proteins = proteins;
	}

	public String getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(String carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public String getProductLife() {
		return productLife;
	}

	public void setProductLife(String productLife) {
		this.productLife = productLife;
	}

	public String getStorageInstructions() {
		return storageInstructions;
	}

	public void setStorageInstructions(String storageInstructions) {
		this.storageInstructions = storageInstructions;
	}

	public String getVeg() {
		return veg;
	}

	public void setVeg(String veg) {
		this.veg = veg;
	}

	public String getNonVeg() {
		return nonVeg;
	}

	public void setNonVeg(String nonVeg) {
		this.nonVeg = nonVeg;
	}
	
	

}
