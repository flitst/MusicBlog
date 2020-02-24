package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * zhangzhong
 * 
 * 2018年6月3日上午2:02:22
 */
@WebServlet(urlPatterns = "/RootControlServlet.do")
@SuppressWarnings("unused")
public class RootControlServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/root/RootLogin.jsp").forward(req, resp);
	}
	
	private void contact(HttpServletRequest req,HttpServletResponse resp) {
		try {
			req.setAttribute("title", "联系站长");
			req.getRequestDispatcher("/WEB-INF/root/ContactTheWebmaster.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
