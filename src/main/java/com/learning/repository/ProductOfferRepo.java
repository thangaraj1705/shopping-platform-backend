package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.learning.model.ProductOffer;


@EnableJpaRepositories
@Repository
public interface ProductOfferRepo extends JpaRepository<ProductOffer,Long>{

	@Query("SELECT p from ProductOffer p order by offerId asc")
	public List<ProductOffer> productOfferList();
	
}
