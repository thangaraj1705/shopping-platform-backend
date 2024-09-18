package com.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Master_Offer")
public class ProductOffer {
	
	@Id
	@Column(name="S.NO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long offerId;
	
	@Column(name="OFFER_PRODUCTNAME",nullable=false)
	private String offerProductName;
	
	@Column(name="FROM_AMOUNT")
	private double fromAmount;
	
	@Column(name="OFFER_PERCENTAGE")
	private double offerPercentage;
	
	@Column(name="OFFER_IMAGE")
	private String offerImage;

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public String getOfferProductName() {
		return offerProductName;
	}

	public void setOfferProductName(String offerProductName) {
		this.offerProductName = offerProductName;
	}

	public double getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(double fromAmount) {
		this.fromAmount = fromAmount;
	}

	public double getOfferPercentage() {
		return offerPercentage;
	}

	public void setOfferPercentage(double offerPercentage) {
		this.offerPercentage = offerPercentage;
	}

	public String getOfferImage() {
		return offerImage;
	}

	public void setOfferImage(String offerImage) {
		this.offerImage = offerImage;
	}
		

}
