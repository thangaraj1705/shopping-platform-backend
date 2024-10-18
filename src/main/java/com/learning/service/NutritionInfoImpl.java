package com.learning.service;

import org.springframework.stereotype.Component;

import com.learning.model.NutritionInfo;
import com.learning.model.ProductFeatures;

@Component
public interface NutritionInfoImpl {
	
	public NutritionInfo saveNutrition(NutritionInfo nutritionInfo);

}
