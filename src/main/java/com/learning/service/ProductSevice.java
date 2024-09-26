package com.learning.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.learning.config.DatabaseException;
import com.learning.config.NoDataFoundException;
import com.learning.model.Advertisement;
import com.learning.model.Product;
import com.learning.model.SignUp;
import com.learning.repository.AdvertisementRepo;
import com.learning.repository.ProductRepository;



@Service
public class ProductSevice implements ProductServiceImpl{
	
	private static final Logger log=LoggerFactory.getLogger(ProductSevice.class);

	@Autowired
	private	ProductRepository productRepository;

	@Autowired
	private	AdvertisementRepo advertisementRepo;


	@Transactional
	@Override
	public Product saveProduct(Product product, MultipartFile file) throws IOException {
		try {
			if(file !=null && ! file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String directoryPath="D:/SpringReactjs/login-app/public/ProductsImg/";
				Path directory = Paths.get(directoryPath);
				if (!Files.exists(directory)) {
					Files.createDirectories(directory);
				}

				String uniqueFileName=uniqueFileName(fileName);
				Path filePath = directory.resolve(uniqueFileName);
				Files.write(filePath, file.getBytes());
				product.setProductImgPath(uniqueFileName.toString());
			}

			return	productRepository.save(product);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();

			throw new RuntimeException("Failed to save product", e);
		}
	}

	@Override
	public String uniqueFileName(String filename) {

		int dotLength=filename.lastIndexOf(".");
		String postername=filename.substring(0,dotLength);
		String extension=filename.substring(dotLength,filename.length());
		String uniqueFileName=postername+"_"+System.currentTimeMillis()+extension;

		return uniqueFileName;
	}


	@Override
	public void deleteProduct(Long productId) {

		productRepository.deleteByProductName(productId);

	}

	@Override
	public List<Product> listAllProducts() {
		List<Product> list=productRepository.listAllProducts();
		return list;
	}
	


	@Override
	public List<Product> searchProduct(String name) {
		List<Product> list=(List<Product>) productRepository.searchProduct(name);

		return list;
	}

	@Override
	public int productCount() {
		int count=productRepository.productCount();

		return count;
	}

	@Override
	public List<Product> filterProducts(String productName, double productPrice) {

   List<Product> filteredProducts=productRepository.filterProducts(productName,productPrice);

		return filteredProducts;
	}


}
