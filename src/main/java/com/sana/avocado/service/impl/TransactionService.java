package com.sana.avocado.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.sana.avocado.enums.SessionStatusEnum;
import com.sana.avocado.enums.TransactionTypeEnum;
import com.sana.avocado.exception.ResourceRegistrationException;
import com.sana.avocado.exception.ResourceUpdationException;
import com.sana.avocado.model.Transactions;
import com.sana.avocado.model.User;
import com.sana.avocado.model.UserTransactionsWallet;
import com.sana.avocado.model.UserWallet;
import com.sana.avocado.repository.TransactionsRepository;
import com.sana.avocado.repository.UserTransactionsWalletRepository;
import com.sana.avocado.service.ITransactionService;
import com.sana.avocado.service.IUserTransactionsWalletService;
import com.sana.avocado.service.IUserWalletService;

@Service
public class TransactionService implements ITransactionService {
	
	private static Logger logger = LogManager.getLogger(TransactionService.class);
	
	@Autowired
	TransactionsRepository transRepo;
	
	@Autowired
	UserTransactionsWalletRepository userTransWalletRepo;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	IUserTransactionsWalletService userTransWalletService;
	
	@Value("${userApi}")
	private String userApi;

	@Override
	public Optional<Transactions> addTransaction(@Valid Transactions transactions) {
		
		Transactions newTransactions = new Transactions();
		try {
			transactions.setUserName(transactions.getUserName());
			transactions.setCreatedTime(new Date());
			
			
			newTransactions = transRepo.save(transactions);
			return Optional.ofNullable(newTransactions);
			
			
		}catch(Exception e) {
			throw new ResourceRegistrationException("Transactions", "Failed to Create Transactions.");
		}
	}

	@Override
	@Transactional
	public Optional<Transactions> updateTransaction(@Valid Transactions transactions, @RequestHeader(value="", required=false) double transactionAmount, @RequestHeader(value="", required=false) TransactionTypeEnum transactionType) {
		Transactions newTransactions = new Transactions();
		try {
						
			Optional<Transactions> transList = getTransactionByUserNameAndSessionId(transactions.getUserName(), transactions.getSessionId());
	  			
			transactions.setId(transList.get().getId());
			transactions.setCreatedTime(transList.get().getCreatedTime());
			transactions.setVersion(transList.get().getVersion());
			transactions.setCreatedBy(transList.get().getCreatedBy());
			transactions.setEvseId(transList.get().getEvseId());
			transactions.setStartDateTime(transList.get().getStartDateTime());
			transactions.setTimeLimit(transList.get().getTimeLimit());
			transactions.setSessionTypeEnum(transList.get().getSessionTypeEnum());
			
			transactions.setUpdatedTime(new Date());
			 
			newTransactions = transRepo.save(transactions);
						
			Optional<UserTransactionsWallet> userTransWallet =  userTransWalletService.getUserBySessionId(newTransactions.getUserName(), newTransactions.getSessionId());
			
			userTransWallet.get().setTransactionAmount(transactionAmount);
			userTransWallet.get().setTransactionType(transactionType);
			userTransWalletService.updateUserWallet(userTransWallet.get());
			
			return Optional.ofNullable(newTransactions);
			
		}catch(Exception e) {
			throw new ResourceUpdationException("Transactions", "Failed to Update the Transaction.");
		}
	}
	
	
	@Override
	public Optional<List<Transactions>> getAllTransactions() {
		return Optional.ofNullable(transRepo.findAll());
	}

	
	@Override
	public User getUser(String userName) throws URISyntaxException {
		User user = null;
		String api = userApi + userName;
		URI uri = new URI(api);
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(uri, User.class);
		User objects = responseEntity.getBody();
		return objects;
	}

	@Override
	public Optional<Transactions> getTransactionByUserNameAndSessionId(String userName, String sessionId) {
		return transRepo.findByUserNameAndSessionId(userName, sessionId);
	}

	@Override
	public Optional<List<Transactions>> getTransactionsByStatus(String userName, SessionStatusEnum sessionStatusEnum) {
		return transRepo.findByUserNameAndSessionStatusEnum(userName, sessionStatusEnum);
	}

	@Override
	public Optional<List<Transactions>> getCompletedTransactions(String userName, SessionStatusEnum sessionStatusEnum) {
		return transRepo.findByUserNameAndSessionStatusEnum(userName, sessionStatusEnum);
	}

	@Override
	public Optional<List<Transactions>> getAllTransactionsByUserName(String userName) {
		return transRepo.findByUserName(userName);
	}

	@Override
	public Optional<Transactions> updateMeterValues(@Valid Transactions transactions) {
		Transactions newTransactions = new Transactions();
		try {
			 
			Optional<Transactions> transList = getTransactionByUserNameAndSessionId(transactions.getUserName(), transactions.getSessionId());
			
			transactions.setId(transList.get().getId());
			transactions.setCreatedTime(transList.get().getCreatedTime());
			transactions.setVersion(transList.get().getVersion());
			transactions.setCreatedBy(transList.get().getCreatedBy());
			transactions.setEvseId(transList.get().getEvseId());
			transactions.setStartDateTime(transList.get().getStartDateTime());
			transactions.setTimeLimit(transList.get().getTimeLimit());
			transactions.setSessionTypeEnum(transList.get().getSessionTypeEnum());
			
			transactions.setUpdatedTime(new Date());			
			 
			newTransactions = transRepo.save(transactions);
			
			return Optional.ofNullable(newTransactions);
			
		}catch(Exception e) {
			throw new ResourceUpdationException("Transactions", "Failed to Update the Transaction.");
	   }
	}

	@Override
	public Optional<List<Transactions>> getAllTransDates(String userName, Date startDate, Date endDate) {
		return transRepo.findByUserNameAndCreatedTimeBetween(userName, startDate, endDate);
	}

	@Override
	public Optional<List<Transactions>> getTransactionsBySessionStatusAndDateRange(@Valid String userName,
			SessionStatusEnum sessionStatusEnum, Date startDate, Date endDate) {
		return transRepo.findByUserNameAndCreatedTimeBetweenAndSessionStatusEnum(userName, startDate, endDate, sessionStatusEnum);
	}

	@Override
	public Optional<List<Transactions>> getPendingTransaction(String userName, SessionStatusEnum sessionStatusEnum) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
