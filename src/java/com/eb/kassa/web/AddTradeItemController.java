package com.eb.kassa.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.Utils;
import com.eb.kassa.beans.TradeItem;
import com.eb.kassa.beans.TradeMilestone;
import com.eb.kassa.beans.User;
import com.eb.kassa.service.TradeService;

@Controller
@RequestMapping("/addtrade")
public class AddTradeItemController {

	@Autowired
	private TradeService tradeService;

	private TradeMilestone milestone;

	@Autowired
	private DateFormat formatter;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model, final HttpSession session) {
		model.addAttribute("now", formatter.format(new Date()));

		BigDecimal price = tradeService.getLastPrice();
		model.addAttribute("price", price);

		milestone = tradeService.getLastMilestone();
		if (milestone == null) {
			model.addAttribute("milestone", 1);
		} else {
			model.addAttribute("milestone", milestone.getId());
		}

		return "addtrade";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@RequestParam("amount")
	Integer amount, @RequestParam("price")
	String price, @RequestParam("company")
	String company, @RequestParam("trackingID")
	String trackingID, @RequestParam(value = "received", required = false)
	Boolean received, @RequestParam("date")
	String dateString, final HttpSession session) {

		if (received == null)
			received = Boolean.FALSE;

		Date date;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}

		TradeItem item = new TradeItem();
		item.setAmount(amount);
		item.setPrice(Utils.toBigDecimal(price));
		item.setMilestone(milestone);
		item.setCreatedBy((User) session.getAttribute(User.class
				.getSimpleName()));
		item.setDate(date);
		item.setCompany(company);
		item.setTrackingID(trackingID);
		item.setReceived(received);

		tradeService.storeTrade(item);

		return "redirect:kassa";
	}
}