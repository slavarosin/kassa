package com.eb.kassa.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.InvoiceType;
import com.eb.kassa.beans.KassaItem;
import com.eb.kassa.beans.StatsType;
import com.eb.kassa.service.CommentService;
import com.eb.kassa.service.CurrencyService;
import com.eb.kassa.service.KassaService;
import com.eb.kassa.service.UserService;

@Controller
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private KassaService kassaService;

	@Autowired
	private UserService userService;

	@Autowired
	private DateFormat dateFormat;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {
		model.addAttribute("types", StatsType.values());
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("creditors", kassaService.getCreditors());
		model.addAttribute("comments", commentService.getComments());

		ModelUtils.initFilterDates(model, dateFormat);

		return "stats";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String showStats(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "comment", required = false) String comment,
			@RequestParam(value = "user", required = false) Integer user,
			@RequestParam(value = "creditor", required = false) String creditor,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "orderType", required = false) String orderType,
			final ModelMap model) {
		StatsType statsType = StatsType.valueOf(type);

		Date dateFrom = null;
		if (StringUtils.isNotEmpty(from)) {
			try {
				dateFrom = dateFormat.parse(from);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		Date dateTo = null;
		if (StringUtils.isNotEmpty(to)) {
			try {
				dateTo = dateFormat.parse(to);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		InvoiceType invoiceType = null;
		List<KassaItem> items = new ArrayList<KassaItem>();
		Map<Currency, BigDecimal> sum;
		Map<Currency, BigDecimal> income;
		Map<Currency, BigDecimal> outcome;
		Map<Currency, BigDecimal> credit;
		Map<Currency, BigDecimal> creditback;
		Map<Currency, BigDecimal> devident;

		switch (statsType) {
		case TURNOVER:
			items = kassaService.getItems(userService.getUser(user), creditor,
					comment, dateFrom, dateTo, orderBy, orderType,
					InvoiceType.INCOME, InvoiceType.OUTCOME,
					InvoiceType.CREDIT, InvoiceType.CREDITBACK,
					InvoiceType.DIVIDEND, InvoiceType.INITIAL);

			income = kassaService.getKassa(InvoiceType.INCOME, userService
					.getUser(user), creditor, comment, dateFrom, dateTo);
			outcome = kassaService.getKassa(InvoiceType.OUTCOME, userService
					.getUser(user), creditor, comment, dateFrom, dateTo);
			credit = kassaService.getKassa(InvoiceType.CREDIT, userService
					.getUser(user), creditor, comment, dateFrom, dateTo);
			creditback = kassaService.getKassa(InvoiceType.CREDITBACK,
					userService.getUser(user), creditor, comment, dateFrom,
					dateTo);
			devident = kassaService.getKassa(InvoiceType.DIVIDEND, userService
					.getUser(user), creditor, comment, dateFrom, dateTo);

			sum = currencyService.add(income, outcome);
			sum = currencyService.add(sum, credit);
			sum = currencyService.add(sum, creditback);
			sum = currencyService.add(sum, devident);

			break;
		case PROFIT:
			income = kassaService.getKassa(InvoiceType.INCOME, userService
					.getUser(user), creditor, comment, dateFrom, dateTo);
			outcome = kassaService.getKassa(InvoiceType.OUTCOME, userService
					.getUser(user), creditor, comment, dateFrom, dateTo);

			sum = currencyService.add(income, outcome);
			break;

		case CREDITREST:
			credit = kassaService.getKassa(InvoiceType.CREDIT, userService
					.getUser(user), creditor, comment, dateFrom, dateTo);
			creditback = kassaService.getKassa(InvoiceType.CREDITBACK,
					userService.getUser(user), creditor, comment, dateFrom,
					dateTo);

			sum = currencyService.add(credit, creditback);

			break;
		default:
			invoiceType = InvoiceType.valueOf(type);
			items = kassaService.getItems(userService.getUser(user), creditor,
					comment, dateFrom, dateTo, orderBy, orderType, invoiceType);
			sum = kassaService.getKassa(invoiceType, userService.getUser(user),
					creditor, comment, dateFrom, dateTo);
		}

		model.addAttribute("type", type);
		model.addAttribute("creditor", creditor);
		model.addAttribute("user", user);
		model.addAttribute("comment", comment);
		model.addAttribute("from", from);
		model.addAttribute("to", to);
		model.addAttribute("sum", sum);
		model.addAttribute("items", items);
		model.addAttribute("otherCurrencies", currencyService
				.convertToOtherCurrencies(sum));

		return setupForm(model);
	}
}