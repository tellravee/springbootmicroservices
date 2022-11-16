package com.eazybytes.accounts.controller;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.AccountConfig;
import com.eazybytes.DBConfiguration;
import com.eazybytes.accounts.model.Accounts;
import com.eazybytes.accounts.model.Cards;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.model.CustomerDetails;
import com.eazybytes.accounts.model.Loans;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class AccountsController {
	private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);
	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private AccountConfig accountsConfig;

	@Autowired
	private DBConfiguration dbConfig;
	
	@Autowired
	private LoansFeignClient loansFeignClient;
	@Autowired 
	private CardsFeignClient cardsFeignClient;
	

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestHeader("tellravee-correlation-id") String correlationId,@RequestBody Customer customer) {
		//logger.in
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}
	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties();
		
		properties.put("msg",accountsConfig.getMsg());
		properties.put("build-Version",accountsConfig.getBuildVersion());
		properties.putAll(accountsConfig.getMailDetails());
		properties.put("activeBranches",accountsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	@GetMapping("/account/dbproperties")
	public String getdbPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties();
		properties.put("driveClassName",dbConfig.getDriverClassName());
		properties.put("url",dbConfig.getUrl());
		properties.put("password",dbConfig.getPassword());
		properties.put("username",dbConfig.getUsername());
		properties.put("username",dbConfig.getUsername());
		
		properties.put("Platform",dbConfig.getDatabasePlatform());
		properties.put("ddlAuto",dbConfig.getDdlAuto());
		properties.put("enabled",dbConfig.isEnabled());
		properties.put("showSql",dbConfig.isShowSql());
		properties.put("Web allowed Others",dbConfig.isWebAllowOthers());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	@PostMapping(consumes = "application/json",produces = "application/json",value = "/account/myCustomer")
	//@CircuitBreaker(name="myCustomerDetails",fallbackMethod = "myCustomerDetailsFallback")
	@Retry(name="myCustomerDetails",fallbackMethod = "myCustomerDetailsFallback")
	public CustomerDetails myCustomerDetails(@RequestHeader("tellravee-correlation-id") String correlationId, @RequestBody Customer customer) {
		logger.info(" Inside myCustomerDetails Correlation id:"+correlationId);
		List<Loans> loans=loansFeignClient.findLoansByCustomerId(correlationId,customer);
		List<Cards> cards=cardsFeignClient.getCardsByCustomerId(correlationId,customer);
		Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId());
		CustomerDetails customerDetails=new CustomerDetails();
		customerDetails.setLoans(loans);
		customerDetails.setCards(cards);
		customerDetails.setAccounts(accounts);
		return customerDetails;
	}
	private CustomerDetails myCustomerDetailsFallback(@RequestHeader("tellravee-correlation-id") String correlationId,Customer customer,Throwable t) {
		logger.info("Inside myCustomerDetails fallback method Correlation id:"+correlationId);
		List<Loans> loans=loansFeignClient.findLoansByCustomerId(correlationId,customer);
		
		Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId());
		CustomerDetails customerDetails=new CustomerDetails();
		customerDetails.setLoans(loans);
		
		customerDetails.setAccounts(accounts);
		return customerDetails;
	}
	
	@GetMapping("/sayHello")
	@RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
	public String sayHello() {
		return "Hello, Welcome to Tellravee Bank";
	}

	private String sayHelloFallback(Throwable t) {
		return "Hi, Welcome to Tellravee Bank";
	}
}
