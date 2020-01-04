package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;
import com.explorer.musicblog.service.ICommonService;
import com.explorer.musicblog.service.impl.UserServiceImpl;

/**
 * zhangzhong Nov 4, 2019 2:12:34 AM 验证用户账户是否被注册(查询数据库里是账户否存在)
 */
@WebServlet("/CheckUserName.do")
public class CheckUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("loginId");
		String password = req.getParameter("loginPwd");
		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		String head = req.getParameter("head");
		String image = req.getParameter("image");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String sql = id + password + age + sex + head + image + email + mobile;
		System.out.println(sql);
		User user = new User();
		if (id != "" && id != null && id.trim().length() > 0) {
			ICommonService<User, String, Object> uService = new UserServiceImpl();
			user.setAccount(id);
			try {
				try {
					List<Map<String, Object>> results = uService.commonQuery(User.class, id, null);
					int index = 0;
					for (Map<String, Object> map : results) {
						System.out.println(map.get(results.get(index++)));
					}
				} catch (CustomException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
