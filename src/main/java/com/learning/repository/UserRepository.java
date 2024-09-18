package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learning.model.SignUp;



@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<SignUp,Long> {

	Optional<SignUp> findByUsername(String userName);

	Optional<SignUp> findByEmail(String email);

	@Query("SELECT u FROM SignUp u WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail")
	SignUp findByUsernameOrEmail(String usernameOrEmail);


	@Modifying
	@Transactional
	@Query("UPDATE SignUp u SET u.failedAttempt =:failedAttempt WHERE u.username =:username")
	public void updateFailedAttempt(int failedAttempt,String username);
	

	@Query("SELECT count(*) from SignUp p")
	public int usersCount();

}
