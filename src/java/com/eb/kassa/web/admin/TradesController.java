package com.eb.kassa.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.beans.TradeItem;
import com.eb.kassa.service.TradeService;

@Controller
@RequestMapping("/manage/trades")
public class TradesController {

	@Autowired
	private TradeService tradeService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		List<TradeItem> trades = tradeService.getTrades();

		model.addAttribute("trades", trades);

		return "trades";
	}

	@RequestMapping(method = RequestMethod.POST, params = { "id" })
	public String updateTradeItemRate(@RequestParam("id")
	final Integer id, @RequestParam(value = "trackingID", required = false)
	final String trackingID,
			@RequestParam(value = "received", required = false)
			final Boolean received, final ModelMap model) {

		TradeItem item = tradeService.getTrade(id);
		item.setTrackingID(trackingID);
		if (received == null) {
			item.setReceived(Boolean.FALSE);
		} else {
			item.setReceived(received);
		}

		tradeService.storeTrade(item);

		return setupForm(model);
	}
}