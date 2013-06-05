package com.eb.kassa.dao;

import java.util.List;

import com.eb.kassa.beans.TradeItem;
import com.eb.kassa.beans.TradeMilestone;
import com.eb.kassa.beans.User;

public interface TradeDao extends AbstractDao<TradeItem> {

	List<TradeItem> find(TradeMilestone milestone, User user);

}
