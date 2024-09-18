package com.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Master_Advertisement")
public class Advertisement {
	

	@Id
	@Column(name="S.NO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long advertisementId;
	
	@Column(name="PRODUCT_AD")
	private String productAd;
	
	@Column(name="POSTER")
	private String poster;
	
	@Column(name="DISCOUNT_PERCENTAGE")
	private double discountPercentage;


	public Long getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getProductAd() {
		return productAd;
	}

	public void setProductAd(String productAd) {
		this.productAd = productAd;
	}
	
	
}
