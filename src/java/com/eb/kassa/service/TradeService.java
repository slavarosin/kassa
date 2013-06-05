package com.eb.kassa.service;

import java.math.BigDecimal;
import java.util.List;

import com.eb.kassa.beans.TradeItem;
import com.eb.kassa.beans.TradeMilestone;
import com.eb.kassa.beans.User;

public interface TradeService {

	TradeMilestone getLastMilestone();

	void storeTrade(TradeItem item);

	List<TradeMilestone> getMilestones();

	void storeMilestone(TradeMilestone milestone);

	BigDecimal getLastPrice();

	List<TradeItem> getTrades();

	TradeItem getTrade(Integer id);

	List<TradeItem> getTrades(Integer milestone, User user);

}