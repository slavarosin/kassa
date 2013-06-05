package com.eb.kassa.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.TradeItem;
import com.eb.kassa.service.CurrencyService;
import com.eb.kassa.service.TradeService;
import com.eb.kassa.service.UserService;

@Controller
@RequestMapping("/tradestats")
public class TradeStatsController {

	@Autowired
	private TradeService tradeService;

	@Autowired
	private UserService userService;

	@Autowired
	private CurrencyService currencyService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {
		model.addAttribute("milestones", tradeService.getMilestones());
		model.addAttribute("users", userService.getUsers());

		return "tradestats";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String showStats(
			@RequestParam(value = "milestone", required = false) Integer milestoneID,
			@RequestParam(value = "user", required = false) Integer user,
			final ModelMap model) {
		BigDecimal sum = new BigDecimal(0);
		List<TradeItem> items = tradeService.getTrades(milestoneID, userService
				.getUser(user));
		for (TradeItem tradeItem : items) {
			double s = tradeItem.getPrice().doubleValue()
					* tradeItem.getAmount();
			sum = sum.add(new BigDecimal(s));
		}

		model.addAttribute("milestone", milestoneID);
		model.addAttribute("user", user);
		model.addAttribute("sum", sum);
		if (items != null)
			model.addAttribute("items", items);

		Map<Currency, BigDecimal> sumPounds = new HashMap<Currency, BigDecimal>();
		sumPounds.put(currencyService.getCurrency("фунт"), sum);
		model.addAttribute("otherCurrencies", currencyService
				.convertToOtherCurrencies(sumPounds));

		return setupForm(model);
	}
}