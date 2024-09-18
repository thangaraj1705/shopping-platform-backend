package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learning.model.Advertisement;

@EnableJpaRepositories
@Repository
public interface AdvertisementRepo extends JpaRepository<Advertisement,Long> {

	@Query("SELECT p from Advertisement p order by advertisementId asc")
	public List<Advertisement> listOfAds();
	
	
	@Query("SELECT count(*) from Advertisement p")
	public int advertisementCount();

}
