package com.eb.kassa.web;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.service.CurrencyService;
import com.eb.kassa.service.KassaService;

@Controller
@RequestMapping("/kassa")
public class KassaController {

	@Autowired
	private KassaService kassaService;

	@Autowired
	private CurrencyService currencyService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		Map<Currency, BigDecimal> kassa = kassaService.getKassa();
		Map<Currency, BigDecimal> otherCurrencies = currencyService
				.convertToOtherCurrencies(kassa);

		model.addAttribute("kassa", kassa);
		model.addAttribute("otherCurrencies", otherCurrencies);

		return "kassa";
	}
}