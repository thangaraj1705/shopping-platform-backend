package com.learning.dto;

import org.springframework.stereotype.Component;

@Component
public class ProductFeaturesDTO {


	private Long productFeatureId;

	private String flavour;

	private String productLife;

	private String storageInstructions;

	private String veg;

	private String nonVeg;

	public Long getProductFeatureId() {
		return productFeatureId;
	}

	public void setProductFeatureId(Long productFeatureId) {
		this.productFeatureId = productFeatureId;
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
