package com.learning.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.dto.ProductRequestDTO;
import com.learning.jwt.JwtUtils;
import com.learning.model.Advertisement;
import com.learning.model.NutritionInfo;
import com.learning.model.Product;
import com.learning.model.ProductFeatures;
import com.learning.model.ProductOffer;
import com.learning.service.AdvertisementServiceImpl;
import com.learning.service.NutritionInfoImpl;
import com.learning.service.ProductFeatureServiceImpl;
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
	private NutritionInfoImpl nutritionInfoImpl;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired	
	private	ProductFeatureServiceImpl ProductFeatureServiceImpl;
	
	@Autowired
	private ProductRequestDTO productRequestDTO;

	@PostMapping("/addproduct")
	public ResponseEntity<String> addProduct(
	        @RequestPart("ProductRequestDTO") ProductRequestDTO productRequestDTO,
	        @RequestPart(value = "file", required = false) MultipartFile file) throws IOException{

		Product product=new Product();
		product.setProductName(productRequestDTO.getProductName());
		product.setProductDescription(productRequestDTO.getProductDescription());
		product.setProductPrice(productRequestDTO.getProductPrice());
		product.setProductDiscount(productRequestDTO.getProductDiscount());
		product.setQuantityInStock(productRequestDTO.getQuantityInStock());
		product.setProductRating(0);

		ProductFeatures productFeatures=new ProductFeatures();
		productFeatures.setFlavour(productRequestDTO.getProductFeaturesDTO().getFlavour());
		productFeatures.setProductLife(productRequestDTO.getProductFeaturesDTO().getProductLife());
		productFeatures.setStorageInstructions(productRequestDTO.getProductFeaturesDTO().getStorageInstructions());
		productFeatures.setVeg(productRequestDTO.getProductFeaturesDTO().getVeg());
		productFeatures.setNonVeg(productRequestDTO.getProductFeaturesDTO().getNonVeg());
		productFeatures=ProductFeatureServiceImpl.saveProductFeature(productFeatures);

		NutritionInfo nutritionInfo=new NutritionInfo();
		nutritionInfo.setCalories(productRequestDTO.getNutritionInfoDTO().getCalories());
		nutritionInfo.setCarbohydrates(productRequestDTO.getNutritionInfoDTO().getCarbohydrates());
		nutritionInfo.setFats(productRequestDTO.getNutritionInfoDTO().getFats());
		nutritionInfo.setProteins(productRequestDTO.getNutritionInfoDTO().getProteins());
		nutritionInfo.setSugar(productRequestDTO.getNutritionInfoDTO().getSugar());
		nutritionInfo=nutritionInfoImpl.saveNutrition(nutritionInfo);


		product.setProductFeatures(productFeatures);
		product.setNutritionInfo(nutritionInfo);		

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

	@GetMapping("/populate-product/{productName}")
	public ResponseEntity<Product> updateProductDetails( @RequestParam("productId") Long productId,
			@PathVariable("productName") String productName){

		try {
			Product updateProductDetails=productServiceImpl.findByProductIdAndProductName(productId, productName);
			if(updateProductDetails==null) {
				return ResponseEntity.notFound().build();   //204  data not found
			}
			return ResponseEntity.ok(updateProductDetails);  //200 data found

		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();   //500 server error
		}
	}

	@PutMapping("/update-product/{productName}")
	public ResponseEntity<String> updateProduct(
	        @RequestParam("productId") Long productId,
	        @PathVariable("productName") String productName,
	        @RequestPart("ProductRequestDTO") ProductRequestDTO productRequestDTO,
	        @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

	    System.out.println("Update product API called");

	    Product product = productServiceImpl.findByProductIdAndProductName(productId, productName);

	    if (product == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	    }
	    product.setProductName(productRequestDTO.getProductName());
		product.setProductDescription(productRequestDTO.getProductDescription());
		product.setProductPrice(productRequestDTO.getProductPrice());
		product.setProductDiscount(productRequestDTO.getProductDiscount());
		product.setQuantityInStock(productRequestDTO.getQuantityInStock());

	    ProductFeatures productFeatures = product.getProductFeatures() != null ? product.getProductFeatures() : new ProductFeatures();
	    if (productRequestDTO.getProductFeaturesDTO() != null) {
	    	productFeatures.setFlavour(productRequestDTO.getProductFeaturesDTO().getFlavour());
			productFeatures.setProductLife(productRequestDTO.getProductFeaturesDTO().getProductLife());
			productFeatures.setStorageInstructions(productRequestDTO.getProductFeaturesDTO().getStorageInstructions());
			productFeatures.setVeg(productRequestDTO.getProductFeaturesDTO().getVeg());
			productFeatures.setNonVeg(productRequestDTO.getProductFeaturesDTO().getNonVeg());
	        productFeatures = ProductFeatureServiceImpl.saveProductFeature(productFeatures);
	        product.setProductFeatures(productFeatures);
	    }

	    NutritionInfo nutritionInfo = product.getNutritionInfo() != null ? product.getNutritionInfo() : new NutritionInfo();
	    if (productRequestDTO.getNutritionInfoDTO() != null) {
	    	nutritionInfo.setCalories(productRequestDTO.getNutritionInfoDTO().getCalories());
			nutritionInfo.setCarbohydrates(productRequestDTO.getNutritionInfoDTO().getCarbohydrates());
			nutritionInfo.setFats(productRequestDTO.getNutritionInfoDTO().getFats());
			nutritionInfo.setProteins(productRequestDTO.getNutritionInfoDTO().getProteins());
			nutritionInfo.setSugar(productRequestDTO.getNutritionInfoDTO().getSugar());
	        nutritionInfo = nutritionInfoImpl.saveNutrition(nutritionInfo);
	        product.setNutritionInfo(nutritionInfo);
	    }

	    Product updatedProduct = productServiceImpl.saveProduct(product, file);

	    if (updatedProduct == null) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product");
	    }

	    return ResponseEntity.ok("Product updated successfully!");
	}


	@GetMapping("/update-ad/{productAd}")
	public ResponseEntity<Advertisement> updateAdProductDetails( @RequestParam("advertisementId") Long advertisementId,
			@PathVariable("productAd") String productAd){

		try {
			Advertisement updateAdProductDetails=advertisementServiceImpl.findByAdvertisementIdAndProductAdName(advertisementId, productAd);
			if(updateAdProductDetails==null) {
				return ResponseEntity.notFound().build();   //204  data not found
			}
			return ResponseEntity.ok(updateAdProductDetails);  //200 data found

		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();   //500 server error
		}
	}

	@PutMapping("/update-ad/{productAd}")
	public ResponseEntity<String> updateAdvertisement(@RequestPart("Advertisement") Advertisement advertisement, 
	        @RequestParam("advertisementId") Long advertisementId,
	        @PathVariable("productAd") String productAd,
	        @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

		Advertisement updateAdProductDetails=advertisementServiceImpl.findByAdvertisementIdAndProductAdName(advertisementId, productAd);
		if(updateAdProductDetails==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Advertisement Product not found");
		}
		
		updateAdProductDetails.setProductAd(advertisement.getProductAd());
		updateAdProductDetails.setDiscountPercentage(advertisement.getDiscountPercentage());
		Advertisement uploadAd=advertisementServiceImpl.uploadAdvertisement(advertisement,file);

		return ResponseEntity.ok("Uploaded Advertisement!!!!");

	}
	
	
	
}
