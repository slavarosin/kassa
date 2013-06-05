package com.eb.kassa.beans;

import java.math.BigDecimal;
import java.util.Date;

public class BankRate {

	private String currency;

	private BigDecimal rate;

	private Date updated;

	public BankRate() {
		super();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}