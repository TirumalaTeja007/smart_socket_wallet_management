package com.sana.avocado.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sana.avocado.enums.SupportTicketStatusEnum;
import com.sana.avocado.exception.ResourceCreationException;
import com.sana.avocado.exception.ResourceUpdationException;
import com.sana.avocado.model.CustomerSupport;
import com.sana.avocado.repository.CustomerSupportRepository;
import com.sana.avocado.service.ICustomerSupportService;

@Service
public class CustomerSupportService implements ICustomerSupportService {
	
	private static Logger logger = LogManager.getLogger(CustomerSupportService.class);
	
	@Autowired
	CustomerSupportRepository supportRepo;

	@Override
	public Optional<CustomerSupport> createSupport(@Valid CustomerSupport support) {
		CustomerSupport newSupport = new CustomerSupport();
		try {
			support.setCreatedTime(new Date());
			
			newSupport = supportRepo.save(support);
			
		}catch(Exception e) {
			throw new ResourceCreationException("Customer Support", "Customer Support ticket registration is failed.");
		}
		return Optional.ofNullable(newSupport);
	}

	@Override
	public Optional<CustomerSupport> updateSupport(@Valid CustomerSupport support) {
		CustomerSupport newSupport = new CustomerSupport();
		try {
			support.setUpdatedTime(new Date());
			
			newSupport = supportRepo.save(support);
			
		}catch(Exception e) {
			throw new ResourceUpdationException("Customer Support", "Customer Support ticket update is failed.");
		}
		return Optional.ofNullable(newSupport);
	}

	@Override
	public Optional<List<CustomerSupport>> getAllTickets() {
		return Optional.ofNullable(supportRepo.findAll());
	}

	@Override
	public Optional<List<CustomerSupport>> getByUserNumber(String userName) {
		return supportRepo.findByUserName(userName);
	}

	@Override
	public Optional<List<CustomerSupport>> getByUserNameAndTicketStatus(String userName,
			SupportTicketStatusEnum ticketStatusEnum) {
		return supportRepo.findByUserNameAndTicketStatusEnum(userName,ticketStatusEnum);
	}


}
