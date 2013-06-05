package com.eb.kassa.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.beans.User;
import com.eb.kassa.service.UserService;

@Controller
@RequestMapping("/manage/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		model.addAttribute("users", userService.getUsers());

		return "users";
	}

	@RequestMapping(method = RequestMethod.GET, params = { "remove" })
	public String removeUser(@RequestParam("remove")
	final Integer userId, final ModelMap model) {

		userService.remove(userId);

		return setupForm(model);
	}

	@RequestMapping(method = RequestMethod.POST, params = { "login", "passwd" })
	public String addUser(@RequestParam("login")
	final String login, @RequestParam("passwd")
	final String passwd, final ModelMap model) {

		User user = new User();
		user.setLogin(login);
		user.setPasswd(userService.encrypt(passwd));
		user.setRole(0);

		userService.addUser(user);

		return setupForm(model);
	}
}