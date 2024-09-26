package com.learning.service;

import java.io.IOException;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.learning.config.DatabaseException;
import com.learning.model.Advertisement;

@Component
public interface AdvertisementServiceImpl {
	
	public int advertisementsCount();
	
	public Advertisement uploadAdvertisement(Advertisement advertisement, MultipartFile poster) throws IOException;

	public List<Advertisement> listOfAds();

	public void deleteAdvertisement(Long advertisementId);

	
}
