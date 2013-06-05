package com.eb.kassa.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eb.kassa.beans.User;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model, final HttpSession session) {

		session.removeAttribute(User.class.getSimpleName());
		session.invalidate();

		return "redirect:login";
	}
}