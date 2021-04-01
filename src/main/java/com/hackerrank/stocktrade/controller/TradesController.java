package com.hackerrank.stocktrade.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hackerrank.stocktrade.exceptions.TradeAlreadyExistException;
import com.hackerrank.stocktrade.model.entity.Trade;
import com.hackerrank.stocktrade.model.service.ITradeService;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

	@Autowired
	private ITradeService tradeService;

	@PostMapping
	public void addTrade(@RequestBody Trade trade, HttpServletResponse response) {
		try {
			tradeService.add(trade);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (TradeAlreadyExistException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade Already Exist");
		}
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Trade get(@PathVariable Long id) {
		Trade trade = tradeService.get(id);
		if (trade == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trade not found");
		}
		return trade;
	}
	
	@GetMapping("/users/{userID}")
	@ResponseBody
	public List<Trade> getByUser(@PathVariable(name = "userID") Long id) {
		List<Trade> trade = tradeService.getByUserId(id);
		if (trade == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trades not found");
		}
		return trade;
	}
	
	@GetMapping()
	@ResponseBody
	public List<Trade> getAll(HttpServletResponse response) {
		List<Trade> trades = tradeService.getAll();
		response.setStatus(HttpServletResponse.SC_OK);
		return trades;
	}
	
	@GetMapping("/stocks/{stockSymbol}")
	@ResponseBody
	public List<Trade> tradesByStockSymbol(@PathVariable(name = "stockSymbol") String stockSymbol, 
			@RequestParam(required = false) String type, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
		List<Trade> trades = tradeService.getByStockSymbol(stockSymbol, type, start, end);
		if (CollectionUtils.isEmpty(trades)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trades not found");
		}
	    return trades;
	}

}
