package com.learning.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.jwt.JwtUtils;
import com.learning.model.Advertisement;
import com.learning.model.Product;
import com.learning.model.ProductOffer;
import com.learning.service.AdvertisementServiceImpl;
import com.learning.service.ProductOfferServiceImpl;
import com.learning.service.ProductServiceImpl;
import com.learning.service.UserService;

@RestController
public class DashboardController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private	UserService userService;

	@Autowired
	private AdvertisementServiceImpl advertisementServiceImpl;
	
	@Autowired
	private ProductOfferServiceImpl productOfferServiceImpl;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/addproduct")
	public ResponseEntity<?> createProduct(@RequestParam("productName")String productName,
			@RequestParam("productDescription")String productDescription,
			@RequestParam("productPrice") Double productPrice,
			@RequestParam("productDiscount")Double productDiscount,
			@RequestParam ("file")MultipartFile file) throws IOException {

		Product product=new Product();
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductPrice(productPrice);
		product.setProductDiscount(productDiscount);
		product.setProductRating(0);
		Product create=productServiceImpl.saveProduct(product,file);

		return ResponseEntity.ok("Product Created!!!");
	}

	@GetMapping("/dashboard/statics")
	public Map<String, Integer> productCount() {
		Map<String,Integer> response=new HashMap<>();		
		int prodcount=productServiceImpl.productCount();
		int userscount=userService.usersCount();
		int adscount=advertisementServiceImpl.advertisementsCount();
		response.put("prodcount", prodcount);	
		response.put("userscount", userscount);	
		response.put("adscount", adscount);	
		return response;
	}

	@PostMapping("/uploadAd")
	public ResponseEntity<?> postAdvertisement(@RequestParam("productAd") String productAd,
			@RequestParam("discountPercentage")double discountPercentage,
			@RequestParam("poster")MultipartFile poster) throws IOException{

		Advertisement advertisement=new Advertisement();
		advertisement.setProductAd(productAd);
		advertisement.setDiscountPercentage(discountPercentage);
		Advertisement uploadAd=advertisementServiceImpl.uploadAdvertisement(advertisement,poster);

		return ResponseEntity.ok("Uploaded Advertisement!!!!");
	}
	
	@PostMapping("/uploadOffer")
	public ResponseEntity<?> postOffers(@RequestParam("offerProductName") String offerProductName,
			@RequestParam("fromAmount") double fromAmount,
			@RequestParam("offerPercentage") double offerPercentage,
			@RequestParam("offerImage") MultipartFile offerImage)  throws IOException{
		
		ProductOffer productOffer=new ProductOffer();
		productOffer.setOfferProductName(offerProductName);
		productOffer.setFromAmount(fromAmount);
		productOffer.setOfferPercentage(offerPercentage);
		ProductOffer uploadOffer=productOfferServiceImpl.uploadOffer(productOffer,offerImage);
		
		return ResponseEntity.ok("Uploaded Offers!!!!");
	}




}
