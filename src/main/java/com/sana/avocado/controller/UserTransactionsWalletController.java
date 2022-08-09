package com.sana.avocado.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sana.avocado.dto.ApiResponse;
import com.sana.avocado.enums.TransactionStatusEnum;
import com.sana.avocado.enums.TransactionTypeEnum;
import com.sana.avocado.exception.EmptyResourceListException;
import com.sana.avocado.exception.ResourceNotFoundException;
import com.sana.avocado.exception.ResourceRegistrationException;
import com.sana.avocado.exception.ResourceUpdationException;
import com.sana.avocado.model.User;
import com.sana.avocado.model.UserTransactionsWallet;
import com.sana.avocado.service.IUserTransactionsWalletService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class UserTransactionsWalletController {
	
	private static Logger logger = LogManager.getLogger(UserTransactionsWalletController.class);
	
	@Autowired
	IUserTransactionsWalletService userWalletService;
	
	@PostMapping(value = "/userWallet")
	public ResponseEntity<ApiResponse> addUserWallet(@RequestBody @Valid UserTransactionsWallet userWallet, @RequestHeader(value="", required=true) String password) throws URISyntaxException {
		logger.info("UserWalletController.addUserWallet()::User Wallet:" + userWallet);
		
		User user = userWalletService.getUser(userWallet.getUserName());
		if(password.equals(user.getPassword()) == true) {
		
			return userWalletService.addUserWallet(userWallet).map(newUserWallet -> {
				ApiResponse apiResponse = null;
				logger.info("User Wallet returned [API[: " + newUserWallet);
				apiResponse=new ApiResponse("User Wallet is added successfully.", true);
				apiResponse.setData(newUserWallet);
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceRegistrationException("User Wallet", "User Wallet registration is failed"));
		}else {
			throw new ResourceRegistrationException("UserName & Password", "Unauthorized request is registered.");
		}
	}
	
	@PutMapping(value = "/userWallet")
	public ResponseEntity<ApiResponse> updateUserWallet(@RequestBody @Valid UserTransactionsWallet userWallet, @RequestHeader(value="", required=true) String password) throws URISyntaxException {
		logger.info("UserWalletController.updateUserWallet()::User Wallet:" + userWallet);
		
		User user = userWalletService.getUser(userWallet.getUserName());
		if(password.equals(user.getPassword()) == true) {
		
			return userWalletService.updateUserWallet(userWallet).map(newUserWallet -> {
				ApiResponse apiResponse = null;
				logger.info("User Wallet returned [API[: " + newUserWallet);
				apiResponse=new ApiResponse("User Wallet is Updated successfully.", true);
				apiResponse.setData(newUserWallet);
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceUpdationException("User Wallet", "User Wallet Updated is failed"));
		}else {
			throw new ResourceRegistrationException("UserName & Password", "Unauthorized request is registered.");
		}
	}
	
	 
	@GetMapping("/userWallet")
	public ResponseEntity<?> getUserWallet(@RequestBody @Valid String userName, @RequestHeader(value="", required=true) String password) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 
		User user = userWalletService.getUser(userName);
		
		if(password.equals(user.getPassword()) == true) {
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserWallet();
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No UserWallets are found."));
			ApiResponse res = new ApiResponse("User Wallets are found Successfully.", true);
			res.setData(userWalletList);
			return ResponseEntity.ok(res);	
		} else {
			throw new ResourceRegistrationException("UserName & Password", "Unauthorized request is registered.");
		}
			
	}	
	
	@GetMapping(value = "/userWallet/userName/{userName}")
	public ResponseEntity<?> getUserNameWallet(@PathVariable("userName") String userName, @RequestHeader(value="", required=true) String password) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 
		User user = userWalletService.getUser(userName);
		
		if(password.equals(user.getPassword()) == true) {
			 return userWalletService.getUserByUserName(userName).map(userWallet -> {
		            logger.info("getUser returned [API[: " + userWallet);
		            ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
		            response.setData(userWallet);
		            
		            return ResponseEntity.ok(response);
		        }).orElseThrow(() -> new ResourceNotFoundException("User", userName, userName));
		} else {
			throw new ResourceRegistrationException("UserName & Password", "Unauthorized request is registered.");
		}
	}
	
	@GetMapping(value = "/userWallet/transactionType/{userName}/{transactionType}")
	public ResponseEntity<?> getUserNameWallet(@PathVariable("userName") String userName,@PathVariable("transactionType") TransactionTypeEnum transactionType, @RequestHeader(value="", required=true) String password) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 
		User user = userWalletService.getUser(userName);
		
		if(password.equals(user.getPassword()) == true) {		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByTrans(userName,transactionType);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and TransactionType."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);
		} else {
			throw new ResourceRegistrationException("UserName & Password", "Unauthorized request is registered.");
		}
	}
	
	@GetMapping(value = "/userWallet/transactionStatus/{userName}/{transactionStatus}")
	public ResponseEntity<?> getUserNameWallet(@PathVariable("userName") String userName,@PathVariable("transactionStatus") TransactionStatusEnum transactionStatus, @RequestHeader(value="", required=true) String password) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 
		User user = userWalletService.getUser(userName);
		
		if(password.equals(user.getPassword()) == true) {
		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByTranxStatus(userName,transactionStatus);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and TransactionStatus."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);
		} else {
			throw new ResourceRegistrationException("UserName & Password", "Unauthorized request is registered.");
		}
	}
	
	@GetMapping(value = "/userWallet/orderId/{userName}/{orderId}")
	public ResponseEntity<?> getUserNameWallet(@PathVariable("userName") String userName,@PathVariable("orderId") String orderId, @RequestHeader(value="", required=true) String password) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 
		User user = userWalletService.getUser(userName);
		
		if(password.equals(user.getPassword()) == true) {
		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByOrderId(userName,orderId);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and OrderId."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);
		} else {
			throw new ResourceRegistrationException("UserName & Password", "Unauthorized request is registered.");
		}
	}
}
