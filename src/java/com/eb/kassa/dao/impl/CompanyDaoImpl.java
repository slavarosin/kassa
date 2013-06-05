package com.eb.kassa.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eb.kassa.beans.Company;
import com.eb.kassa.dao.CompanyDao;

@Repository
public class CompanyDaoImpl extends AbstractDaoImpl<Company> implements
		CompanyDao {

	@Autowired
	public CompanyDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Company.class);
	}
}
