package com.sana.avocado.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sana.avocado.model.User;
import com.sana.avocado.model.UserWallet;
import com.sana.avocado.repository.UserWalletRepository;
import com.sana.avocado.service.IUserWalletService;

@Service
public class UserWalletService implements IUserWalletService {
	
	private static Logger logger = LogManager.getLogger(UserWalletService.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UserWalletRepository userWalletRepo;
	
	@Value("${userApi}")
	private String userApi;

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
	
	
	@Override
	public User getUser(String userName) throws URISyntaxException {
		User user = null;
		String api = userApi + userName;
		URI uri = new URI(api);
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(uri, User.class);
		User objects = responseEntity.getBody();
		return objects;
	}
}
