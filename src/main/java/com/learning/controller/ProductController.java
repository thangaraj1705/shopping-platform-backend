package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.jwt.JwtUtils;
import com.learning.model.Advertisement;
import com.learning.model.Product;
import com.learning.model.ProductOffer;
import com.learning.service.ProductOfferServiceImpl;
import com.learning.service.ProductServiceImpl;


@RestController
public class ProductController {


	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	ProductOfferServiceImpl productOfferServiceImpl;

	@Autowired
	JwtUtils jwtUtils;

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProduct(String name){

		productServiceImpl.deleteProduct(name);

		return ResponseEntity.ok("Product has been deleted");

	}

//	@PutMapping("/update")
//	public ResponseEntity<?> updateProduct (@RequestBody Product product){
//
//		Product update=productServiceImpl.saveProduct(product);
//
//		return ResponseEntity.ok("Product updated");
//	}

	@GetMapping("/listProducts")
	public List<Product> listOfProducts () {

		return	productServiceImpl.listAllProducts();

	}
	
	@GetMapping("/advertisements")
	public List<Advertisement> listOfAds(){
		
		return productServiceImpl.listOfAds();
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
	
	




}
