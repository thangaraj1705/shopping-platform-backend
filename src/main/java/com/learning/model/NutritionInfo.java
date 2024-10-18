package com.learning.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="NUTRITION_INFO")
public class NutritionInfo {
	
	@Id
	@Column(name="NUTRITION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long nutritionId;
	
	@Column(name="CALORIES")
	private String calories;
	
	@Column(name="FATS")
	private String fats;
	
	@Column(name="PROTEINS")
	private String proteins;
	
	@Column(name="CARBOHYDRATES")
	private String carbohydrates;
	
	@Column(name="SUGAR")
	private String sugar;

	public Long getNutritionId() {
		return nutritionId;
	}

	public void setNutritionId(Long nutritionId) {
		this.nutritionId = nutritionId;
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


	

}
