package com.learning.service;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.learning.config.DatabaseException;
import com.learning.model.NutritionInfo;
import com.learning.repository.NutritionInfoRepo;

@Service
public class NutritionInfoService implements NutritionInfoImpl{

	private static final Logger log=LoggerFactory.getLogger(NutritionInfoService.class);
	
	@Autowired
	private NutritionInfoRepo nutritionInfoRepo;
	
	@Override
	public NutritionInfo saveNutrition(NutritionInfo nutritionInfo) {
		try {
			return	nutritionInfoRepo.save(nutritionInfo);
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
