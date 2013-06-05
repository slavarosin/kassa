package com.eb.kassa.web.admin;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.Utils;
import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.InvoiceType;
import com.eb.kassa.beans.KassaItem;
import com.eb.kassa.beans.User;
import com.eb.kassa.service.CurrencyService;
import com.eb.kassa.service.KassaService;

@Controller
@RequestMapping("/manage/balance")
public class BalanceController {

	@Autowired
	private KassaService kassaService;

	@Autowired
	private CurrencyService currencyService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		model.addAttribute("balance", kassaService.getInitialBalance());
		model.addAttribute("currencies", currencyService.getCurrencies());

		return "balance";
	}

	@RequestMapping(method = RequestMethod.POST, params = { "balance",
			"currency" })
	public String init(@RequestParam("balance") String balance,
			@RequestParam("currency") Integer currency, final ModelMap model,
			final HttpSession session) {

		Currency curr = currencyService.getCurrency(currency);
		User user = (User) session.getAttribute(User.class.getSimpleName());

		KassaItem kassaInitialBalace = new KassaItem();
		// kassaInitialBalace.setId(1);
		kassaInitialBalace.setAmount(Utils.toBigDecimal(balance));
		kassaInitialBalace.setCurrencyFrom(curr);
		kassaInitialBalace.setRate(new BigDecimal(1));
		kassaInitialBalace.setCurrencyTo(curr);
		kassaInitialBalace.setType(InvoiceType.INITIAL);
		kassaInitialBalace.setDate(new Date());
		kassaInitialBalace.setCreatedBy(user);
		kassaInitialBalace.setComments("initial value");

		kassaService.clear();
		kassaService.store(kassaInitialBalace);

		return setupForm(model);
	}
}