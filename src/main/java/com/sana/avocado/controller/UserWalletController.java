package com.sana.avocado.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sana.avocado.dto.ApiResponse;
import com.sana.avocado.exception.ResourceNotFoundException;
import com.sana.avocado.model.UserWallet;
import com.sana.avocado.service.IUserWalletService;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class UserWalletController {
	
	private static Logger logger = LogManager.getLogger(UserWalletController.class);
	
	@Autowired
	IUserWalletService userWalletService;
	
	
	
	@GetMapping("/userWalletAmount")
	public ResponseEntity<?> getUserWallet(@RequestHeader(value="", required=false) String password){
		logger.info("UserWalletController.getUserWallet() details ::");
		
		Optional<List<UserWallet>> userWalletList = userWalletService.getUserWallet();
		
		ApiResponse apiResponse=null;
		if(userWalletList.isPresent()) {
			apiResponse=new ApiResponse("User Wallets are Not Found.", false);
		}else {
			apiResponse=new ApiResponse("User Wallet are found successfully.", true);
		}
		apiResponse.setData(userWalletList.get());
		return ResponseEntity.ok(apiResponse);
		
	}
	
	
	@GetMapping(value = "/userWalletAmount/{userName}")
	public ResponseEntity<?> getUserNameWallet(@PathVariable("userName") String userName) {
		
			 return userWalletService.getUserByUserName(userName).map(user -> {
		            logger.info("getUser returned [API[: " + user);
		            return ResponseEntity.ok(user);
		        }).orElseThrow(() -> new ResourceNotFoundException("User", userName, userName));
	}
	

}
