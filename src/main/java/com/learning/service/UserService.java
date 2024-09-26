package com.learning.service;

import java.util.Date;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.config.DatabaseException;
import com.learning.config.NoDataFoundException;
import com.learning.model.SignUp;
import com.learning.repository.UserRepository;

@Service
public class UserService {

	public static final int MAX_FAILED_ATTEMPT=3;
	public static final Long LOCK_TIME_DURATION=60000l;
	public static final String msg="Your account has been locked due to 3 failed attempts. It will be unlocked after 1 min";

	private static final Logger log=LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder  passwordEncoder;

	public SignUp findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}
	public SignUp findByEmailAddress(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	private SignUp findByUsernameOrEmail(String usernameOrEmail) {
		return userRepository.findByUsernameOrEmail(usernameOrEmail);
	}

	@Transactional
	public SignUp save(SignUp signUp) {
		signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
		signUp.setFailedAttempt(0);
		signUp.setLockFlag('N');
		return userRepository.save(signUp);
	}

	@Transactional
	public void increaseFailedAttempt(SignUp signUp) {
		Integer failureAttempt = (signUp.getFailedAttempt() != null ? signUp.getFailedAttempt() : 0) + 1;
		userRepository.updateFailedAttempt(failureAttempt, signUp.getUsername());

	}

	@Transactional
	public void loginLock(SignUp signUp) {
		signUp.setLockTime(new Date());
		signUp.setLockFlag('Y');
		userRepository.save(signUp);

	}

	@Transactional
	public SignUp unlockTimeExpired(SignUp signUp) {

		if(signUp !=null && signUp.getLockTime() !=null) {
			long lockTimeMillis=(signUp.getLockTime() !=null ? signUp.getLockTime().getTime(): 0);
			long currentTimeMillis=System.currentTimeMillis();
			if(lockTimeMillis + LOCK_TIME_DURATION <currentTimeMillis && signUp.getLockFlag()=='Y') {
				signUp.setFailedAttempt(0);
				signUp.setLockFlag('N');
				signUp.setLockTime(null);
				userRepository.save(signUp);
				return signUp;
			}}
		return signUp;
	}

	public void authenticationFailure(SignUp signUp) {
		int failedAttempt=signUp.getFailedAttempt() !=null? signUp.getFailedAttempt() : 0;

		if(failedAttempt<MAX_FAILED_ATTEMPT-1) {
			increaseFailedAttempt(signUp);		
		}
		else {
			loginLock(signUp);
		}

	}

	public SignUp existingUserDetails(String usernameOrEmail) {
		SignUp existingUser=findByUsernameOrEmail(usernameOrEmail);

		return existingUser;
	}

	@Transactional
	public boolean authenticate(String usernameOrEmail,String password) {
		SignUp signUp=existingUserDetails(usernameOrEmail);

		if(signUp !=null) {
			Boolean passwordMatch=false;
			passwordMatch=passwordEncoder.matches(password, signUp.getPassword());
			if(passwordMatch.booleanValue())
			{
				signUp.setFailedAttempt(0);
				signUp.setLockFlag('N');
				signUp.setLockTime(null);
				userRepository.save(signUp);
				return true;
			}
			else {
				authenticationFailure(signUp);
			}
		}

		return false;
	}

	public int usersCount() {
		int userscount=userRepository.usersCount();

		return userscount;
	}

	public List<SignUp> listOfUser() {

		try {
			List<SignUp> userDetails=userRepository.listOfUsers();

			if(userDetails==null) {
				throw new NoDataFoundException("No user found in Database");
			}
			return userDetails;
		}
		catch(DataAccessException e) {
			log.error("Error accessing the database : ",e.getMessage(),e);
			throw new DatabaseException("Error accessing the database",e);
		}
		catch(Exception e) {
			log.error("Unexcepted error: ",e.getMessage(),e);
			throw new ServiceException("An unexcepted error occurred",e);
		}

	}

	public void deleteUserId(Long userId) {

		try {
			userRepository.deleteUser(userId);
		}
		catch(DataAccessException e) {
			log.error("Error accessing the database : ",e.getMessage(),e);
			throw new DatabaseException("Error accessing the database",e);
		}
		catch(Exception e) {
			log.error("Unexcepted error: ",e.getMessage(),e);
			throw new ServiceException("An unexcepted error occurred",e);
		}

	}


}
