package com.eb.kassa.service;

import java.util.List;

import com.eb.kassa.beans.Company;

public interface CompanyService {

	List<Company> getCompanies();

	void remove(Integer id);

	Company get(Integer id);

	void store(Company company);

}