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
	public ResponseEntity<ApiResponse> updateUserWallet(@RequestBody @Valid UserTransactionsWallet userWallet) throws URISyntaxException {
		logger.info("UserWalletController.updateUserWallet()::User Wallet:" + userWallet);
				
			return userWalletService.updateUserWallet(userWallet).map(newUserWallet -> {
				ApiResponse apiResponse = null;
				logger.info("User Wallet returned [API[: " + newUserWallet);
				apiResponse=new ApiResponse("User Wallet is Updated successfully.", true);
				apiResponse.setData(newUserWallet);
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceUpdationException("User Wallet", "User Wallet Updated is failed"));
		
	}
	  
	@GetMapping("/userWallet")
	public ResponseEntity<?> getUserWallet(@RequestBody @Valid String userName) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserWallet();
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No UserWallets are found."));
			ApiResponse res = new ApiResponse("User Wallets are found Successfully.", true);
			res.setData(userWalletList);
			return ResponseEntity.ok(res);	
		
			
	}	
	
	@GetMapping(value = "/userWallet/userName/{userName}")
	public ResponseEntity<?> getUserNameWallet(@PathVariable("userName") String userName) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 
			 return userWalletService.getUserByUserName(userName).map(userWallet -> {
		            logger.info("getUser returned [API[: " + userWallet);
		            ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
		            response.setData(userWallet);
		            
		            return ResponseEntity.ok(response);
		        }).orElseThrow(() -> new ResourceNotFoundException("User", userName, userName));
		
	}
	
	@GetMapping(value = "/userWallet/transactionType/{userName}/{transactionType}")
	public ResponseEntity<?> getWalletTranxByType(@PathVariable("userName") String userName,@PathVariable("transactionType") TransactionTypeEnum transactionType) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByTrans(userName,transactionType);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and TransactionType."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(value = "/userWallet/transactionStatus/{userName}/{transactionStatus}")
	public ResponseEntity<?> getWalletTranxByStatus(@PathVariable("userName") String userName,@PathVariable("transactionStatus") TransactionStatusEnum transactionStatus) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");		
		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByTranxStatus(userName,transactionStatus);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and TransactionStatus."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);		
	}
	
	@GetMapping(value = "/userWallet/orderId/{userName}/{orderId}")
	public ResponseEntity<?> getWalletTranxByOrderId(@PathVariable("userName") String userName,@PathVariable("orderId") String orderId) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByOrderId(userName,orderId);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and OrderId."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);		
	}
	 	
	@GetMapping(value = "/userWallet/walletTransactionId/{userName}/{walletTransactionId}")
	public ResponseEntity<?> getWalletTranxByTranxId(@PathVariable("userName") String userName,@PathVariable("walletTransactionId") String walletTransactionId) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByWalletTransactionId(userName,walletTransactionId);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and OrderId."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);		
	}
	
	@GetMapping(value = "/userWallet/sessionId/{userName}/{sessionId}")
	public ResponseEntity<?> getWalletTranxBySessionId(@PathVariable("userName") String userName,@PathVariable("sessionId") String sessionId) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 		
			Optional<UserTransactionsWallet> userWalletList = userWalletService.getUserBySessionId(userName,sessionId);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and SessionId."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);		
	}
	
	@GetMapping(value = "/userWallet/{userName}/{startDate}/{endDate}")
	public ResponseEntity<?> getAllWalletsDates(@PathVariable("userName") String userName,@PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) {
		logger.info("paymentService.getAllPayments()" );
		
		Optional<List<UserTransactionsWallet>> paymentList = userWalletService.getAllWalletsDates(userName,startDate,endDate);
		paymentList.orElseThrow(() -> new EmptyResourceListException("No User Transactions Wallets are found"));
		ApiResponse apiResponse=new ApiResponse("User Transactions Wallets are found", true);
		apiResponse.setData(paymentList.get());
		return ResponseEntity.ok(apiResponse);
		
	}
	
	@GetMapping(value = "/userWallet/dateRange/transactionType/{userName}/{transactionType}/{startDate}/{endDate}")
	public ResponseEntity<?> getWalletTranxByTypeAndDateRange(@PathVariable("userName") String userName,@PathVariable("transactionType") TransactionTypeEnum transactionType,@PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByTransactionTypeAndDates(userName,transactionType,startDate,endDate);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and TransactionType."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(value = "/userWallet/dateRange/transactionStatus/{userName}/{transactionType}/{startDate}/{endDate}")
	public ResponseEntity<?> getWalletTranxByStatusAndDateRange(@PathVariable("userName") String userName,@PathVariable("transactionType") TransactionStatusEnum transactionStatus,@PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) throws URISyntaxException{
		logger.info("UserWalletController.getUserWallet() details ::");
		 		
			Optional<List<UserTransactionsWallet>> userWalletList = userWalletService.getUserByTransactionStatusAndDates(userName,transactionStatus,startDate,endDate);
			userWalletList.orElseThrow(() -> new EmptyResourceListException("No user Transactions are found by UserName and TransactionType."));
			ApiResponse response = new ApiResponse("User Wallets are Found by UserName Successfully.", true);
			response.setData(userWalletList.get());
			return ResponseEntity.ok(response);
		
	}
}
