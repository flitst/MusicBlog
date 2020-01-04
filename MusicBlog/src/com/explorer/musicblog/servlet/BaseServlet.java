package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.service.ISingerService;
import com.explorer.musicblog.service.impl.ServiceFactory;

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
		try {
			String param = req.getQueryString();
			System.out.println("param:"+param);
			Class<?> cls = this.getClass();
			//class com.explorer.musicbox.servlet.SingerServlet 截去包名前面的"class"和空格
			Class<?> clazz = Class.forName(new String(cls.toString()).substring(6));
			System.out.println("clazz:"+clazz);
			Method method = clazz.getMethod(param,ISingerService.class,HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			ISingerService iss = ServiceFactory.getSingerService();
			method.invoke(param,iss,req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
