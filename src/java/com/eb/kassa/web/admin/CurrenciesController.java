package com.eb.kassa.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.Rate;
import com.eb.kassa.service.CurrencyService;
import com.eb.kassa.service.RateService;

@Controller
@RequestMapping("/manage/currencies")
public class CurrenciesController {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private RateService rateService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		model.addAttribute("currencies", currencyService.getCurrencies());

		return "currencies";
	}

	@RequestMapping(method = RequestMethod.GET, params = { "remove" })
	public String removeCurrency(@RequestParam("remove") final Integer id,
			final ModelMap model) {

		currencyService.removeCurrency(id);

		removeRates();

		return setupForm(model);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "first" })
	public String makeDefaultCurrency(@RequestParam("first") final Integer id,
			final ModelMap model) {

		currencyService.setDefaultCurrency(id);

		return setupForm(model);
	}

	private void removeRates() {
		List<Rate> rates = currencyService.getRates();

		for (Rate rate : rates) {
			if (currencyService.getCurrency(rate.getCurrency1()) == null)
				currencyService.removeRate(rate.getId());

			if (currencyService.getCurrency(rate.getCurrency2()) == null)
				currencyService.removeRate(rate.getId());
		}
	}

	@RequestMapping(method = RequestMethod.POST, params = { "name", "sign" })
	public String addCurrency(@RequestParam("name") final String name,
			@RequestParam("sign") final String sign, final ModelMap model) {

		Currency currency = new Currency();
		currency.setName(name);
		currency.setSign(sign);

		currencyService.addCurrency(currency);
		rateService.updateRates();

		return setupForm(model);
	}
}