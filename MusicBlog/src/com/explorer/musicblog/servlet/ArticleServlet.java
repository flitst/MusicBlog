package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.pojo.Article;
import com.explorer.musicblog.service.IArticleService;
import com.explorer.musicblog.service.impl.ServiceFactory;
import com.explorer.musicblog.util.StringUtils;
@WebServlet(urlPatterns = "/ArticleServlet.do")
@SuppressWarnings("unused")
public class ArticleServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	IArticleService service = ServiceFactory.getArticleServiceInstace();
	
	public ArticleServlet() {}
	
	public void pushArticle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String reference = req.getParameter("reference");
		String description = req.getParameter("description");
		String body = req.getParameter("body");
		if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(id) && StringUtils.isNotBlank(body)) {
			Article article = new Article();
			article.setTitle(title);
			article.setReference(reference);
			article.setUid(Integer.parseInt(id));
			article.setBody(body);
			Integer num = service.insert(article);
			if (null != num && num > 0) {
				req.setAttribute("msg", "发布文章成功！");
				List<Article> documents = service.getByName(title);
				req.setAttribute("articles", documents);
				// 发布成功重新查询用户的文章
				getUserArticle(req, resp);
				System.out.println("发布文章成功！");
			} else {
				req.setAttribute("msg", "发布文章失败！");
				System.out.println("发布文章失败！");
			}
			req.getRequestDispatcher("/WEB-INF/user/Main.jsp").forward(req, resp);
		}
	}
	
	private void getUserArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			List<Article> userAllArticle = service.getUserAllArticle(Integer.parseInt(id));
			req.setAttribute("allArticles", userAllArticle);
			req.getRequestDispatcher("/WEB-INF/user/Main.jsp").forward(req, resp);
			return;
		}
		req.getRequestDispatcher("/WEB-INF/404.jsp").forward(req, resp);
	}
	
	public void getAll(HttpServletRequest req, HttpServletResponse res) throws Exception{
		List<Article> articles = service.getAll();
		req.setAttribute("msg", "获取所有文章");
		req.setAttribute("title", "获取所有文章");
		req.setAttribute("articles", articles);
		try {
			req.getRequestDispatcher("/WEB-INF/article/ArticleGetAll.jsp").forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void manager(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		IArticleService articleService = ServiceFactory.getArticleServiceInstace();
		List<Article> all = articleService.getAll();
		req.setAttribute("msg", "管理文章");
		req.setAttribute("articles", all);
		try {
			req.getRequestDispatcher("/WEB-INF/article/ArticleManager.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException("错误信息："+e.getMessage()); 
		}
	}
}
