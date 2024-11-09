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
import com.learning.repository.AdvertisementRepo;

@Service
public class AdvertisementService implements AdvertisementServiceImpl{
	
	private static final Logger log=LoggerFactory.getLogger(AdvertisementService.class);


	@Autowired
	private	AdvertisementRepo advertisementRepo;
	
	@Autowired
	private ProductServiceImpl productServiceImpl;



	@Override
	public int advertisementsCount() {
		int adsCount=advertisementRepo.advertisementCount();
		return adsCount;
	}

	@Override
	public List<Advertisement> listOfAds() {

		try {
			List<Advertisement> advertisementList=advertisementRepo.listOfAds();

			if(advertisementList==null) {
				throw new NoDataFoundException("No user found in Database");
			}
			return advertisementList;
		}
		catch(DataAccessException e) {
			log.error("Error accessing the database : ",e.getMessage(),e);
			throw new DatabaseException("Error accessing the database",e);
		}
		catch(Exception e) {
			log.error("Unexcepted error: ",e.getMessage(),e);
			throw new ServiceException("An unexcepted error occurred",e);
		}

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
				String uniqueFileName=productServiceImpl.uniqueFileName(fileName);
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
	public void deleteAdvertisement(Long advertisementId) {

		try {
			advertisementRepo.deleteAdvertisement(advertisementId);
		}
		catch(DataAccessException e) {
			log.error("Error accessing the database : ",e.getMessage(),e);
			throw new DatabaseException("Error accessing the database",e);
		}
		catch(Exception e) {
			log.error("Unexcepted error: ",e.getMessage(),e);
			throw new ServiceException("An unexcepted error occurred",e);
		}

	}

	@Override
	public Advertisement findByAdvertisementIdAndProductAdName(Long advertisementId, String productAd) {
		try {
			Advertisement adProductDetails=advertisementRepo.findByAdvertisementIdAndProductAdName(advertisementId, productAd);

				if(adProductDetails==null) {
					throw new NoDataFoundException("No user found in Database");
				}
				return adProductDetails;
			}
			catch(DataAccessException e) {
				log.error("Error accessing the database : ",e.getMessage(),e);
				throw new DatabaseException("Error accessing the database",e);
			}
			catch(Exception e) {
				log.error("Unexcepted error: ",e.getMessage(),e);
				throw new ServiceException("An unexcepted error occurred",e);
			}
	}


}
