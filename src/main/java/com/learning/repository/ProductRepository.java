package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learning.model.Advertisement;
import com.learning.model.Product;

@EnableJpaRepositories
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

	@Modifying
	@Transactional
	@Query("DELETE FROM Product p WHERE p.productId =:productId")
	public void deleteByProductName(Long productId);

	@Query("SELECT p from Product p order by productId desc")
	public List<Product> listAllProducts();
	
	@Query("SELECT count(*) from Product p")
	public int productCount();	
	
	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
	public List<Product> searchProduct(String productName);

	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName% AND p.productPrice>=:productPrice")
	public List<Product> filterProducts(String productName,double productPrice);
	
	@Query("SELECT p FROM Product p "+"LEFT JOIN FETCH p.productFeatures "+"LEFT JOIN FETCH p.nutritionInfo "+"WHERE p.productId=:productId AND p.productName=:productName")
	public Product findByProductIdAndProductName(Long productId, String productName);

}
