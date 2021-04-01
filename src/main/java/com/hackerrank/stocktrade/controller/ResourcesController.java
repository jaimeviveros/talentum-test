package com.hackerrank.stocktrade.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.model.service.ITradeService;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
    
	@Autowired
	private ITradeService tradeService;
	
	@DeleteMapping
	public void deleteAll(HttpServletResponse response) {
		tradeService.deleteAll();
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
