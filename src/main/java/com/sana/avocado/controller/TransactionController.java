package com.sana.avocado.controller;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sana.avocado.model.User;
import com.sana.avocado.model.UserTransactionsWallet;
import com.sana.avocado.dto.ApiResponse;
import com.sana.avocado.enums.SessionStatusEnum;
import com.sana.avocado.enums.TransactionTypeEnum;
import com.sana.avocado.exception.EmptyResourceListException;
import com.sana.avocado.exception.ResourceRegistrationException;
import com.sana.avocado.exception.ResourceUpdationException;
import com.sana.avocado.model.Transactions;
import com.sana.avocado.service.ITransactionService;
import com.sana.avocado.service.IUserTransactionsWalletService;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class TransactionController {
	
	private static Logger logger = LogManager.getLogger(TransactionController.class);
	
	@Autowired
	ITransactionService transactionService;
	
	
	@Autowired
	IUserTransactionsWalletService userTransWalletService;
	
	
	@PostMapping(value = "/transaction")
	public ResponseEntity<ApiResponse> addTransaction(@RequestBody @Valid Transactions transactions) throws URISyntaxException {
		logger.info("TransactionController.addTransaction()::Transactions:" + transactions);

			return transactionService.addTransaction(transactions).map(newTransactions -> {
				ApiResponse apiResponse = null;
				logger.info("Transactions returned [API[: " + newTransactions);
				apiResponse=new ApiResponse("Transaction is added successfully.", true);
				apiResponse.setData(newTransactions);
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceRegistrationException("Transactions", "Transactions registration is failed"));

	}
	
	@PutMapping(value = "/transaction")
	public ResponseEntity<ApiResponse> updateTransaction(@RequestBody @Valid Transactions transactions, @RequestHeader(value="transactionAmount") double transactionAmount, @RequestHeader(value="", required=false) TransactionTypeEnum transactionType) throws URISyntaxException {
		
		logger.info("TransactionController.updateTransaction()::Transactions:" + transactions);

			return transactionService.updateTransaction(transactions,transactionAmount,transactionType).map(newTransactions -> {
				ApiResponse apiResponse = null;
				logger.info("Transactions returned [API[: " + newTransactions);
				apiResponse=new ApiResponse("Transaction is updated successfully.", true);
				Optional<UserTransactionsWallet> userTransWallet =  userTransWalletService.getUserBySessionId(newTransactions.getUserName(), newTransactions.getSessionId());
				apiResponse.setData(newTransactions); 
				apiResponse.setData(userTransWallet.get());
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceUpdationException("Transactions", "Transactions Updation is failed"));

	}	
	
	
	@PutMapping(value = "/transaction/meterValues")
	public ResponseEntity<ApiResponse> updateMeterValues(@RequestBody @Valid Transactions transactions) throws URISyntaxException {
		
		logger.info("TransactionController.updateTransaction()::Transactions:" + transactions);

			return transactionService.updateMeterValues(transactions).map(newTransactions -> {
				ApiResponse apiResponse = null;
				logger.info("Transactions returned [API[: " + newTransactions);
				apiResponse=new ApiResponse("Transaction is updated successfully.", true);
				apiResponse.setData(newTransactions);
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceUpdationException("Transactions", "Transactions Updation is failed"));

	}			
	
	@GetMapping(value = "/transaction/{userName}")
	public ResponseEntity<?> getAllTransactions(@PathVariable @Valid String userName) throws URISyntaxException {
		
		logger.info("TransactionController.getAllTransactions() details ::");
		

			Optional<List<Transactions>> transactionsList = transactionService.getAllTransactionsByUserName(userName);
			ApiResponse apiResponse=null;
			if(transactionsList.isPresent()) {
				apiResponse=new ApiResponse("Transactions are Not Found.", false);
			}else {
				apiResponse=new ApiResponse("Transactions are found successfully.", true);
			}
			apiResponse.setData(transactionsList.get());
			return ResponseEntity.ok(apiResponse);
		
		
	}
	 	
	@GetMapping(value = "/transaction/sessionStatus/{userName}/{sessionStatusEnum}")
	public ResponseEntity<?> getTransactionsByStatus(@PathVariable @Valid String userName, @PathVariable("sessionStatusEnum") SessionStatusEnum sessionStatusEnum) throws URISyntaxException {
		
		logger.info("TransactionController.getAllTransactions() details ::");
		
			Optional<List<Transactions>> transactionsList = transactionService.getTransactionsByStatus(userName, sessionStatusEnum);
			ApiResponse apiResponse=null;
			if(transactionsList.isPresent()) {
				apiResponse=new ApiResponse("Transactions are Not Found.", false);
			}else {
				apiResponse=new ApiResponse("Transactions are found successfully.", true);
			}
			apiResponse.setData(transactionsList.get());
			return ResponseEntity.ok(apiResponse);
	
		
	}
	
	@GetMapping(value = "/transaction/completed/{userName}")
	public ResponseEntity<?> getCompletedTransactions(@PathVariable @Valid String userName) throws URISyntaxException {
		
		logger.info("TransactionController.getAllTransactions() details ::");
		
		    SessionStatusEnum sessionStatusEnum = SessionStatusEnum.STOP;
		
			Optional<List<Transactions>> transactionsList = transactionService.getTransactionsByStatus(userName, sessionStatusEnum);
			ApiResponse apiResponse=null;
			if(transactionsList.isPresent()) {
				apiResponse=new ApiResponse("Transactions are Not Found.", false);
			}else {
				apiResponse=new ApiResponse("Transactions are found successfully.", true);
			}
			apiResponse.setData(transactionsList.get());
			return ResponseEntity.ok(apiResponse);
	
		
	}
	
	
	@GetMapping(value = "/transaction/pending/{userName}")
	public ResponseEntity<?> getPendingTransactions(@PathVariable @Valid String userName) throws URISyntaxException {
		
		logger.info("TransactionController.getAllTransactions() details ::");
		
		    SessionStatusEnum sessionStatusEnum = SessionStatusEnum.START;
		
			Optional<List<Transactions>> transactionsList = transactionService.getTransactionsByStatus(userName, sessionStatusEnum);
			ApiResponse apiResponse=null;
			if(transactionsList.isPresent()) {
				apiResponse=new ApiResponse("Transactions are Not Found.", false);
			}else {
				apiResponse=new ApiResponse("Transactions are found successfully.", true);
			}
			apiResponse.setData(transactionsList.get());
			return ResponseEntity.ok(apiResponse);
		
	}
	
		
	@GetMapping(value = "/transaction/sessionId/{userName}/{sessionId}")
	public ResponseEntity<?> getTransactionBySessionId(@PathVariable @Valid String userName, @PathVariable @Valid String sessionId) throws URISyntaxException {
		
		logger.info("TransactionController.getAllTransactions() details ::");
		
		
			Optional<Transactions> transactionsList = transactionService.getTransactionByUserNameAndSessionId(userName, sessionId);
			ApiResponse apiResponse=null;
			if(transactionsList.isPresent()) {
				apiResponse=new ApiResponse("Transactions are Not Found.", false);
			}else {
				apiResponse=new ApiResponse("Transactions are found successfully.", true);
			}
			apiResponse.setData(transactionsList.get());
			return ResponseEntity.ok(apiResponse);
			
	}
	
	@GetMapping(value = "/transaction/{userName}/{startDate}/{endDate}")
	public ResponseEntity<?> getAllTransDates(@PathVariable("userName") String userName,@PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) {
		logger.info("Transactions.getAllTransDates()" );
		
		Optional<List<Transactions>> paymentList = transactionService.getAllTransDates(userName,startDate,endDate);
		paymentList.orElseThrow(() -> new EmptyResourceListException("No User Transactions are found"));
		ApiResponse apiResponse=new ApiResponse("User Transactions are found", true);
		apiResponse.setData(paymentList.get());
		return ResponseEntity.ok(apiResponse);
		
	}
	
	@GetMapping(value = "/transaction/dateRange/sessionStatus/{userName}/{sessionStatusEnum}/{startDate}/{endDate}")
	public ResponseEntity<?> getTransactionsBySessionStatusAndDateRange(@PathVariable @Valid String userName,  @PathVariable("sessionStatusEnum") SessionStatusEnum sessionStatusEnum, @PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) throws URISyntaxException {
		
		logger.info("TransactionController.getAllTransactions() details ::");
		
		System.out.println(sessionStatusEnum);
			
			Optional<List<Transactions>> transactionsList = transactionService.getTransactionsBySessionStatusAndDateRange(userName, sessionStatusEnum, startDate, endDate);
			ApiResponse apiResponse=null;
			if(transactionsList.isPresent()) {
				apiResponse=new ApiResponse("Transactions are Not Found.", false);
			}else {
				apiResponse=new ApiResponse("Transactions are found successfully.", true);
			}
			apiResponse.setData(transactionsList.get());
			return ResponseEntity.ok(apiResponse);
		
	}
}
