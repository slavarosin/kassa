package com.eb.kassa.web.admin;

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
import com.eb.kassa.beans.KassaItem;
import com.eb.kassa.beans.User;
import com.eb.kassa.service.CommentService;
import com.eb.kassa.service.CurrencyService;
import com.eb.kassa.service.KassaService;
import com.eb.kassa.service.UserService;
import com.eb.kassa.web.ModelUtils;

@Controller
@RequestMapping("/manage/kassa")
public class KassaItemsController {

	@Autowired
	private KassaService kassaService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private UserService userService;

	@Autowired
	private DateFormat dateFormat;

	@Autowired
	private CommentService commentService;

	private Date dateFrom;

	private User user;

	private Date dateTo;

	private String comment;

	@RequestMapping(method = RequestMethod.GET)
	public String getItems(final ModelMap model) {

		List<KassaItem> items = kassaService.getItems(user, null, comment,
				dateFrom, dateTo, null, null);

		model.addAttribute("items", items);
		model.addAttribute("currencies", currencyService.getCurrencies());
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("dateFrom", dateFrom);
		model.addAttribute("dateTo", dateTo);
		model.addAttribute("comment", comment);
		model.addAttribute("user", user);
		model.addAttribute("comments", commentService.getComments());

		ModelUtils.initFilterDates(model, dateFormat);

		List<String> creditors = kassaService.getCreditors();
		model.addAttribute("creditors", creditors);

		return "items";
	}

	@RequestMapping(method = RequestMethod.POST, params = "filter=true")
	public String getItemsWithFilter(final ModelMap model, HttpSession session,
			@RequestParam(value = "comment", required = false) String comment,
			@RequestParam(value = "user", required = false) Integer user,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to) {

		if (StringUtils.isNotEmpty(from)) {
			try {
				dateFrom = dateFormat.parse(from);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty(to)) {
			try {
				dateTo = dateFormat.parse(to);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		this.comment = comment;
		this.user = userService.getUser(user);

		return getItems(model);
	}

	@RequestMapping(method = RequestMethod.POST, params = "id")
	public String updateKassaItem(
			@RequestParam("id") final Integer id,
			@RequestParam(value = "amount", required = false) final String amount,
			@RequestParam(value = "currencyFrom", required = false) final Integer currencyFrom,
			@RequestParam(value = "rate", required = false) final String rate,
			@RequestParam(value = "currencyTo", required = false) final Integer currencyTo,
			@RequestParam(value = "user", required = false) final Integer user,
			@RequestParam(value = "comments", required = false) final String comments,
			@RequestParam(value = "userComments", required = false) final String userComments,
			@RequestParam(value = "standardCreditor", required = false) final String standardCreditor,
			@RequestParam(value = "creditor", required = false) final String creditor,
			final ModelMap model) {

		KassaItem item = kassaService.getItem(id);

		item.setAmount(Utils.toBigDecimal(amount));
		if (amount.startsWith("-")) {
			item.setAmount(item.getAmount().negate());
		}
		item.setCurrencyFrom(currencyService.getCurrency(currencyFrom));
		item.setRate(Utils.toBigDecimal(rate));
		item.setCurrencyTo(currencyService.getCurrency(currencyTo));
		item.setCreatedBy(userService.getUser(user));

		if ((standardCreditor != null) && (standardCreditor.length() > 0)) {
			item.setCreditor(standardCreditor);
		}
		if ((creditor != null) && (creditor.length() > 0)) {
			item.setCreditor(creditor);
		}

		if (comments != null)
			item.setComments(comments);
		if (userComments != null)
			item.setUserComments(userComments);

		kassaService.store(item);

		return getItems(model);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "remove" })
	public String removeItem(@RequestParam("remove")
	final Integer id, final ModelMap model) {

		kassaService.remove(id);

		return getItems(model);
	}
}
