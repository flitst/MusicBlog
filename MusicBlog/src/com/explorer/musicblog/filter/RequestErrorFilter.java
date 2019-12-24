package com.explorer.musicblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * zhangzhong Dec 4, 2019 1:48:26 AM
 */
//@WebFilter(description = "ERROR", dispatcherTypes = DispatcherType.ERROR)
public class RequestErrorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("请求错误!");
//		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)request;
		resp.sendRedirect("/error.jsp");
		chain.doFilter(request, response);
	}

}
