package com.eb.kassa.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.dao.CurrencyDao;

@Repository
public class CurrencyDaoImpl extends AbstractDaoImpl<Currency> implements
		CurrencyDao {

	@Autowired
	public CurrencyDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Currency.class);
	}

	public Currency find(final String name) {
		return (Currency) createCriteria().add(Restrictions.eq("name", name))
				.uniqueResult();
	}

	public Currency findDefault() {
		return (Currency) createCriteria().add(Restrictions.eq("def", true))
				.uniqueResult();
	}

	public List<Currency> findAll() {
		return (List<Currency>) createCriteria().addOrder(Order.desc("def"))
				.addOrder(Order.asc("code")).list();
	}
}
