package com.sana.avocado.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.avocado.enums.SupportTicketStatusEnum;
import com.sana.avocado.model.CustomerSupport;

@Repository
public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Integer> {

	Optional<List<CustomerSupport>> findByUserName(String userName);

	Optional<List<CustomerSupport>> findByUserNameAndTicketStatusEnum(String userName,
			SupportTicketStatusEnum ticketStatusEnum);

}
   