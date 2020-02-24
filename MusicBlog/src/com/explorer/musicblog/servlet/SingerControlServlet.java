package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SingerControlServlet.do")
public class SingerControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		System.out.println("param:"+param);
		if (param != null) {
			switch (param) {
				case "get":
					req.getRequestDispatcher("SingerServlet.do?singer=all").forward(req, resp);
					return;
				case "add":
					req.getRequestDispatcher("/WEB-INF/singer/SingerAdd.jsp").forward(req, resp);
					return;
				default:
					System.out.println("没有可执行的操作！");
					return;
			}
		}
		req.getRequestDispatcher("/MusicBlog/WEB-INF/404.jsp").forward(req, resp);
	}
	
}
