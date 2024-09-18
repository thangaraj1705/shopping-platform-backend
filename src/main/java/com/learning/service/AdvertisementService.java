package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.repository.AdvertisementRepo;

@Service
public class AdvertisementService implements AdvertisementServiceImpl{

	@Autowired
	private	AdvertisementRepo advertisementRepo;



	@Override
	public int advertisementsCount() {
		int adsCount=advertisementRepo.advertisementCount();
		return adsCount;
	}




}
