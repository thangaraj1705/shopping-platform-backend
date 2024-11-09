package com.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.learning.model.Product;
import com.learning.model.ProductCart;
import com.learning.model.SignUp;

@EnableJpaRepositories
@Repository
public interface CartRepository extends JpaRepository<ProductCart,Long>{
	
	
	@Query("SELECT c FROM ProductCart c WHERE c.product=:product AND c.signUp=:signUp")
	public ProductCart findByUserAndProduct(Product product,SignUp signUp);

}
