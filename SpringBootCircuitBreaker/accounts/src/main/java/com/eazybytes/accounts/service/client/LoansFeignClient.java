package com.eazybytes.accounts.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.model.Loans;

@FeignClient("Loans")
public interface LoansFeignClient {
	@RequestMapping(method = RequestMethod.POST,value="/myLoans",consumes="application/json",produces = "application/json")
	public List<Loans> findLoansByCustomerId(@RequestBody Customer customer);
}
