package com.eb.kassa.beans;


public class EuropeanCentralBankRate extends BankRate {

	@Override
	public String toString() {
		return "EUR -> " + getCurrency() + " = " + getRate() + " ("
				+ getUpdated() + ")";
	}
}
