package com.eb.kassa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.InvoiceType;
import com.eb.kassa.beans.KassaItem;
import com.eb.kassa.beans.User;

public interface KassaService {

	KassaItem getInitialBalance();

	void store(KassaItem item);

	Map<Currency, BigDecimal> getKassa();

	List<String> getCreditors();

	Map<Currency, BigDecimal> getKassa(InvoiceType type, User user,
			String creditor, String comments, Date from, Date to);

	List<KassaItem> getItems(User user, String creditor, String comments,
			Date from, Date to, String orderBy, String orderType,
			InvoiceType... type);

	KassaItem getItem(Integer id);

	String getLastComment();

	void clear();

	void remove(Integer id);

}