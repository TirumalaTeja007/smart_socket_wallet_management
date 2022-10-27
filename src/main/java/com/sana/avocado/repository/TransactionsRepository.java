package com.sana.avocado.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.avocado.enums.SessionStatusEnum;
import com.sana.avocado.model.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

	Optional<Transactions> findByUserNameAndSessionId(String userName, String sessionId);
	
	Optional<List<Transactions>> findByUserName(String userName);

	Optional<List<Transactions>> findByUserNameAndSessionStatusEnum(String userName, SessionStatusEnum sessionStatusEnum);

	Optional<List<Transactions>> findByUserNameAndCreatedTimeBetween(String userName, Date startDate, Date endDate);


	Optional<List<Transactions>> findByUserNameAndCreatedTimeBetweenAndSessionStatusEnum(@Valid String userName,
			SessionStatusEnum sessionStatusEnum, Date startDate, Date endDate);

	Optional<List<Transactions>> findByUserNameAndCreatedTimeBetweenAndSessionStatusEnum(@Valid String userName,
			Date startDate, Date endDate, SessionStatusEnum sessionStatusEnum);  
}  
  