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

/**
 * zhangzhong
 * Nov 28, 2019 2:09:24 AM
 */
@WebFilter(urlPatterns = "/*",filterName = "CommonFilter")
public class CommonFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 将servlet转换成支持HTTP类型的servlet
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		// 设置请求/响应类型
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 设置浏览器不缓存
		resp.setHeader("Cache-control", "no-cache");// HTTP1.1
		resp.setHeader("Pragma", "no-cache");// HTTP1.0
		
//		Object user = req.getSession().getAttribute("user");
//		if(user != null) {
			System.out.println(">>> session");
			chain.doFilter(req, resp);
			System.out.println("session >>>");
//		} else {
//			resp.sendRedirect(req.getServletContext()+"/index.jsp");
//		}
	}

}
