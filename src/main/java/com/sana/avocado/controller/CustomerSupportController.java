package com.sana.avocado.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sana.avocado.dto.ApiResponse;
import com.sana.avocado.enums.SupportTicketStatusEnum;
import com.sana.avocado.exception.ResourceNotFoundException;
import com.sana.avocado.exception.ResourceRegistrationException;
import com.sana.avocado.exception.ResourceUpdationException;
import com.sana.avocado.model.CustomerSupport;
import com.sana.avocado.service.ICustomerSupportService;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class CustomerSupportController {
	
	private static Logger logger = LogManager.getLogger(CustomerSupportController.class);
	
	@Autowired
	ICustomerSupportService supportService;
	
	@PostMapping(value = "/support")
	public ResponseEntity<ApiResponse> createSupport(@RequestBody @Valid CustomerSupport support) {
		logger.info("CustomerSupportController.createSupport()::Transactions:" + support);
		System.out.println(support);
			return supportService.createSupport(support).map(newsupport -> {
				ApiResponse apiResponse = null;
				logger.info("Customer Support returned [API[: " + newsupport);
				apiResponse=new ApiResponse("Customer Support ticket is raised successfully.", true);
				apiResponse.setData(newsupport);
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceRegistrationException("Customer Support", "Customer Support ticket registration is failed"));
	}
	
	
	@PutMapping(value = "/support")
	public ResponseEntity<ApiResponse> updateSupport(@RequestBody @Valid CustomerSupport support) {
		logger.info("CustomerSupportController.updateSupport()::Transactions:" + support);
		System.out.println(support);
			return supportService.updateSupport(support).map(newsupport -> {
				ApiResponse apiResponse = null;
				logger.info("Customer Support returned [API[: " + newsupport);
				apiResponse=new ApiResponse("Customer Support ticket is updated successfully.", true);
				apiResponse.setData(newsupport);
				return ResponseEntity.ok(apiResponse);
			}).orElseThrow(() -> new ResourceUpdationException("Customer Support", "Customer Support ticket update is failed"));
	}
	
	@GetMapping("/support")
	public ResponseEntity<?> getAllTickets(){
		logger.info("CustomerSupportController.getAllTickets() details ::");
		
		Optional<List<CustomerSupport>> supportList = supportService.getAllTickets();
		ApiResponse apiResponse=null;
		if(supportList.isPresent()) {
			apiResponse=new ApiResponse("Customer Support tickets are Not Found.", false);
		}else {
			apiResponse=new ApiResponse("Customer Support tickets are found successfully.", true);
		}
		apiResponse.setData(supportList.get());
		return ResponseEntity.ok(apiResponse);
		
	}
	
	@GetMapping("/support/{userName}")
	public ResponseEntity<?> getByUserNumber(@PathVariable("userName") String userName) {
		logger.info("CustomerSupportController.getByUserNumber()::userName:" + userName);
		
		Optional<List<CustomerSupport>> supportList = supportService.getByUserNumber(userName);
		
		ApiResponse apiResponse=null;
		if(supportList.isPresent()) {
			apiResponse=new ApiResponse("Customer Support tickets are Not Found.", false);
		}else {
			apiResponse=new ApiResponse("Customer Support tickets are found successfully.", true);
		}
		apiResponse.setData(supportList.get());
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/support/ticketStatus/{userName}/{ticketStatusEnum}")
	public ResponseEntity<?> getByUserNameAndTicketStatus(@PathVariable("userName") String userName,@PathVariable("ticketStatusEnum") SupportTicketStatusEnum ticketStatusEnum) {
		logger.info("CustomerSupportController.getByUserNumber()::userName:" + userName);
		
        Optional<List<CustomerSupport>> supportList = supportService.getByUserNameAndTicketStatus(userName, ticketStatusEnum);
		
		ApiResponse apiResponse=null;
		if(supportList.isPresent()) {
			apiResponse=new ApiResponse("Customer Support tickets are Not Found.", false);
		}else {
			apiResponse=new ApiResponse("Customer Support tickets are found successfully.", true);
		}
		apiResponse.setData(supportList.get());
		return ResponseEntity.ok(apiResponse);
		
	}
}
