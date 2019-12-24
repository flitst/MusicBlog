package com.explorer.musicblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * zhangzhong
 * Nov 28, 2019 2:09:24 AM
 */
@WebFilter(urlPatterns = "/*",filterName = "CommonFilter")
public class CommonFilter implements Filter {

	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		System.out.println("begin doFilter()...");
		HttpSession session = req.getSession();
		if(session != null) {
			System.out.println(">>> session");
			chain.doFilter(req, resp);
			System.out.println("session >>>");
		} else {
			System.out.println("index >>>");
			System.out.println(req.getServletContext()+"/index.jsp");
			resp.sendRedirect(req.getServletContext()+"/index.jsp");
		}
		System.out.println("end doFilter()...");
	}

}
