package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.learning.model.NutritionInfo;

@EnableJpaRepositories
@Repository
public interface NutritionInfoRepo extends JpaRepository<NutritionInfo,Long>{

}
