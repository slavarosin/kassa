package com.eb.kassa.web;

import java.text.DateFormat;
import java.util.Calendar;

import org.springframework.ui.ModelMap;

public class ModelUtils {

	public static void initFilterDates(final ModelMap model,
			DateFormat dateFormat) {
		// Date variables for date period templates
		Calendar calendar = Calendar.getInstance();
		String today = dateFormat.format(calendar.getTime());

		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		String yesterday = dateFormat.format(calendar.getTime());

		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		String thisMonthStart = dateFormat.format(calendar.getTime());

		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		String thisMonthEnd = dateFormat.format(calendar.getTime());

		calendar = Calendar.getInstance();
		int dof = calendar.get(Calendar.DAY_OF_WEEK) - 2;
		calendar.add(Calendar.DATE, dof * -1);
		String thisWeekStart = dateFormat.format(calendar.getTime());
		calendar.add(Calendar.DATE, -1);
		String prevWeekEnd = dateFormat.format(calendar.getTime());
		calendar.add(Calendar.DATE, 7);
		String thisWeekEnd = dateFormat.format(calendar.getTime());
		calendar.add(Calendar.DATE, -13);
		String prevWeekStart = dateFormat.format(calendar.getTime());

		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, 0);
		String thisYearStart = dateFormat.format(calendar.getTime());

		calendar.set(Calendar.DATE, 31);
		calendar.set(Calendar.MONTH, 11);
		String thisYearEnd = dateFormat.format(calendar.getTime());

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DATE, 1);
		String prevMonthStart = dateFormat.format(calendar.getTime());
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		String prevMonthEnd = dateFormat.format(calendar.getTime());

		model.addAttribute("today", today);
		model.addAttribute("yesterday", yesterday);
		model.addAttribute("thisMonthStart", thisMonthStart);
		model.addAttribute("thisMonthEnd", thisMonthEnd);
		model.addAttribute("thisWeekStart", thisWeekStart);
		model.addAttribute("prevWeekEnd", prevWeekEnd);
		model.addAttribute("thisWeekEnd", thisWeekEnd);
		model.addAttribute("prevWeekStart", prevWeekStart);
		model.addAttribute("thisYearStart", thisYearStart);
		model.addAttribute("thisYearEnd", thisYearEnd);
		model.addAttribute("prevMonthStart", prevMonthStart);
		model.addAttribute("prevMonthEnd", prevMonthEnd);
	}
}
