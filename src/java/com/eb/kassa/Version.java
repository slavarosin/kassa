package com.eb.kassa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Version {

	private String number = "1.5.4";

	private String date = "20.06.09 15:00";

	public String getNumber() {
		return number;
	}

	public Date getDate() {
		try {
			return (new SimpleDateFormat("dd.MM.yy HH:mm")).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
