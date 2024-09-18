package com.learning.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.learning.model.Advertisement;
import com.learning.model.ProductOffer;

@Component
public interface ProductOfferServiceImpl {


	public ProductOffer uploadOffer(ProductOffer productOffer, MultipartFile offerImage) throws IOException;
	
	
	public List<ProductOffer> productOfferList();

	
	
	

}
