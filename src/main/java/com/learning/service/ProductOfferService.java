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


import com.learning.model.ProductOffer;
import com.learning.repository.ProductOfferRepo;

@Service
public class ProductOfferService implements ProductOfferServiceImpl{

	@Autowired
	private ProductOfferRepo productOfferRepo;
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Transactional
	@Override
	public ProductOffer uploadOffer(ProductOffer productOffer, MultipartFile offerImage) throws IOException{

		try {
			if(offerImage !=null && ! offerImage.isEmpty()) {

				String fileName=offerImage.getOriginalFilename();
				String directoryPath="D:/SpringReactjs/login-app/public/OfferPoster/";
				Path directory=Paths.get(directoryPath);
				if(! Files.exists(directory)) {
					Files.createDirectories(directory);
				}
				String uniqueFileName=productServiceImpl.uniqueFileName(fileName);
				Path filePath=directory.resolve(uniqueFileName);
				Files.write(filePath,offerImage.getBytes());
				productOffer.setOfferImage(uniqueFileName);
			}
			return	productOfferRepo.save(productOffer);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();

			throw new RuntimeException("Failed to save OfferPoster ", e);
		}
	}

	@Override
	public List<ProductOffer> productOfferList() {
		
		List<ProductOffer> offerlist=productOfferRepo.productOfferList();
		
		return offerlist;
	}

}
