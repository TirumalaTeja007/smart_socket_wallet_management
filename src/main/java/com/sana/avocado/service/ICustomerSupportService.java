package com.sana.avocado.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sana.avocado.enums.SupportTicketStatusEnum;
import com.sana.avocado.model.CustomerSupport;

public interface ICustomerSupportService {
	
	Optional<CustomerSupport> createSupport(@Valid CustomerSupport support);

	Optional<CustomerSupport> updateSupport(@Valid CustomerSupport support);

	Optional<List<CustomerSupport>> getAllTickets();

	Optional<List<CustomerSupport>> getByUserNumber(String userName);

	Optional<List<CustomerSupport>> getByUserNameAndTicketStatus(String userName, SupportTicketStatusEnum ticketStatusEnum);

}
    