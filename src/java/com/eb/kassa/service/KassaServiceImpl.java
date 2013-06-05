package com.eb.kassa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.InvoiceType;
import com.eb.kassa.beans.KassaItem;
import com.eb.kassa.beans.User;
import com.eb.kassa.dao.KassaDao;

@Service
@Transactional(readOnly = true)
public class KassaServiceImpl implements KassaService {

	private final KassaDao kassaDao;

	@Autowired
	public KassaServiceImpl(KassaDao kassaDao) {
		this.kassaDao = kassaDao;
	}

	public KassaItem getInitialBalance() {
		List<KassaItem> initials = kassaDao.find(InvoiceType.INITIAL);
		if (initials.size() == 0)
			return null;
		return initials.get(0);
	}

	@Transactional
	public void store(KassaItem item) {
		kassaDao.save(item);
	}

	public Map<Currency, BigDecimal> getKassa() {
		Map<Currency, BigDecimal> kassa = new HashMap<Currency, BigDecimal>();
		List<Currency> currencies = getKassaCurrencies();

		for (Currency currency : currencies) {
			BigDecimal sum = new BigDecimal(0);

			sum = putAllToKassa(sum, InvoiceType.INITIAL, currency);
			sum = putAllToKassa(sum, InvoiceType.INCOME, currency);
			sum = putAllToKassa(sum, InvoiceType.OUTCOME, currency);
			sum = putAllToKassa(sum, InvoiceType.CREDIT, currency);
			sum = putAllToKassa(sum, InvoiceType.CREDITBACK, currency);
			sum = putAllToKassa(sum, InvoiceType.DIVIDEND, currency);

			if (sum.longValue() != 0)
				kassa.put(currency, sum);
		}

		return kassa;
	}

	private List<Currency> getKassaCurrencies() {
		return kassaDao.findAllCurrencies();
	}

	private BigDecimal putAllToKassa(BigDecimal kassa, InvoiceType type,
			Currency currency) {
		List<KassaItem> items = kassaDao.find(type, currency);

		BigDecimal sum = countItems(items);
		kassa = kassa.add(sum);

		return kassa;
	}

	private BigDecimal countItems(List<KassaItem> items) {
		double sum = 0;
		for (KassaItem item : items) {
			BigDecimal v = item.getAmount().multiply(item.getRate());
			sum += v.doubleValue();
		}
		return new BigDecimal(sum);
	}

	public List<String> getCreditors() {
		return kassaDao.findAllCreditors();
	}

	public Map<Currency, BigDecimal> getKassa(InvoiceType type, User user,
			String creditor, String comments, Date from, Date to) {
		Map<Currency, BigDecimal> kassa = new HashMap<Currency, BigDecimal>();
		List<Currency> currencies = getKassaCurrencies();

		for (Currency currency : currencies) {
			List<KassaItem> items = getItems(type, user, creditor, comments,
					from, to, currency);
			BigDecimal sum = countItems(items);

			if (sum.longValue() != 0)
				kassa.put(currency, sum);
		}

		return kassa;
	}

	private List<KassaItem> getItems(InvoiceType type, User user,
			String creditor, String comments, Date from, Date to,
			Currency currency) {
		return kassaDao
				.find(type, user, creditor, comments, from, to, currency);
	}

	public List<KassaItem> getItems(User user, String creditor,
			String comments, Date from, Date to, String orderBy,
			String orderType, InvoiceType... type) {
		return kassaDao.find(user, creditor, comments, from, to, orderBy,
				orderType, type);
	}

	public KassaItem getItem(Integer id) {
		return kassaDao.find(id);
	}

	public String getLastComment() {
		KassaItem item = kassaDao.findLastWithComment();
		if (item == null)
			return null;
		return item.getComments();
	}

	@Transactional
	public void clear() {
		kassaDao.removeAll();
	}

	@Transactional
	public void remove(Integer id) {
		kassaDao.remove(id);
	}
}