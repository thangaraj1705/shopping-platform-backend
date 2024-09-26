package com.learning.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.jwt.JwtUtils;
import com.learning.model.Advertisement;
import com.learning.model.Product;
import com.learning.model.ProductOffer;
import com.learning.service.AdvertisementServiceImpl;
import com.learning.service.ProductOfferServiceImpl;
import com.learning.service.ProductServiceImpl;
import com.learning.service.UserService;


@RestController
public class ProductController {

	private static final Logger log=LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	ProductOfferServiceImpl productOfferServiceImpl;	
	
	@Autowired
	AdvertisementServiceImpl advertisementServiceImpl;

	@Autowired
	JwtUtils jwtUtils;

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProduct(@RequestParam("productId") Long productId){
		
		if (productId ==null || productId <=0) {
			return ResponseEntity.ok("Invalid product !!!~!");
		}

		productServiceImpl.deleteProduct(productId);

		return ResponseEntity.ok("Product has been deleted");

	}

	@GetMapping("/listProducts")
	public List<Product> listOfProducts () {

		return	productServiceImpl.listAllProducts();

	}
	
	@GetMapping("/offerProducts")
	public List<ProductOffer> ListOfOffers(){
		
		return productOfferServiceImpl.productOfferList();
	}

	@GetMapping("/search")
	public List<Product> searchProduct(@RequestParam(name="name") String name) {
		List<Product> products= productServiceImpl.searchProduct(name);
		return  products;

	} 
	
	@GetMapping("/filter-offers-by-starting-price")
	public List<Product> filterOffersByStartingPrice(@RequestParam("productName") String productName,
			@RequestParam("productPrice") double productPrice){
		
		List<Product> products= productServiceImpl.filterProducts(productName,productPrice);
		
				return products;		
	}
	
	@GetMapping("/advertisements")
	public ResponseEntity<List<Advertisement>> listOfAds(){

		try {
			List<Advertisement> adDetails= advertisementServiceImpl.listOfAds();
			if(adDetails.isEmpty()) {
				return ResponseEntity.noContent().build();     //204  data not found
			}
			return ResponseEntity.ok(adDetails);  //200 data found

		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();   //500 server error
		}

	}
	
	@DeleteMapping("/ads/{advertisementId}")
	public ResponseEntity<?> deleteAdvertisement(@PathVariable Long advertisementId){
		
		log.info("Deleting advertisement with ID: " + advertisementId);
		try {			
			
			advertisementServiceImpl.deleteAdvertisement(advertisementId);
			return ResponseEntity.ok("Advertisement has been deleted successfully!!!!!!");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();    //500 server error
		}
		
	}




}
