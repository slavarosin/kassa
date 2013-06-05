package com.eb.kassa.dao;

import com.eb.kassa.beans.Currency;

public interface CurrencyDao extends AbstractDao<Currency> {

	public Currency find(String name);

	public Currency findDefault();

}
