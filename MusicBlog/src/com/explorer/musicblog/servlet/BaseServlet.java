package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有请求由这个Servlet接收
 */
//@WebServlet(name = "/BaseServlet",urlPatterns = "/*")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BaseServlet() {
		super();
		System.out.println("BaseServlet...");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
	}
}
