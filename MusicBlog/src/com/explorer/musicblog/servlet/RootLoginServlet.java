package com.explorer.musicblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Root;
import com.explorer.musicblog.service.IRootService;
import com.explorer.musicblog.service.IUserService;
import com.explorer.musicblog.service.impl.RootServiceImpl;
import com.explorer.musicblog.service.impl.UserServiceImpl;

/**
 * zhangzhong
 * 2018年6月3日上午2:02:22
 */
@WebServlet(urlPatterns = "/RootLoginServlet.do")
public class RootLoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		Root root = new Root();
		IRootService service = new RootServiceImpl();
		if(name != null && !"".equals(name.trim()) && pwd != null && !"".equals(pwd)) {
			try {
				root = service.getRoot(name,pwd);
			} catch (CustomException e) {
				e.printStackTrace();
				//throw new CustomException("获取数据失败!"+e.getMessage());
			}
			if(root != null && root.getAid() != null) {
				IUserService userService = new UserServiceImpl();
				try {
					Integer size = userService.getSize();
					req.setAttribute("msg", "登录成功!欢迎您:"+root.getName());
					req.setAttribute("num", size);
					req.getRequestDispatcher("/Root.jsp").forward(req, resp);
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
		req.getRequestDispatcher("/RootMessage.jsp").forward(req, resp);
	}
}
