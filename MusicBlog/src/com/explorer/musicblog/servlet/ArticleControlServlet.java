package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ArticleControlServlet.do")
@SuppressWarnings("unused")
public class ArticleControlServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public ArticleControlServlet() {}
	
	public void pushArticle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("title", "发布文章");
		req.getRequestDispatcher("/WEB-INF/article/PushArticle.jsp").forward(req, res);
	}
	
	public void getAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("title", "获取所有文章");
		req.getRequestDispatcher("ArticleServlet.do?getAll").forward(req, res);
	}
	
	private void manager(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("title", "管理文章文章");
		req.getRequestDispatcher("ArticleServlet.do?manager").forward(req, res);
	}

	private void getUserArticle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("title", "管理文章文章");
		req.getRequestDispatcher("ArticleServlet.do?getUserArticle").forward(req, res);
	}
}
