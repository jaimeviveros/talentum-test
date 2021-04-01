package com.hackerrank.stocktrade.model.entity;

import java.io.Serializable;

public class StockPriceSymbol implements Serializable {

	private static final long serialVersionUID = -3323901745132428671L;

	private Float maxPrice;
	private Float minPrice;
	private String symbol;

	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
