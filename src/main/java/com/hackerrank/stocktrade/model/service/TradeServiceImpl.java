package com.hackerrank.stocktrade.model.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.exceptions.TradeAlreadyExistException;
import com.hackerrank.stocktrade.model.dao.UserDao;
import com.hackerrank.stocktrade.model.entity.StockPriceSymbol;
import com.hackerrank.stocktrade.model.entity.Trade;
import com.hackerrank.stocktrade.model.repository.TradeRepository;

@Service
public class TradeServiceImpl implements ITradeService {

	@Autowired
	private TradeRepository tradeDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void add(Trade trade) {
		userDao.save(trade.getUser());
		
		if (tradeDao.findById(trade.getId()).isPresent()) {
			throw new TradeAlreadyExistException(); 
		}
		tradeDao.save(trade);
	}

	@Override
	public void deleteAll() {
		tradeDao.deleteAll();
	}

	@Override
	public Trade get(Long id) {
		return tradeDao.findById(id).orElse(null);
	}

	@Override
	public List<Trade> getAll() {
		return (List<Trade>) tradeDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	@Override
	public List<Trade> getByUserId(Long userId) {
		return tradeDao.getByUserId(userId);
	}

	@Override
	public List<Trade> getByStockSymbol(String stockSymbol, String type, Date start, Date end) {
		return tradeDao.getBySymbolAndTypeAndTimestampBetween(stockSymbol, type, start, end);
	}

	@Override
	public StockPriceSymbol getPricesByStockSymbol(String stockSymbol, Date start, Date end) {
		StockPriceSymbol stockPriceSymbol = null;
		//
		Map<String, Object> values = tradeDao.getPricesBySymbolAndTimestampBetween(stockSymbol, start, end); 
		if (values != null && !values.isEmpty()) {
			Float min = values.get("min") != null ? Float.valueOf(values.get("min").toString()) : 0;
			Float max = values.get("max") != null ? Float.valueOf(values.get("max").toString()) : 0;
			String symbol = values.get("symbol").toString();
			stockPriceSymbol = new StockPriceSymbol();
			stockPriceSymbol.setMinPrice(min);
			stockPriceSymbol.setMaxPrice(max);
			stockPriceSymbol.setSymbol(symbol);
		}
		//
		return stockPriceSymbol;
	}

}
