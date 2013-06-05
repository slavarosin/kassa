package com.eb.kassa.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eb.kassa.beans.User;
import com.eb.kassa.service.RateService;
import com.eb.kassa.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private RateService rateService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		model.addAttribute("user", new User());

		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("user") final User userForm,
			final BindingResult errors, final HttpSession session) {

		User user = userService.getUser(userForm.getLogin(), userService
				.encrypt(userForm.getPasswd()));

		if (user == null) {
			errors.reject("user.login");
			return "login";
		} else {
			session.setAttribute(User.class.getSimpleName(), user);

			rateService.updateRates();

			return "redirect:kassa";
		}
	}
}