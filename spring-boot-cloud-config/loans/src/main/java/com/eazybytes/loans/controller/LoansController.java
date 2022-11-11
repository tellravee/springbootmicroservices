package com.eazybytes.loans.controller;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.DBConfiguration;
import com.eazybytes.LoansConfig;
import com.eazybytes.loans.model.Customer;
import com.eazybytes.loans.model.Loans;
import com.eazybytes.loans.repository.LoanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RestController
public class LoansController {
	@Autowired
	LoanRepository loanRepository;
	@Autowired
	LoansConfig loanConfig;
	@Autowired
	DBConfiguration dbConfig;
	@PostMapping("/myLoans")
	public List<Loans> findLoansByCustomerId(@RequestBody Customer customer){
		List<Loans> loanList=loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		if(loanList.size()>0)
			return loanList;
		else 
			return null;
	}
	
	@GetMapping("/loans/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties();
		
		properties.put("msg",loanConfig.getMsg());
		properties.put("build-Version",loanConfig.getBuildVersion());
		properties.putAll(loanConfig.getMailDetails());
		properties.put("activeBranches",loanConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	@GetMapping("/loans/dbproperties")
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

}
