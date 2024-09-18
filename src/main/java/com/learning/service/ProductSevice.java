package com.learning.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.learning.model.Advertisement;
import com.learning.model.Product;
import com.learning.repository.AdvertisementRepo;
import com.learning.repository.ProductRepository;



@Service
public class ProductSevice implements ProductServiceImpl{

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
				String directoryPath="D:/SpringReactjs/login-app/src/img/";
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


	@Transactional
	@Override
	public Advertisement uploadAdvertisement(Advertisement advertisement, MultipartFile poster)throws IOException {

		try {
			if(poster !=null && ! poster.isEmpty()) {

				String fileName=poster.getOriginalFilename();
				String directoryPath="D:/SpringReactjs/login-app/public/AdvertisementPoster/";
				Path directory=Paths.get(directoryPath);
				if(! Files.exists(directory)) {
					Files.createDirectories(directory);
				}
				String uniqueFileName=uniqueFileName(fileName);
				Path filePath=directory.resolve(uniqueFileName);
				Files.write(filePath,poster.getBytes());
				advertisement.setPoster(uniqueFileName.toString());
			}
			return	advertisementRepo.save(advertisement);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();

			throw new RuntimeException("Failed to save Advertisement ", e);
		}
	}


	@Override
	public void deleteProduct(String name) {

		productRepository.deleteByProductName(name);

	}

	@Override
	public List<Product> listAllProducts() {
		List<Product> list=productRepository.listAllProducts();
		return list;
	}

	@Override
	public List<Advertisement> listOfAds() {
		List<Advertisement> list=advertisementRepo.listOfAds();	
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


}
