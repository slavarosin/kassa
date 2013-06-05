package com.eb.kassa.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.beans.Company;
import com.eb.kassa.service.CompanyService;

@Controller
@RequestMapping("/manage/companies")
public class CompaniesController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		model.addAttribute("companies", companyService.getCompanies());

		return "companies";
	}

	@RequestMapping(method = RequestMethod.GET, params = "remove")
	public String removecompany(@RequestParam("remove") final Integer id,
			final ModelMap model) {

		companyService.remove(id);

		return setupForm(model);
	}

	@RequestMapping(method = RequestMethod.POST, params = "name")
	public String addcompany(@RequestParam("name") final String name,
			final ModelMap model) {

		Company company = new Company();
		company.setName(name);
		companyService.store(company);

		return setupForm(model);
	}
}