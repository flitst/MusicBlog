package com.explorer.musicblog.servlet;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有请求由这个Servlet接收
 */
public class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp){
		try {
			String params = req.getParameter("params");
			System.out.println("params:"+params);
			if (params != null) {
				Class<?> clazz = this.getClass();
				Method method = clazz.getDeclaredMethod(params,HttpServletRequest.class, HttpServletResponse.class);
				method.setAccessible(true);
				method.invoke(this,req,resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
