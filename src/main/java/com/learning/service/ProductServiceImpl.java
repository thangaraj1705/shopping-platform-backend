package com.learning.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.model.Advertisement;
import com.learning.model.Product;

@Component
public interface ProductServiceImpl {
	
	public Product saveProduct(Product product,MultipartFile file) throws IOException;
	
	public void deleteProduct(String name);
	
	public List<Product> listAllProducts();
	
	public List<Product> searchProduct(String name);

	public int productCount();

	public Advertisement uploadAdvertisement(Advertisement advertisement, MultipartFile poster) throws IOException;

	public List<Advertisement> listOfAds();
	
	public String uniqueFileName(String filename);

}
