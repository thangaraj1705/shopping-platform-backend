package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.learning.model.ProductFeatures;

@EnableJpaRepositories
@Repository
public interface ProductFeatureRepo extends JpaRepository<ProductFeatures,Long>{

}
