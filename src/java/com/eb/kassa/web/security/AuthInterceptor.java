package com.eb.kassa.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eb.kassa.beans.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {

		String url = request.getRequestURL().toString();

		boolean adminURL = url.contains("manage");

		User user = (User) request.getSession().getAttribute(
				User.class.getSimpleName());

		if (url.endsWith("login")) {
			if (user != null) {
				response.sendRedirect(response.encodeRedirectURL(request
						.getContextPath()
						+ "/do/kassa"));
			}
			return true;
		}

		if (user == null || (user.getRole() == Role.USER & adminURL)) {
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath()
					+ "/do/login"));
			return false;
		}

		return true;
	}
}
