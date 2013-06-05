package com.eb.kassa;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class Utils {

	public static BigDecimal toBigDecimal(String s) {
		if (s == null)
			return new BigDecimal(0);

		s = s.replace(",", "").replaceAll("([^.0-9])", "");

		if (StringUtils.isEmpty(s)) {
			s = "0";
		}

		BigDecimal dec = new BigDecimal(0);
		try {
			dec = new BigDecimal(s);
			return dec;
		} catch (Exception e) {
			return new BigDecimal(0);
		}
	}
}
