package com.sana.avocado.service;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sana.avocado.enums.SessionStatusEnum;
import com.sana.avocado.enums.TransactionTypeEnum;
import com.sana.avocado.model.Transactions;
import com.sana.avocado.model.User;

public interface ITransactionService {

	Optional<Transactions> addTransaction(@Valid Transactions transactions);

	//Optional<Transactions> updateTransaction(@Valid Transactions transactions);

	Optional<List<Transactions>> getAllTransactions();

	User getUser(String userName) throws URISyntaxException;

	Optional<Transactions> getTransactionByUserNameAndSessionId(String userName, String sessionId);

	Optional<List<Transactions>> getPendingTransaction(String userName,SessionStatusEnum sessionStatusEnum);

	Optional<List<Transactions>> getCompletedTransactions(String userName,SessionStatusEnum sessionStatusEnum);

	Optional<List<Transactions>> getAllTransactionsByUserName(String userName);

	Optional<Transactions> updateTransaction(@Valid Transactions transactions, double transactionAmount,
			TransactionTypeEnum transactionType);

	Optional<Transactions> updateMeterValues(@Valid Transactions transactions);

	Optional<List<Transactions>> getAllTransDates(String userName, Date startDate, Date endDate);

	Optional<List<Transactions>> getTransactionsBySessionStatusAndDateRange(@Valid String userName,
			SessionStatusEnum sessionStatusEnum, Date startDate, Date endDate);

	Optional<List<Transactions>> getTransactionsByStatus(@Valid String userName, SessionStatusEnum sessionStatusEnum);
   
}
     