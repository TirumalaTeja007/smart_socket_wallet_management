package com.sana.avocado.service;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sana.avocado.enums.TransactionStatusEnum;
import com.sana.avocado.enums.TransactionTypeEnum;
import com.sana.avocado.model.User;
import com.sana.avocado.model.UserTransactionsWallet;

public interface IUserTransactionsWalletService {

	Optional<UserTransactionsWallet> addUserWallet(@Valid UserTransactionsWallet userWallet);

	Optional<UserTransactionsWallet> updateUserWallet(@Valid UserTransactionsWallet userWallet);

	Optional<List<UserTransactionsWallet>> getUserWallet();

	User getUser(String userName) throws URISyntaxException;

	Optional<List<UserTransactionsWallet>> getUserByUserName(String userName);

	Optional<List<UserTransactionsWallet>> getUserByTrans(String userName, TransactionTypeEnum transactionType);

	Optional<List<UserTransactionsWallet>> getUserByTranxStatus(String userName,
			TransactionStatusEnum transactionStatus);

	Optional<List<UserTransactionsWallet>> getUserByOrderId(String userName, String orderId); 
	
	Optional<UserTransactionsWallet> getUserBySessionId(String userName, String sessionId);

	Optional<List<UserTransactionsWallet>> getAllWalletsDates(String userName, Date startDate, Date endDate);

	Optional<List<UserTransactionsWallet>> getUserByWalletTransactionId(String userName, String walletTransactionId);

	Optional<List<UserTransactionsWallet>> getUserByTransactionTypeAndDates(String userName,
			TransactionTypeEnum transactionType, Date startDate, Date endDate);

	Optional<List<UserTransactionsWallet>> getUserByTransactionStatusAndDates(String userName,
			TransactionStatusEnum transactionStatus, Date startDate, Date endDate);
}
     