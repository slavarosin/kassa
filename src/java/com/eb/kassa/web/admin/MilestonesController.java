package com.eb.kassa.web.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eb.kassa.beans.TradeMilestone;
import com.eb.kassa.service.TradeService;

@Controller
@RequestMapping("/manage/milestones")
public class MilestonesController {

	@Autowired
	private TradeService tradeService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		model.addAttribute("milestones", tradeService.getMilestones());

		return "milestones";
	}

	@RequestMapping(method = RequestMethod.GET, params = { "addmilestone" })
	public String addMilestone(final ModelMap model) {

		TradeMilestone lastMilestone = tradeService.getLastMilestone();
		if (lastMilestone != null) {
			lastMilestone.setDate(new Date());
			tradeService.storeMilestone(lastMilestone);
		}

		TradeMilestone milestone = new TradeMilestone();
		tradeService.storeMilestone(milestone);

		return setupForm(model);
	}
}