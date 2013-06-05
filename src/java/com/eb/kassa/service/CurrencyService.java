package com.eb.kassa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.Rate;

public interface CurrencyService {

	List<Currency> getCurrencies();

	Currency getCurrency(String name);

	void removeCurrency(Integer id);

	Currency getCurrency(Integer id);

	void addCurrency(Currency currency);

	List<Rate> getRates();

	Rate getRate(Integer currency1, Integer currency2);

	void addRate(Rate rate);

	void removeRate(Integer id);

	Map<Currency, BigDecimal> convertToOtherCurrencies(
			Map<Currency, BigDecimal> sum);

	Map<Currency, BigDecimal> add(Map<Currency, BigDecimal> item1,
			Map<Currency, BigDecimal> item2);

	void setDefaultCurrency(Integer id);

}