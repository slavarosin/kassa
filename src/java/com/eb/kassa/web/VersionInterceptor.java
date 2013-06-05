package com.eb.kassa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eb.kassa.Version;

public class VersionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {

		if (request.getSession().getAttribute(Version.class.getSimpleName()) == null)
			request.getSession().setAttribute(Version.class.getSimpleName(),
					new Version());

		String url = request.getRequestURL().toString();
		String menu = url.substring(url.indexOf("do/") + 3);

		if ("add".equals(menu) && request.getParameter("type") != null) {
			menu += request.getParameter("type");
		}

		request.getSession().setAttribute("menu", menu);

		return true;
	}
}
