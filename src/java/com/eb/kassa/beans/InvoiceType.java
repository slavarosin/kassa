package com.eb.kassa.beans;

public enum InvoiceType {

	INITIAL(0, true), INCOME(1, true), OUTCOME(2, false), CREDIT(3, true), CREDITBACK(
			4, false), DIVIDEND(5, false);

	private Integer id;

	private boolean in;

	InvoiceType(Integer id, boolean in) {
		this.id = id;
		this.in = in;
	}

	public Integer getId() {
		return id;
	}

	public boolean isIn() {
		return in;
	}
}
