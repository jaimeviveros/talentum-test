package com.hackerrank.stocktrade.model.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.hackerrank.stocktrade.model.entity.Trade;

public class TradeRepositoryCustomImpl implements TradeRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Trade> getBySymbolAndTypeAndTimestampBetween(String stockSymbol, String type, Date start, Date end) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Trade> query = cb.createQuery(Trade.class);
		Root<Trade> trades = query.from(Trade.class);
		Path<String> symbolPath = trades.get("symbol");
		//
		query.select(trades)
			.where(cb.equal(symbolPath, stockSymbol));
		//
		if (!StringUtils.isEmpty(type)) {
			Path<String> typePath = trades.get("type");
			query.where(cb.equal(typePath, type));
			System.out.println(type);
		}
		//
		if (start != null) {
			Expression<Timestamp> timestampPath = trades.get("timestamp").as(Timestamp.class);
			query.where(cb.greaterThanOrEqualTo(timestampPath, start));
		}
		//
		if (end != null) {
			Expression<Timestamp> timestampPath = trades.get("timestamp").as(Timestamp.class);
			query.where(cb.lessThanOrEqualTo(timestampPath, end));
			System.out.println(end);
		}
		//
		return entityManager.createQuery(query)
	            .getResultList();
	}

}
