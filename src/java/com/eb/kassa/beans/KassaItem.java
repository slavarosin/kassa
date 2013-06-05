package com.eb.kassa.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "kassa")
@Transactional
public class KassaItem implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "createdBy")
	private User createdBy;

	@ManyToOne
	@JoinColumn(name = "currency_from")
	private Currency currencyFrom;

	private BigDecimal rate;

	@ManyToOne
	@JoinColumn(name = "currency_to")
	private Currency currencyTo;

	private BigDecimal amount;

	@Column(name = "invoice_type")
	private InvoiceType type;

	private Date date;

	private String comments;

	private String company;

	@Column(name = "user_comments")
	private String userComments;

	private String creditor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "createdBy")
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne
	@JoinColumn(name = "currency_from")
	public Currency getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	@ManyToOne
	@JoinColumn(name = "currency_to")
	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public InvoiceType getType() {
		return type;
	}

	public void setType(InvoiceType type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCreditor() {
		return creditor;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
