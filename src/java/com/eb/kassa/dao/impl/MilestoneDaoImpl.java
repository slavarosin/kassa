package com.eb.kassa.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eb.kassa.beans.TradeMilestone;
import com.eb.kassa.dao.MilestoneDao;

@Repository
public class MilestoneDaoImpl extends AbstractDaoImpl<TradeMilestone> implements
		MilestoneDao {

	@Autowired
	public MilestoneDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, TradeMilestone.class);
	}
}
