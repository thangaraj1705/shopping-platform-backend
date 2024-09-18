package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.learning.model.Admin;


@EnableJpaRepositories
@Repository
public interface AdminRepo extends JpaRepository<Admin,Long>{

	Optional<Admin> findByEmail(String email);
}
