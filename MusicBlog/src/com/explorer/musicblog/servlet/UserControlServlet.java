package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/UserControlServlet.do")
public class UserControlServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String params = req.getParameter("params");
		if (params != null && !params.isEmpty()) {
			switch (params) {
			case "login":
				req.getRequestDispatcher("/WEB-INF/user/Login.jsp").forward(req, resp);
				return;
			case "register":
				req.getRequestDispatcher("/WEB-INF/user/Register.jsp").forward(req, resp);
				return;
			case "update":
				req.getRequestDispatcher("/WEB-INF/user/UpdatePersonalDetails.jsp").forward(req, resp);
				return;
			case "updatepwd":
				req.getRequestDispatcher("/WEB-INF/user/UpdatePassword.jsp").forward(req, resp);
				return;
			case "forgetpwd":
				req.getRequestDispatcher("/WEB-INF/user/ForgetPassword.jsp").forward(req, resp);
				return;
			case "del":
				req.getRequestDispatcher("/WEB-INF/user/Delete.jsp").forward(req, resp);
				return;
			case "index":
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				return;
			case "upload":
				req.getRequestDispatcher("/UploadFiles.jsp").forward(req, resp);
				return;
			default:
				System.out.println("没有可执行的操作!");
				return;
			}
		}
		req.getRequestDispatcher("/WEB-INF/404.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
