package com.eb.kassa.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eb.kassa.beans.TradeItem;
import com.eb.kassa.beans.TradeMilestone;
import com.eb.kassa.beans.User;
import com.eb.kassa.dao.MilestoneDao;
import com.eb.kassa.dao.TradeDao;

@Service
@Transactional(readOnly = true)
public class TradeServiceImpl implements TradeService {

	private final TradeDao tradeDao;

	private final MilestoneDao milestoneDao;

	@Autowired
	public TradeServiceImpl(TradeDao tradeDao, MilestoneDao milestoneDao) {
		this.tradeDao = tradeDao;
		this.milestoneDao = milestoneDao;
	}

	@Transactional
	public void storeTrade(TradeItem item) {
		tradeDao.save(item);
	}

	public TradeMilestone getLastMilestone() {
		return milestoneDao.findLast();
	}

	@Transactional
	public void storeMilestone(TradeMilestone milestone) {
		milestoneDao.save(milestone);
	}

	public List<TradeMilestone> getMilestones() {
		return milestoneDao.findAll();
	}

	public BigDecimal getLastPrice() {
		TradeItem item = tradeDao.findLast();
		if (item == null)
			return null;
		return item.getPrice();
	}

	public List<TradeItem> getTrades() {
		return tradeDao.findAll();
	}

	public TradeItem getTrade(Integer id) {
		return tradeDao.find(id);
	}

	@Override
	public List<TradeItem> getTrades(Integer milestoneID, User user) {
		return tradeDao.find(milestoneDao.find(milestoneID), user);
	}
}
