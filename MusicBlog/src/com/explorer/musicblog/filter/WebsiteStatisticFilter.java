package com.explorer.musicblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * zhangzhong
 * Oct 22, 2019 7:05:52 PM
 * 实现一个网站访问次数
 */
//@WebFilter(filterName = "WebsiteStatisticFilter",urlPatterns = "/index.jsp")
public class WebsiteStatisticFilter implements Filter {

	private static long statistic = 0;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init...");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		statistic++;
		System.out.println("请求数量:"+statistic);
		HttpServletRequest req = (HttpServletRequest)request;
		ServletContext context = req.getServletContext();
		context.setAttribute("count", statistic);
		HttpServletResponse resp = (HttpServletResponse)response;
		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy...");
		Filter.super.destroy();
	}
	
}
