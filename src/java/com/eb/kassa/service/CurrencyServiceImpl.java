package com.eb.kassa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.Rate;
import com.eb.kassa.dao.CurrencyDao;
import com.eb.kassa.dao.RateDao;

@Service
@Transactional(readOnly = true)
public class CurrencyServiceImpl implements CurrencyService {

	private final CurrencyDao currencyDao;

	private final RateDao rateDao;

	@Autowired
	public CurrencyServiceImpl(CurrencyDao currencyDao, RateDao rateDao) {
		this.currencyDao = currencyDao;
		this.rateDao = rateDao;
	}

	public List<Currency> getCurrencies() {
		return currencyDao.findAll();
	}

	public Currency getCurrency(String name) {
		return currencyDao.find(name);
	}

	@Transactional
	public void removeCurrency(Integer id) {
		currencyDao.remove(id);
	}

	public Currency getCurrency(Integer id) {
		return currencyDao.find(id);
	}

	@Transactional
	public void addCurrency(Currency currency) {
		currencyDao.save(currency);
	}

	public Rate getRate(Integer currency1, Integer currency2) {
		return rateDao.find(currency1, currency2);
	}

	public List<Rate> getRates() {
		return rateDao.findAll();
	}

	@Transactional
	public void addRate(Rate rate) {
		rateDao.save(rate);
	}

	@Transactional
	public void removeRate(Integer id) {
		rateDao.remove(id);
	}

	public Map<Currency, BigDecimal> convertToOtherCurrencies(
			Map<Currency, BigDecimal> sum) {
		Map<Currency, BigDecimal> otherCurrencies = new TreeMap<Currency, BigDecimal>(
				new Comparator<Currency>() {
					@Override
					public int compare(Currency c1, Currency c2) {
						if ((c1.getId() == null) & (c2.getId() == null))
							return 0;
						if (c1.getId() == null)
							return -1;
						if (c2.getId() == null)
							return 1;

						return c1.getId().compareTo(c2.getId());
					}

				});

		List<Currency> currencies = getCurrencies();

		for (Currency currency : currencies) {
			BigDecimal s = new BigDecimal(0);
			for (Currency sumCurrency : sum.keySet()) {
				BigDecimal n = sum.get(sumCurrency);

				if (!currency.equals(sumCurrency)) {
					Rate rate = getRate(sumCurrency.getId(), currency.getId());
					if (rate != null) {
						n = n.multiply(rate.getRate());
					}
				}

				s = s.add(n);
			}
			otherCurrencies.put(currency, s);
		}
		return otherCurrencies;
	}

	public Map<Currency, BigDecimal> add(Map<Currency, BigDecimal> item1,
			Map<Currency, BigDecimal> item2) {
		List<Currency> currencies = new ArrayList<Currency>();
		currencies.addAll(item1.keySet());
		for (Currency currency : item2.keySet()) {
			if (!currencies.contains(currency)) {
				currencies.add(currency);
			}
		}

		for (Currency currency : currencies) {
			BigDecimal s1 = item1.get(currency);
			if (s1 == null) {
				s1 = item2.get(currency);
			} else {
				BigDecimal s2 = item2.get(currency);

				if (s2 != null) {
					s1 = new BigDecimal(s1.doubleValue() + s2.doubleValue());
				}
			}
			item1.put(currency, s1);
		}

		return item1;
	}

	@Transactional
	public void setDefaultCurrency(Integer id) {
		Currency currency = currencyDao.findDefault();
		currency.setDef(false);
		currencyDao.save(currency);

		currency = currencyDao.find(id);
		currency.setDef(true);
		currencyDao.save(currency);
	}
}
