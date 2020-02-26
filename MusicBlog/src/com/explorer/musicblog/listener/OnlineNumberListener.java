package com.explorer.musicblog.listener;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.explorer.musicblog.pojo.SongType;
import com.explorer.musicblog.service.ISongTypeService;
import com.explorer.musicblog.service.impl.ServiceFactory;
import com.explorer.musicblog.servlet.BaseServlet;

/**
 * zhangzhong
 * Nov 19, 2019 7:14:29 PM
 *	在线人数统计类
 */
@WebListener(value = "/OnlineNumberListener")
public class OnlineNumberListener extends BaseServlet implements HttpSessionListener,ServletContextListener {

	private static final long serialVersionUID = 1L;
	private static ServletContext context = null;
	private static Integer userNum = 0;
	
	/**
     * 	容器初始化
     */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized...");
		context = sce.getServletContext();
		// 初始化歌曲类型
		ISongTypeService its = ServiceFactory.getSongTypeServiceInstace();
		List<SongType> types = null;
		try {
			types = its.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.setAttribute("types", types);
		System.out.println(context.getAttribute("types"));
		if(context == null) {
			// 初始化在线人数
			context.setAttribute("userNum", 0);
		} else {
			context.setAttribute("userNum", userNum);
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed()...");
		while (DriverManager.getDrivers().hasMoreElements()) {
			try {
				DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
			} catch (SQLException e) {
				throw new RuntimeException("注销JDBC驱动异常!"+e.getMessage());
			}
		}
		context = sce.getServletContext();
		if(context != null) {
			context.removeAttribute("userNum");
			context.removeAttribute("types");
		}
	}
	
	/**
	 * 	创建session
	 */
    @Override
	public void sessionCreated(HttpSessionEvent se) {
    	System.out.println("sessionCreated...");
    	if(context != null && context.getAttribute("userNum") instanceof Integer) {
    		userNum = (Integer)context.getAttribute("userNum");
			if(userNum == null || userNum >= 0) {
				context.setAttribute("userNum",++userNum);
			}
    	}
	}
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	System.out.println("sessionDestroyed()...");
    	userNum = (Integer)context.getAttribute("userNum");
    	if(userNum != null && userNum >= 0) {
    		context.setAttribute("userNum",--userNum);
    	}
    }
}
