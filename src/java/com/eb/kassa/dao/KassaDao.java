package com.eb.kassa.dao;

import java.util.Date;
import java.util.List;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.InvoiceType;
import com.eb.kassa.beans.KassaItem;
import com.eb.kassa.beans.User;

public interface KassaDao extends AbstractDao<KassaItem> {

	List<KassaItem> find(InvoiceType type);

	List<KassaItem> find(InvoiceType type, Currency currency);

	List<KassaItem> find(User user, String creditor, String comments,
			Date from, Date to, String orderBy, String orderType,
			InvoiceType... type);

	List<KassaItem> find(InvoiceType type, User user, String creditor,
			String comments, Date from, Date to, Currency currency);

	List<String> findAllCreditors();

	List<Currency> findAllCurrencies();

	KassaItem findLastWithComment();

	void removeAll();
}
