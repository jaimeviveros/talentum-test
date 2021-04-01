package com.hackerrank.stocktrade.model.repository;

import java.util.Date;
import java.util.List;

import com.hackerrank.stocktrade.model.entity.Trade;

public interface TradeRepositoryCustom {
	
	public List<Trade> getBySymbolAndTypeAndTimestampBetween(String symbol, String type, Date start, Date end);
	
}
