package com.explorer.musicblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(urlPatterns="/index.jsp")
public class SongFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String search = req.getParameter("search");
		System.out.println("search:"+search);
		String name = req.getParameter("name");
		System.out.println("name:'"+name+"'");
		if(name != null && "".equals(name.trim())) {
			req.setAttribute("name", search);
		}
		chain.doFilter(req, resp);
	}

}
