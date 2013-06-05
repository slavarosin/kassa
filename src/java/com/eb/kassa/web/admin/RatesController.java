package com.eb.kassa.web.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.Utils;
import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.Rate;
import com.eb.kassa.service.CurrencyService;
import com.eb.kassa.service.RateService;

@Controller
@RequestMapping("/manage/rates")
public class RatesController {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private RateService rateService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {
		Date updated = rateService.updateRates();
		model.addAttribute("updated", updated);

		List<Currency> currencies = currencyService.getCurrencies();
		List<Rate> rates = currencyService.getRates();

		for (Rate rate : rates) {
			rate.setCurr1(currencyService.getCurrency(rate.getCurrency1()));
			rate.setCurr2(currencyService.getCurrency(rate.getCurrency2()));
		}

		model.addAttribute("currencies", currencies);
		model.addAttribute("rates", rates);

		return "rates";
	}

	@RequestMapping(method = RequestMethod.POST, params = { "currency1",
			"currency2", "rate" })
	public String updateRate(
			@RequestParam("currency1") final Integer currency1,
			@RequestParam("currency2") final Integer currency2,
			@RequestParam("rate") String rate, final ModelMap model) {
		Rate r = currencyService.getRate(currency1, currency2);
		r.setRate(Utils.toBigDecimal(rate));

		currencyService.addRate(r);

		return setupForm(model);
	}
}