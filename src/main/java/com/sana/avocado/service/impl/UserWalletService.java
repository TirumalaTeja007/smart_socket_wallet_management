package com.sana.avocado.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sana.avocado.model.UserWallet;
import com.sana.avocado.repository.UserWalletRepository;
import com.sana.avocado.service.IUserWalletService;

@Service
public class UserWalletService implements IUserWalletService {
	
	private static Logger logger = LogManager.getLogger(UserWalletService.class);
	
	@Autowired
	UserWalletRepository userWalletRepo;
	
	@Override
	public Optional<UserWallet> getUserByUserName(String userName) {
		return userWalletRepo.findByUserName(userName);
	}

	@Override
	@Transactional
	public Optional<UserWallet> updateUserWallet(UserWallet existingUserWallet) {

		UserWallet newUserWallet = new UserWallet();
		existingUserWallet.setUpdatedTime(new Date());
		existingUserWallet.setVersion(existingUserWallet.getVersion()+1);
		newUserWallet = userWalletRepo.save(existingUserWallet);
		
		return Optional.ofNullable(newUserWallet);
	}

	@Override
	public Optional<List<UserWallet>> getUserWallet() {
		return Optional.ofNullable(userWalletRepo.findAll());
	}
	
}
