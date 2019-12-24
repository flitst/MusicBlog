package com.explorer.musicblog.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * zhangzhong
 * Nov 19, 2019 7:14:29 PM
 * 实现一个在线人数的功能
 */
@WebListener(value = "OnlineNumberListener")
public class OnlineNumberListener extends HttpServlet implements HttpSessionListener,ServletContextListener {

	private static final long serialVersionUID = 1L;
	private static ServletContext sc = null;
	private static int count = 0;
	/**
	 * 当新增一个session在线数量就+1
	 */
    @Override
	public void sessionCreated(HttpSessionEvent se) {
    	System.out.println("sessionCreated...");
		Object user = se.getSession().getAttribute("user");
		System.out.println(user);
		count = (int)sc.getAttribute("count");
		if(user != null && count >= 0) {
			se.getSession().setAttribute("count",++count);
		}
	}

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	System.out.println("sessionDestroyed()...");
    	count = (int)sc.getAttribute("count");
    	if(count >= 0) {
    		se.getSession().setAttribute("count",--count);
    	}
    }
	
    /**
     * 服务器初始化时设置人数为0,否则设置当前人数即可
     */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized...");
		sc = sce.getServletContext();
		if(sc == null) {
			sc.setAttribute("count", 0);
			System.out.println("count:"+sc.getAttribute("count"));
		} else {
			sc.setAttribute("count", count);
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed()...");
	}
}
