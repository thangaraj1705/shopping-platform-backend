package com.learning.service;

import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.learning.config.DatabaseException;
import com.learning.config.NoDataFoundException;
import com.learning.dto.CartRequestDTO;
import com.learning.model.Product;
import com.learning.model.ProductCart;
import com.learning.model.SignUp;
import com.learning.repository.CartRepository;
import com.learning.repository.ProductRepository;
import com.learning.repository.UserRepository;

@Service
public class CartService implements CartServiceImpl{

	private static final Logger log=LoggerFactory.getLogger(CartService.class);


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public void addItemToCart(CartRequestDTO cartRequestDTO, Long userId) {

		try {
			SignUp signUpObj=userRepository.findByUserId(userId);

			Product productObj=productRepository.findByProductId(cartRequestDTO.getProductId());

			if(signUpObj==null && productObj==null ) {
				throw new NoDataFoundException("No data found in Database");
			}

			ProductCart existingCartItem=cartRepository.findByUserAndProduct(productObj, signUpObj);

			if(existingCartItem != null && existingCartItem.getItemQuantity()!=null) {

				existingCartItem.setItemQuantity(existingCartItem.getItemQuantity()+cartRequestDTO.getItemQuantity());
				cartRepository.save(existingCartItem);
			}
			else {
				ProductCart productCartObj=new ProductCart(productObj,cartRequestDTO.getItemQuantity(),signUpObj);

				cartRepository.save(productCartObj);

			}

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
