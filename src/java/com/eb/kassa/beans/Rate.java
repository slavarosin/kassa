package com.eb.kassa.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "rates")
@Transactional
public class Rate implements Serializable {

	@Id
	private int id;

	private int currency1;

	private int currency2;

	@Transient
	private Currency curr1;

	@Transient
	private Currency curr2;

	private BigDecimal rate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrency1() {
		return currency1;
	}

	public void setCurrency1(int currency1) {
		this.currency1 = currency1;
	}

	public int getCurrency2() {
		return currency2;
	}

	public void setCurrency2(int currency2) {
		this.currency2 = currency2;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Currency getCurr1() {
		return curr1;
	}

	public void setCurr1(Currency curr1) {
		this.curr1 = curr1;
	}

	public Currency getCurr2() {
		return curr2;
	}

	public void setCurr2(Currency curr2) {
		this.curr2 = curr2;
	}
}
