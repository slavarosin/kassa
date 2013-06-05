package com.eb.kassa.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.Utils;
import com.eb.kassa.beans.InvoiceType;
import com.eb.kassa.beans.KassaItem;
import com.eb.kassa.beans.User;
import com.eb.kassa.service.CommentService;
import com.eb.kassa.service.CompanyService;
import com.eb.kassa.service.CurrencyService;
import com.eb.kassa.service.KassaService;

@Controller
@RequestMapping("/add")
public class AddKassaItemController {

	@Autowired
	private KassaService kassaService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CompanyService companyService;

	private InvoiceType invoiceType;

	@Autowired
	private DateFormat formatter;

	@RequestMapping(method = RequestMethod.GET, params = { "type" })
	public String setupForm(@RequestParam("type") String type,
			final ModelMap model, final HttpSession session) {
		this.invoiceType = InvoiceType.valueOf(type.toUpperCase());

		model.addAttribute("currencies", currencyService.getCurrencies());
		model.addAttribute("type", invoiceType);
		model.addAttribute("now", formatter.format(new Date()));
		model.addAttribute("comments", commentService.getComments());
		model.addAttribute("companies", companyService.getCompanies());
		String lastComment = kassaService.getLastComment();
		if (lastComment != null)
			model.addAttribute("standardComment", lastComment);
		model.addAttribute("rates", currencyService.getRates());

		if (invoiceType.equals(InvoiceType.CREDIT)
				|| invoiceType.equals(InvoiceType.CREDITBACK)) {
			List<String> creditors = kassaService.getCreditors();
			model.addAttribute("creditors", creditors);
		}

		return "additem";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addItem(
			@RequestParam("amount") String amount,
			@RequestParam("currencyFrom") Integer currencyFrom,
			@RequestParam(value = "rate", required = false) String rate,
			@RequestParam(value = "currencyTo", required = false) Integer currencyTo,
			@RequestParam("date") String dateString,
			@RequestParam(value = "comments", required = false) String comments,
			@RequestParam(value = "standardComment", required = false) String standardComment,
			@RequestParam(value = "creditor", required = false) String creditor,
			@RequestParam(value = "company", required = false) String company,
			final HttpSession session) {

		Date date;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}

		KassaItem item = new KassaItem();
		BigDecimal a = Utils.toBigDecimal(amount);
		if (!invoiceType.isIn())
			a = a.negate();
		item.setAmount(a);
		item.setType(invoiceType);
		item.setCurrencyFrom(currencyService.getCurrency(currencyFrom));
		if (rate == null) {
			item.setRate(new BigDecimal(1));
			item.setCurrencyTo(currencyService.getCurrency(currencyFrom));
		} else {
			item.setRate(Utils.toBigDecimal(rate));
			item.setCurrencyTo(currencyService.getCurrency(currencyTo));
		}
		item.setCreatedBy((User) session.getAttribute(User.class
				.getSimpleName()));
		item.setDate(date);
		if (StringUtils.isNotEmpty(comments))
			item.setUserComments(comments);
		if (StringUtils.isNotEmpty(standardComment))
			item.setComments(standardComment);
		if (StringUtils.isNotEmpty(creditor))
			item.setCreditor(creditor);
		if (StringUtils.isNotEmpty(company))
			item.setCompany(company);

		kassaService.store(item);

		return "redirect:kassa";
	}
}