package com.hackerrank.stocktrade.model.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hackerrank.stocktrade.model.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>, TradeRepositoryCustom {

	@Query("SELECT t FROM Trade t WHERE t.user.id = LOWER(:userId)")
	public List<Trade> getByUserId(@Param("userId") Long userId);
	
	@Query("SELECT max(t.price) as max, min(t.price) as min, symbol as symbol  FROM Trade t WHERE t.symbol = :symbol and t.timestamp between :start and :end "
			+ "group by symbol")
	public Map<String, Object> getPricesBySymbolAndTimestampBetween(@Param("symbol") String symbol, @Param("start") Date start, @Param("end") Date end);
	
}
