package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;
import com.explorer.musicblog.service.IUserService;
import com.explorer.musicblog.service.impl.UserServiceImpl;
import com.explorer.musicblog.util.StringUtils;

/**
 * 	UserServlet
 * 	用户
 */
@WebServlet(urlPatterns = "/UserServlet.do")
public class UserServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;

	public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		String piccode = req.getParameter("piccode");
		String ukey = (String)req.getSession().getAttribute("ukey");
		String k = (String)ukey;
		Object obj = req.getSession().getAttribute(k);
		String rememberMe = req.getParameter("rememberMe");
		System.out.println("rememberMe:"+rememberMe);
		Cookie[] cookies = req.getCookies();
		if (obj != null && obj instanceof User || cookies != null && cookies.length > 0 && rememberMe != null && rememberMe.equals("on")) {
			boolean bool = rememberMe(account, pwd, rememberMe, cookies,req, resp);
			if (bool) {
				req.getRequestDispatcher("/WEB-INF/user/Login.jsp").forward(req, resp);
				return;
			}
		}
		// 验证账号和密码
		if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(pwd)){
			Object code = req.getSession().getAttribute("code");
			// 验证用户输入的验证码
			if (StringUtils.isNotBlank(piccode) && code instanceof String && piccode.equalsIgnoreCase((String)code)) {
				// 验证完毕立即销毁此次验证码，以防下次登录时验证的还是之前的验证码
				req.getSession().removeAttribute("code");
				IUserService ius = new UserServiceImpl();
				List<User> users = ius.getByName(account);
				for (User u : users) {
					if (u != null && u.getStatus() == 1) {
						u = ius.login(account,pwd);
						if (u != null && u.getId() != null) {
							System.out.println("登录成功! 欢迎您:"+u.getAccount());
							req.setAttribute("msg", "登录成功! 欢迎您:"+u.getAccount());
			//				String key = u.getId()+u.getAccount();
			//				req.getSession().setAttribute(key, u);
			//				req.getSession().setAttribute("ukey", key);
							req.getSession().setAttribute("title", "我的主页");
							req.getSession().setAttribute("user", u);
							req.getRequestDispatcher("/WEB-INF/user/Main.jsp").forward(req, resp);
							return;
						} else {
							System.out.println("用户名或密码错误!");
							req.setAttribute("error_msg", "用户名或密码错误!");
						}
					} else {
						req.setAttribute("error_msg", "此账号已被禁用！若用疑问请<a href=\"RootControlServlet.do?params=contact\">联系站长<a>");
						System.out.println("此账号已被禁用！");
					}
				}
			} else {
				req.setAttribute("error_msg", "验证码无效！");
			}
		} else {
			System.out.println("用户名或密码不能为空!");
			req.setAttribute("error_msg", "用户名或密码不能为空!");
		}
		req.getRequestDispatcher("/WEB-INF/user/Login.jsp").forward(req, resp);
	}

	private boolean rememberMe(String account,String pwd,String rememberMe,Cookie[] cookies, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (account != null && !"".equals(account) && pwd != null && !"".equals(pwd)) {
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(account) && cookie.getValue().equals(pwd)) {
						User user = new User();
						user.setAccount(cookie.getName());
						user.setPwd(cookie.getValue());
						req.getSession().setAttribute("account", account);
						req.getSession().setAttribute("pwd", pwd);
						return true;
					}
				}
			} else {
				Cookie username = new Cookie("account", account);
				Cookie pass = new Cookie("pwd", pwd);
				username.setPath(req.getContextPath() + "/user");
				pass.setPath(req.getContextPath() + "/user");
				if (rememberMe != null && rememberMe.equals("on")) {
					username.setMaxAge(60 * 60 * 24 * 3);// 三天
					pass.setMaxAge(60 * 60 * 24 * 3);// 三天
				} else {
					username.setMaxAge(0);
					pass.setMaxAge(0);
				}
				resp.addCookie(username);
				resp.addCookie(pass);
			}
		}
		return false;
	}
	
	/**
	 * 	退出登录
	 * @param user
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		User user = new User();
//		String key = (String)req.getSession().getAttribute("ukey");
//		if(key != null && key instanceof String) {
//			String ukey = (String)key;
//			Object obj = req.getSession().getAttribute(ukey);
//			if(obj instanceof User) {
//				user = (User)obj;
//			}
//		}
		User user = (User)req.getSession().getAttribute("user");
		if(user != null) {
//			Object obj = req.getSession().getAttribute(user.getId()+user.getAccount());
//			if (obj != null) {
				req.getSession().removeAttribute("user");
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
				return;
//			}
		}
		System.out.println("获取用户信息失败!");
		req.setAttribute("error_msg", "获取用户信息失败!未能退出!");
		req.getRequestDispatcher("/WEB-INF/user/Main.jsp").forward(req, resp);
	}
	
	public void register(IUserService ius,HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		User user = new User();
		boolean checkAccount = false;
		if (StringUtils.isLegal(account) != null) {
			checkAccount = checkAccount(user, ius, account, req, resp);
			if(!checkAccount) {
				return;
			}
		}
		if (StringUtils.isLegal(account) != null && StringUtils.isLegal(pwd) != null) {
			user.setAccount(account);
			user.setPwd(pwd);
			user.setNickname(null);
			user.setSignature(null);
			user.setAge((short)0);
			user.setSex((byte)0);
			String[] hobby = new String[]{};
			user.setHobby(hobby);
			user.setHead(null);
			user.setImage(null);
			user.setMobile(mobile);
			user.setEmail(email);
	    	// 2019年第48周,第332天 11月28日 星期:4 03:40:09
	    	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy年第w周,第D天 MM月dd日 星期:F HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());
			user.setCreateTime(date); //设置注册时间
			user.setUpdateTime(date); //设置修改时间
			IUserService userService = new UserServiceImpl();
			Integer num = userService.insert(user);
			req.setAttribute("title","用户注册");
			if(num != null && num > 0) {
				System.out.println("注册成功!");
				req.setAttribute("msg","注册成功!");
				req.getRequestDispatcher("/WEB-INF/user/RegisterSucceed.jsp").forward(req, resp);
			} else {
				System.out.println("注册失败!");
				req.setAttribute("msg","注册失败!");
				req.getRequestDispatcher("/WEB-INF/user/RegisterError.jsp").forward(req, resp);
			}
		}
	}
	
	private boolean checkAccount(User user,IUserService iu,String account, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		PrintWriter out = resp.getWriter();
		user.setAccount(account);
		User u = iu.checkUser(user);
		if(u != null) {
			out.write(" × 此帐号已注册!");
			req.setAttribute("account_msg","对不起哦！此账号已存在，请重新输入！");
			System.out.println("此帐号已注册!");
			return false;
		} else {
			out.write(" √ 此账号可以注册!");
			req.setAttribute("account_msg"," √ ");
			System.out.println("此账号可以注册!");
			return true;
		}
	}

	public void updatePWD(IUserService ius, HttpServletRequest req, HttpServletResponse resp) {
		String no = req.getParameter("no");
		if(no != null && !"".equals(no)) {
			System.out.println("no:"+no);
			User u = new User();
			ius.update(u);
		}
	}

	public void forgetPWD(IUserService ius, HttpServletRequest req, HttpServletResponse resp) {
	}
	
	/**
	 * 获取所有用户
	 * @param us
	 * @param req
	 * @param resp
	 */
	public void manager(HttpServletRequest req,HttpServletResponse resp) {
		IUserService us = new UserServiceImpl();
		try {
			List<User> all = us.getAll();
			System.out.println("all:"+all);
			req.setAttribute("users", all);
			req.getRequestDispatcher("/WEB-INF/user/UserManager.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(IUserService ius, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oldPwd = req.getParameter("old");
		String newPwd = req.getParameter("new");
		System.out.println(oldPwd);
		System.out.println(newPwd);
		String ur = (String)req.getSession().getAttribute("ukey");
//		IUserService iu = new UserServiceImpl();
		if (ur != null) {
			Object obj = req.getSession().getAttribute(ur);
			User u = (User)obj;
			String nickname = req.getParameter("nickname");
			String signature = req.getParameter("signature");
			String age = req.getParameter("age");
			String sex = req.getParameter("sex");
			String[] hobby = req.getParameterValues("hobby");
			String head = req.getParameter("head");
			String image = req.getParameter("image");
			String email = req.getParameter("email");
			String mobile = req.getParameter("mobile");
			User user = new User();
			user = u;
			user.setAccount(u.getAccount());
			user.setPwd(u.getPwd());
//			checkUserId(req, resp, user, iu);
//			if (email != null && "" != email && email.trim().length() > 0) {
//				checkEmail(req, resp, user, iu);
//			}
//			if (mobile != null && "" != mobile && mobile.trim().length() > 0) {
//				checkMobile(req, resp, user, iu);
//			}
			user.setNickname(nickname);
			user.setSignature(signature);
			if (age != null && !(age.trim().equals(""))) {
				user.setAge(Short.parseShort(age));
			} else {
				user.setAge(null);
			}
			if ("MAN".equalsIgnoreCase(sex)) {
				user.setSex((byte) 1);
			} else if ("LADY".equalsIgnoreCase(sex)) {
				user.setSex((byte) 2);
			} else {
				user.setSex((byte) 0);
			}
			user.setHobby(hobby);
			user.setHead(head);
			user.setImage(image);
			user.setEmail(email);
			user.setMobile(mobile);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// yyyy-MM-dd 星期:F HH:mm:ss
			user.setUpdateTime(sdf.format(new Date()));
			IUserService serviceImpl = new UserServiceImpl();
//			String sql = "update `user` set `nickname`=?,`signature`=?,`age`=?,`sex`=?,`hobby`=?,`head`=?,`image`=?,`email`=?,`mobile`=? where `name`=? and `password`=?";
//			Integer rs = null;
			try {
//				result = serviceImpl.commonCUD(sql, user.getNickname(), user.getSignature(), user.getAge(), user.getSex(),
//						user.getHobby().toString(), user.getHead(), user.getImage(), user.getEmail(), user.getMobile(), user.getName(),
//						user.getPassword());
				Integer num = serviceImpl.update(user);
				if (num != null && num > 0) {
					req.setAttribute("msg", "执行操作成功!欢迎你:" + user.getAccount());
					req.setAttribute("user", user);
					req.getRequestDispatcher("/WEB-INF/user/Main.jsp").forward(req, resp);
					return;
				} else {
					req.setAttribute("msg", "执行操作失败!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			req.setAttribute("msg", "无法修改!未查到用户信息!");
		}
		req.getRequestDispatcher("/WEB-INF/user/Login.jsp").forward(req, resp);
	}

	/**
	 * 禁用用户
	 * @param us
	 * @param req
	 */
	public void disableUser(HttpServletRequest req,HttpServletResponse resp) {
		IUserService us = new UserServiceImpl();
		String idStr = req.getParameter("id");
		if(idStr != null && !"".equals(idStr)) {
			User user = new User();
			try {
				int id = Integer.parseInt(idStr);
				user.setId(id);
				User u = us.getById(user.getId());
				if (u != null) {
					boolean bool = us.disableUser(id,(byte)0);
					if(bool) {
						req.setAttribute("msg","禁用用户成功!");
						req.getRequestDispatcher("/WEB-INF/user/Delete.jsp").forward(req, resp);
						System.out.println("禁用用户成功!");
						return;
					}
				}
				req.setAttribute("msg","禁用户失败!");
				System.out.println("禁用户失败!");
				req.getRequestDispatcher("/WEB-INF/user/Login.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 禁用用户
	 * @param us
	 * @param req
	 */
	public void enableUser(HttpServletRequest req,HttpServletResponse resp) {
		IUserService us = new UserServiceImpl();
		String idStr = req.getParameter("id");
		if(idStr != null && !"".equals(idStr)) {
			User user = new User();
			try {
				int id = Integer.parseInt(idStr);
				user.setId(id);
				User u = us.getById(user.getId());
				if (u != null) {
					boolean bool = us.disableUser(id,(byte)1);
					if(bool) {
						req.setAttribute("msg","启用户成功!");
						req.getRequestDispatcher("/WEB-INF/user/Delete.jsp").forward(req, resp);
						System.out.println("启用用户成功!");
						return;
					}
				}
				req.setAttribute("msg","启用户失败!");
				System.out.println("启用户失败!");
				req.getRequestDispatcher("/WEB-INF/user/Login.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除用户
	 * @param us
	 * @param req
	 */
	public void delete(HttpServletRequest req,HttpServletResponse resp) {
		IUserService us = new UserServiceImpl();
		String id = req.getParameter("id");
		if(id != null && !"".equals(id)) {
			User user = new User();
			try {
				user.setId(Integer.parseInt(id));
				Integer delete = us.delete(user.getId());
				req.setAttribute("title","删除用户");
				if(delete != null && delete > 0) {
					req.getSession().removeAttribute("user");
					req.setAttribute("msg","删除用户成功!");
					System.out.println("删除用户成功!");
					req.getRequestDispatcher("/WEB-INF/user/Delete.jsp").forward(req, resp);
					return;
				}
				req.setAttribute("msg","删除用户失败!");
				System.out.println("删除用户失败!");
				req.getRequestDispatcher("/WEB-INF/user/Login.jsp").forward(req, resp);
			} catch (CustomException e) {
				e.getMessage(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
