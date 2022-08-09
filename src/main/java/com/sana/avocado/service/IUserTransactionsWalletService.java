package com.sana.avocado.service;

import java.net.URISyntaxException;
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
}
 