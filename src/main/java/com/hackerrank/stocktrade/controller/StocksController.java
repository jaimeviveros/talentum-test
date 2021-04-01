package com.hackerrank.stocktrade.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hackerrank.stocktrade.model.entity.StockPriceSymbol;
import com.hackerrank.stocktrade.model.service.ITradeService;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {
	
	@Autowired
	private ITradeService tradeService;
	
	@GetMapping("/{stockSymbol}/price")
	@ResponseBody
	public StockPriceSymbol stockSymbolPrices(@PathVariable(name = "stockSymbol") String stockSymbol, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start, 
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end) {
		//
		StockPriceSymbol prices = tradeService.getPricesByStockSymbol(stockSymbol, start, end);
		//
		if (prices == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trades not found");
		}
	    return prices;
	}
    
}
