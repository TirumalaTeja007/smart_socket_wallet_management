package com.sana.avocado.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sana.avocado.dto.ApiResponse;
import com.sana.avocado.enums.TransactionStatusEnum;
import com.sana.avocado.enums.TransactionTypeEnum;
import com.sana.avocado.exception.EmptyResourceListException;
import com.sana.avocado.exception.ResourceCreationException;
import com.sana.avocado.exception.ResourceNotFoundException;
import com.sana.avocado.model.User;
import com.sana.avocado.model.UserTransactionsWallet;
import com.sana.avocado.model.UserWallet;
import com.sana.avocado.repository.UserTransactionsWalletRepository;
import com.sana.avocado.repository.UserWalletRepository;
import com.sana.avocado.service.IUserTransactionsWalletService;

@Service
public class UserTransactionsWalletService implements IUserTransactionsWalletService {
	
	private static Logger logger = LogManager.getLogger(UserTransactionsWalletService.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UserTransactionsWalletRepository userTransWalletRepo;
	
	@Autowired
	UserWalletRepository userWalletRepo;
	
	@Autowired
	UserWalletService userWalletService;

	@Override
	public Optional<UserTransactionsWallet> addUserWallet(@Valid UserTransactionsWallet userWallet) {
		
		UserTransactionsWallet newUserWallet = new UserTransactionsWallet();
		try {
			userWallet.setCreatedTime(new Date());
			userWallet.setTransactionType(userWallet.getTransactionType());
			if(userWallet.getTransactionStatus() == TransactionStatusEnum.SUCCESS) {
				if(userWallet.getTransactionType() == TransactionTypeEnum.CREDIT) {
					double addAmount = userWallet.getWalletAmount() + userWallet.getTransactionAmount();
					userWallet.setWalletAmount(addAmount);
				}else if(userWallet.getTransactionType() == TransactionTypeEnum.DEBIT) {
					double subAmount = userWallet.getWalletAmount() - userWallet.getTransactionAmount();
					userWallet.setWalletAmount(subAmount);
				}else if(userWallet.getTransactionType() == TransactionTypeEnum.ON_HOLD) {
					double holdAmount = userWallet.getWalletAmount() - userWallet.getOnHoldAmount();
					userWallet.setWalletAmount(holdAmount);
				}
			}else {
				throw new ResourceCreationException("UserWallet", "User Wallet credit is failed due to transaction status:: "+userWallet.getTransactionStatus());
			}
			
			newUserWallet = userTransWalletRepo.save(userWallet);
			
				Optional<UserWallet> user = userWalletService.getUserByUserName(userWallet.getUserName());
				logger.info("==================="+user);
				if(user.isPresent() == false) {
					UserWallet newWallet = new UserWallet();
					newWallet.setCreatedTime(new Date());
					newWallet.setUserName(newUserWallet.getUserName());
					newWallet.setTotalWalletAmount(newUserWallet.getWalletAmount());
					UserWallet wallet = userWalletRepo.save(newWallet);
				}else {
			
					userWalletService.getUserByUserName(newUserWallet.getUserName()).map(existingUserWallet -> {
						logger.info("Getting userName details if already exist."+existingUserWallet);
						existingUserWallet.setTotalWalletAmount(userWallet.getWalletAmount());
						userWalletService.updateUserWallet(existingUserWallet);
						return new ApiResponse("UserWallet updated Successfully.", true);
					});
			
				}
			
			return Optional.ofNullable(newUserWallet);
			
		}catch(Exception e) {
			throw new ResourceCreationException("User Wallet", "Failed to Create user Wallet.");
		}
	}
	
	
	


	@Override
	public Optional<UserTransactionsWallet> updateUserWallet(@Valid UserTransactionsWallet userWallet) {
		
		UserTransactionsWallet newUserWallet = new UserTransactionsWallet();
		try {
			if(userWallet.getTransactionStatus() == TransactionStatusEnum.SUCCESS) {
				userWallet.setUpdatedTime(new Date());
				userWallet.setTransactionType(userWallet.getTransactionType());
			
				if(userWallet.getTransactionType() == TransactionTypeEnum.CREDIT) {
					double addAmount = userWallet.getWalletAmount() + userWallet.getTransactionAmount();
					userWallet.setWalletAmount(addAmount);
				}else if(userWallet.getTransactionType() == TransactionTypeEnum.DEBIT) {
					double subAmount = userWallet.getWalletAmount() - userWallet.getTransactionAmount();
					userWallet.setWalletAmount(subAmount);
				}else if(userWallet.getTransactionType() == TransactionTypeEnum.ON_HOLD) {
					double holdAmount = userWallet.getWalletAmount() - userWallet.getOnHoldAmount();
					userWallet.setWalletAmount(holdAmount);
				}
			}else {
				throw new ResourceCreationException("UserWallet", "User Wallet credit is failed due to transaction status:: "+userWallet.getTransactionStatus());
			}
			
			newUserWallet = userTransWalletRepo.save(userWallet);
			return Optional.ofNullable(newUserWallet);
			
		}catch(Exception e) {
			throw new ResourceCreationException("User Wallet", "Failed to Update user Wallet.");
		}
	}


	@Override
	public Optional<List<UserTransactionsWallet>> getUserWallet() {
		try {
			return Optional.ofNullable(userTransWalletRepo.findAll());
		}catch(Exception e) {
			throw new EmptyResourceListException("No UserWallets are found.");
		}
		
	}
	
	
	@Override
	public User getUser(String userName) throws URISyntaxException {
		User user = null;
		String api = "http://smartsocket.axonifytech.com/apple/api/user/userName/"+userName;
		URI uri = new URI(api);
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(uri, User.class);
		User objects = responseEntity.getBody();
		return objects;
	}





	@Override
	public Optional<List<UserTransactionsWallet>> getUserByUserName(String userName) {
		try {
			return userTransWalletRepo.findByUserName(userName);
		} catch(Exception e) {
			throw new ResourceNotFoundException("UserName", userName, userName);
		}
		
	}


	@Override
	public Optional<List<UserTransactionsWallet>> getUserByTrans(String userName, TransactionTypeEnum transactionType) {
		try {
			return userTransWalletRepo.findByUserNameAndTransactionType(userName,transactionType);
		} catch(Exception e) {
			throw new ResourceNotFoundException("UserName", userName, userName);
		}
	}





	@Override
	public Optional<List<UserTransactionsWallet>> getUserByTranxStatus(String userName,
			TransactionStatusEnum transactionStatus) {
		try {
			return userTransWalletRepo.findByUserNameAndTransactionStatus(userName,transactionStatus);
		} catch(Exception e) {
			throw new ResourceNotFoundException("UserName", userName, userName);
		}
	}


	@Override
	public Optional<List<UserTransactionsWallet>> getUserByOrderId(String userName, String orderId) {
		try {
			return userTransWalletRepo.findByUserNameAndOrderId(userName,orderId);
		} catch(Exception e) {
			throw new ResourceNotFoundException("UserName", userName, userName);
		}
	}
	
}
