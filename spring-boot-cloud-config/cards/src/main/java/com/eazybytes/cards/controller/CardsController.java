package com.eazybytes.cards.controller;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.CardsConfig;
import com.eazybytes.DBConfiguration;
import com.eazybytes.cards.model.Cards;
import com.eazybytes.cards.model.Customer;
import com.eazybytes.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class CardsController {
  
	@Autowired
	CardsRepository cardsRepository;
	@Autowired
	CardsConfig cardsConfig;
	@Autowired
	DBConfiguration dbConfig;
	@PostMapping("/myCards")
	public List<Cards> getCardsByCustomerId(@RequestBody Customer customer){
		List<Cards> cardsList=cardsRepository.findByCustomerId(customer.getCustomerId());
		cardsList.forEach((e)->System.out.println(e));
		//System.out.println(" cardsList.size():"+cardsList.forEach(()-););
		if(cardsList.size()>0)
			return cardsList;
		return null;
	}
	@GetMapping("/cards/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties();
		
		properties.put("msg",cardsConfig.getMsg());
		properties.put("build-Version",cardsConfig.getBuildVersion());
		properties.putAll(cardsConfig.getMailDetails());
		properties.put("activeBranches",cardsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}
	@GetMapping("/cards/dbproperties")
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
