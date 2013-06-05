package com.eb.kassa.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eb.kassa.beans.Rate;
import com.eb.kassa.dao.RateDao;

@Repository
public class RateDaoImpl extends AbstractDaoImpl<Rate> implements RateDao {

	@Autowired
	public RateDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Rate.class);
	}

	public Rate find(Integer currency1, Integer currency2) {
		return (Rate) createCriteria().add(
				Restrictions.and(Restrictions.eq("currency1", currency1),
						Restrictions.eq("currency2", currency2)))
				.uniqueResult();
	}
}
