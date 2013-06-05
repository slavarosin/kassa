package com.eb.kassa.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "trade")
@Transactional
public class TradeItem implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer amount;

	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "createdBy")
	private User createdBy;

	@ManyToOne
	@JoinColumn(name = "milestone")
	private TradeMilestone milestone;

	private Date date;

	private Boolean received;

	private String company;

	private String trackingID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
	@JoinColumn(name = "milestone")
	public TradeMilestone getMilestone() {
		return milestone;
	}

	public void setMilestone(TradeMilestone milestone) {
		this.milestone = milestone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getReceived() {
		return received;
	}

	public void setReceived(Boolean received) {
		this.received = received;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTrackingID() {
		return trackingID;
	}

	public void setTrackingID(String trackingID) {
		this.trackingID = trackingID;
	}
}
