package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/TypeControlServlet.do")
public class TypeControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		if (param != null && !param.isEmpty()) {
			switch (param) {
				case "update":
					req.getRequestDispatcher("/WEB-INF/type/TypeUpdate.jsp").forward(req, resp);
					break;
				case "add":
					req.getRequestDispatcher("/WEB-INF/type/TypeAdd.jsp").forward(req, resp);
					break;
				case "manager":
					req.getRequestDispatcher("TypeServlet.do?control=get").forward(req, resp);
					break;
				default :
					System.out.println("没有可执行的操作!");
					break;
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
