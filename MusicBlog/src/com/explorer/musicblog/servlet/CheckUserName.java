package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.explorer.musicblog.service.IUserService;
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

	@SuppressWarnings("unlikely-arg-type")
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
	@SuppressWarnings("unused")
	private void get(String name, String pass, User user, IUserService ius, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("param", "name");
		hashMap.put("pattern", "=");
		hashMap.put("value", "'" + name + "'");
		list.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("param", "password");
		hashMap.put("pattern", "=");
		hashMap.put("value", "'" + pass + "'");
		list.add(hashMap);
		for (Map<String, Object> map : list) {
			System.out.print("param:"+map.get("param")+"\t");
			System.out.print("pattern:"+map.get("pattern")+"\t");
			System.out.println("value:"+map.get("value"));				
		}
		List<Map<String, Object>> users = null;
		users = ius.get(list);
		for (int i = 0; i < users.size(); i++) {
			Map<String, Object> map = users.get(i);
			Object object = map.get("1");
			User u = (User) object;
			user.setId(u.getId());
			user.setAccount(u.getAccount());
			if (null != u.getNickname()) {
				user.setNickname(u.getNickname());
			} else {
				user.setNickname("暂无昵称");
			}
			if (null != u.getSignature()) {
				user.setSignature(u.getSignature());
			} else {
				user.setSignature("暂无签名");
			}
			if (1 == u.getSex()) {
				user.setSex((byte)1);
			} else if (2 == u.getSex()) {
				user.setSex((byte)2);
			} else {
				user.setSex((byte)0);
			}
			user.setAge(u.getAge());
			String[] hobby = null;
			if (null != u.getHobby() && u.getHobby().length > 0) {
				hobby = new String[] { u.getHobby().toString() };
				user.setHobby(hobby);
			} else {
				hobby = new String[] { "暂无爱好" };
				user.setHobby(hobby);
			}
			if (null != u.getHead()) {
				user.setHead(u.getHead());
			} else {
				user.setHead("暂无头像");
			}
			if (null != u.getImage()) {
				user.setImage(u.getImage());
			} else {
				user.setImage("暂无图片");
			}
			if (null != u.getEmail()) {
				user.setEmail(u.getEmail());
			} else {
				user.setEmail("暂无邮箱");
			}
			if (null != u.getMobile()) {
				user.setMobile(u.getMobile());
			} else {
				user.setMobile("暂无手机");
			}
			user.setCreateTime(u.getCreateTime());
		}
		req.setAttribute("user", user);
		req.getRequestDispatcher("User.jsp").forward(req, resp);
	}
}
