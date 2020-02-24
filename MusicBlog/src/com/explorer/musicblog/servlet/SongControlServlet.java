package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SongControlServlet.do")
public class SongControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String control = req.getParameter("param");
		System.out.println("param:"+control);
		if (control != null) {
			switch (control) {
			case "get":
				req.getRequestDispatcher("SongServlet.do?song=all").forward(req, resp);
				return;
			case "add":
				req.getRequestDispatcher("/WEB-INF/song/SongAdd.jsp").forward(req, resp);
				return;
			default:
				System.out.println("没有可执行的操作！");
				return;
			}
		}
		req.getRequestDispatcher("/MusicBlog/WEB-INF/404.jsp").forward(req, resp);
	}
	
}
