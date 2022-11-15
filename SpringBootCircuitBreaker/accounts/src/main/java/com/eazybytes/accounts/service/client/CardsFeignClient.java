package com.eazybytes.accounts.service.client;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eazybytes.accounts.model.Cards;
import com.eazybytes.accounts.model.Customer;



@FeignClient("cards")
public interface CardsFeignClient {
	@RequestMapping(method = RequestMethod.POST,value="/myCards",consumes="application/json",produces = "application/json")
	public List<Cards> getCardsByCustomerId(@RequestBody Customer customer);
}
