package com.hackerrank.stocktrade.model.service;

import java.util.Date;
import java.util.List;

import com.hackerrank.stocktrade.model.entity.StockPriceSymbol;
import com.hackerrank.stocktrade.model.entity.Trade;

public interface ITradeService {
	
	public void add(Trade trade);
	
	public Trade get(Long id);
	
	public List<Trade> getByUserId(Long userId);
	
	public List<Trade> getByStockSymbol(String stockSymbol, String type, Date start, Date end);
	
	public StockPriceSymbol getPricesByStockSymbol(String stockSymbol, Date start, Date end);
	
	public List<Trade>  getAll();
	
	public void deleteAll();

}
