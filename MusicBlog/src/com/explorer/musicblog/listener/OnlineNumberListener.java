package com.explorer.musicblog.listener;

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
		ISongTypeService its = ServiceFactory.getSongTypeService();
		List<SongType> types = its.getAll();
		context.setAttribute("types", types);
		System.out.println(context.getAttribute("types"));
		if(context == null) {
			// 初始化在线人数
			context.setAttribute("count", 0);
		} else {
			context.setAttribute("count", userNum);
		}
		System.out.println("count:"+context.getAttribute("count"));
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed()...");
		context = sce.getServletContext();
		if(context != null) {
			context.removeAttribute("count");
			context.removeAttribute("types");
		}
	}
	
	/**
	 * 	创建session
	 */
    @Override
	public void sessionCreated(HttpSessionEvent se) {
    	System.out.println("sessionCreated...");
    	if(context != null && context.getAttribute("count") instanceof Integer) {
    		userNum = (Integer)context.getAttribute("count");
			if(userNum >= 0) {
				context.setAttribute("userNum",++userNum);
			}
    	}
	}
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	System.out.println("sessionDestroyed()...");
    	userNum = (Integer)context.getAttribute("count");
    	if(userNum >= 0) {
    		context.setAttribute("userNum",--userNum);
    	}
    }
}
