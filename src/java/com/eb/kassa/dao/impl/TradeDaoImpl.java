package com.eb.kassa.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eb.kassa.beans.TradeItem;
import com.eb.kassa.beans.TradeMilestone;
import com.eb.kassa.beans.User;
import com.eb.kassa.dao.TradeDao;

@Repository
public class TradeDaoImpl extends AbstractDaoImpl<TradeItem> implements
		TradeDao {

	@Autowired
	public TradeDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, TradeItem.class);
	}

	@SuppressWarnings("unchecked")
	public List<TradeItem> find(TradeMilestone milestone, User user) {
		Criteria criteria = createCriteria();
		if (milestone != null)
			criteria.add(Restrictions.eq("milestone", milestone));
		if (user != null)
			criteria.add(Restrictions.eq("createdBy", user));
		return criteria.list();
	}
}
