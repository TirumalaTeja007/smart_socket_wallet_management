package com.sana.avocado.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.avocado.enums.TransactionStatusEnum;
import com.sana.avocado.enums.TransactionTypeEnum;
import com.sana.avocado.model.UserTransactionsWallet;

@Repository
public interface UserTransactionsWalletRepository extends JpaRepository<UserTransactionsWallet, Integer> {

	Optional<List<UserTransactionsWallet>> findByUserName(String userName);

	Optional<List<UserTransactionsWallet>> findByUserNameAndTransactionType(String userName, TransactionTypeEnum transactionType);

	Optional<List<UserTransactionsWallet>> findByUserNameAndTransactionStatus(String userName,
			TransactionStatusEnum transactionStatus);

	Optional<List<UserTransactionsWallet>> findByUserNameAndOrderId(String userName, String orderId);
} 
   