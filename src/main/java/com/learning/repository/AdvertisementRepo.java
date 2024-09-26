package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learning.model.Advertisement;

@EnableJpaRepositories
@Repository
public interface AdvertisementRepo extends JpaRepository<Advertisement,Long> {

	@Query("SELECT a from Advertisement a order by advertisementId asc")
	public List<Advertisement> listOfAds();
	
	
	@Query("SELECT count(*) from Advertisement a")
	public int advertisementCount();
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Advertisement a WHERE a.advertisementId=:advertisementId")
	public void deleteAdvertisement(Long advertisementId);

}
