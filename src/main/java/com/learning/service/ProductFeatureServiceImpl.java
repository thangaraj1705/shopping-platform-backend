package com.learning.service;

import org.springframework.stereotype.Component;

import com.learning.model.Product;
import com.learning.model.ProductFeatures;

@Component
public interface ProductFeatureServiceImpl {

	
	public ProductFeatures saveProductFeature(ProductFeatures productFeatures);
}
