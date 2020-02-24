package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.pojo.Root;
import com.explorer.musicblog.service.IArticleService;
import com.explorer.musicblog.service.IRootService;
import com.explorer.musicblog.service.ISingerService;
import com.explorer.musicblog.service.ISongService;
import com.explorer.musicblog.service.ISongTypeService;
import com.explorer.musicblog.service.IUserService;
import com.explorer.musicblog.service.impl.RootServiceImpl;
import com.explorer.musicblog.service.impl.ServiceFactory;
import com.explorer.musicblog.util.StringUtils;

/**
 * zhangzhong
 * 2018年6月3日上午2:02:22
 */
@WebServlet(urlPatterns = "/RootServlet.do")
@SuppressWarnings("unused")
public class RootServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	private void contact(HttpServletRequest req,HttpServletResponse resp) {
		String value = req.getParameter("value");
		if (StringUtils.isNotBlank(value)) {
			IRootService service = new RootServiceImpl();
			Integer contactTheWebmaster = service.contactTheWebmaster(value);
			req.setAttribute("msg", contactTheWebmaster);
			try {
				req.getRequestDispatcher("/WEB-INF/root/ContactTheWebmasterMessage.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		Root root = new Root();
		IRootService service = new RootServiceImpl();
		if(name != null && !"".equals(name.trim()) && pwd != null && !"".equals(pwd)) {
			root = service.getRoot(name,pwd);
			if(root != null && root.getAid() != null) {
				IUserService userService = ServiceFactory.getUserService();
				IArticleService articleService = ServiceFactory.getArticleService();
				ISongTypeService songTypeService = ServiceFactory.getSongTypeService();
				ISongService songService = ServiceFactory.getSongService();
				ISingerService singerService = ServiceFactory.getSingerService();
				try {
					// 获取总用户数
					Integer users = userService.getSize();
					// 获取总文章数
					Integer articles = articleService.getSize();
					// 获取总类型数
					Integer songTypes = songTypeService.getSize();
					// 获取总歌曲数
					Integer songs = songService.getSize();
					// 获取总歌手数
					Integer singers = singerService.getSize();
					req.setAttribute("msg", "登录成功!欢迎您:"+root.getName());
					req.setAttribute("user_num", users);
					req.setAttribute("article_num", articles);
					req.setAttribute("song_type_num", songTypes);
					req.setAttribute("song_num", songs);
					req.setAttribute("singer_num", singers);
					req.getRequestDispatcher("/WEB-INF/root/Root.jsp").forward(req, resp);
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				req.setAttribute("msg", "未查询到此账户!用户名或密码错误!");
			}
		} else {
			req.setAttribute("msg", "账号或密码不能为空!");
		}
		req.getRequestDispatcher("/WEB-INF/root/RootMessage.jsp").forward(req, resp);
	}
}
