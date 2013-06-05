package com.eb.kassa.dao;

import com.eb.kassa.beans.Rate;

public interface RateDao extends AbstractDao<Rate> {

	public Rate find(Integer currency1, Integer currency2);

}
