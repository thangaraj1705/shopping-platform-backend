package com.learning.service;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.learning.config.DatabaseException;
import com.learning.model.ProductFeatures;
import com.learning.repository.ProductFeatureRepo;

@Service
public class ProductFeatureService implements ProductFeatureServiceImpl{
	
	private static final Logger log=LoggerFactory.getLogger(ProductFeatureService.class);

	@Autowired
	private ProductFeatureRepo productFeatureRepo;
	
	
	@Override
	public ProductFeatures saveProductFeature(ProductFeatures productFeatures) {
		try {
		return	productFeatureRepo.save(productFeatures);
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
