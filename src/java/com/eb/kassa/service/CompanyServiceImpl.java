package com.eb.kassa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eb.kassa.beans.Company;
import com.eb.kassa.dao.CompanyDao;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

	private final CompanyDao companyDao;

	@Autowired
	public CompanyServiceImpl(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public List<Company> getCompanies() {
		return companyDao.findAll();
	}

	@Transactional
	public void remove(Integer id) {
		companyDao.remove(id);
	}

	public Company get(Integer id) {
		return companyDao.find(id);
	}

	@Transactional
	public void store(Company company) {
		companyDao.save(company);
	}
}
