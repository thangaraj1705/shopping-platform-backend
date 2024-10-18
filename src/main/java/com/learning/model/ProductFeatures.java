package com.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCT_FEATURE")
public class ProductFeatures {

	
	@Id
	@Column(name="PRODUCT_FEATURE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long productFeatureId;
	
	@Column(name="FLAVOUR")
	private String flavour;
	
	@Column(name="PRODUCT_LIFE")
	private String productLife;
	
	@Column(name="STORAGE_INSTRUCTIONS")
	private String storageInstructions;
	
	@Column(name="VEG")
	private String veg;
	
	@Column(name="NON_VEG")
	private String nonVeg;

	public Long getProductFeatureId() {
		return productFeatureId;
	}

	public void setProductFeatureId(Long productFeatureId) {
		this.productFeatureId = productFeatureId;
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

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	

	
}
