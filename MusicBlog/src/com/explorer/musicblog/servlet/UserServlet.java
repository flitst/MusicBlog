package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;
import com.explorer.musicblog.service.IUserService;
import com.explorer.musicblog.service.impl.ServiceFactory;
import com.explorer.musicblog.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserLogoutServlet
 */
@WebServlet(name = "/UserServlet", urlPatterns = "/UserServlet.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service...");
		Enumeration<String> names = req.getParameterNames();
		IUserService ius = ServiceFactory.getUserService();
		while (names.hasMoreElements()) {
			String nextElement = names.nextElement();
			System.out.println("nextElement:"+nextElement);
			if (nextElement != null && !nextElement.isEmpty()) {
				User u = new User();
				if ("LOGIN".equals(nextElement.toUpperCase())) {
					System.out.println("login...");
					String account = req.getParameter("account");
					String pwd = req.getParameter("pwd");
					login(ius,account,pwd, req, resp);
					break;
				} else if ("REGISTER".equals(nextElement.toUpperCase())) {
					System.out.println("register...");
					register(ius, req, resp);
					break;
				} else if ("LOGOUT".equals(nextElement.toUpperCase())) {
					System.out.println("logout...");
					Object key = req.getSession().getAttribute("ukey");
					if(key != null && key instanceof String) {
						String ukey = (String)key;
						Object obj = req.getSession().getAttribute(ukey);
						if(obj instanceof User) {
							u = (User)obj;
							logout(u,req, resp);
						}
					}
					break;
				} else if ("UPDATE".equals(nextElement.toUpperCase())) {
					System.out.println("update...");
					 update(req,resp);
					break;
				} else if ("DELETE".equals(nextElement.toUpperCase())) {
					System.out.println("delete...");
					delUser(u,ius, req, resp);
					break;
				} else if ("manager".equals(nextElement.toUpperCase())) {
					System.out.println("managerUser...");
					managerUser(ius, req, resp);
					break;
				} else if ("FORGETPWD".equals(nextElement.toUpperCase())) {
					System.out.println("forgetPWD...");
					forgetPWD(ius, req, resp);
					break;
				}
			} else {
				System.out.println("没有可执行的操作");
			}

		}
		// UserUpdate.jsp UserPWDUpdate.jsp
	}

	private void forgetPWD(IUserService ius, HttpServletRequest req, HttpServletResponse resp) {
	}

	public void login(IUserService ius,String account,String pwd,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(account != null && pwd != null && !"".equals(account.trim()) && !"".equals(pwd.trim())){
			try {
				User u = ius.login(account,pwd);
				if (u != null && u.getId() != null) {
					String rememberMe = req.getParameter("rememberMe");
					System.out.println("rememberMe:"+rememberMe);
					Cookie[] cookies = req.getCookies();
					if (cookies != null && cookies.length > 0 && rememberMe != null && rememberMe.equals("on")
							&& account != null && pwd != null && !"".equals(account.trim()) && !"".equals(pwd.trim())) {
							try {
								boolean bool  = rememberMe(u.getId().toString(), pwd,rememberMe,cookies, ius, req, resp);
								if(bool) {
									System.out.println("bool:"+bool);
									req.getRequestDispatcher("/UserLogin.jsp").forward(req, resp);
									return;
								}
							} catch (CustomException e) {
								e.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
							}
					}
					System.out.println("登录成功! 欢迎您:"+u.getAccount());
					req.setAttribute("msg", "登录成功! 欢迎您:"+u.getAccount());
					String ukey = u.getId()+u.getAccount();
					req.getSession().setAttribute(ukey, u);
					req.getSession().setAttribute("ukey", ukey);
					req.getRequestDispatcher("/User.jsp").forward(req, resp);
					return;
				} else {
					System.out.println("用户名或密码错误!");
					req.setAttribute("msg", "用户名或密码错误!");
				}
			} catch (CustomException e) {
				e.printStackTrace();
			}  catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("用户名或密码不能为空!");
			req.setAttribute("msg", "用户名或密码不能为空!");
		}
		req.getRequestDispatcher("/UserLogin.jsp").forward(req, resp);
	}

	private boolean rememberMe(String id,String pwd,String rememberMe,Cookie[] cookies,IUserService ius, HttpServletRequest req, HttpServletResponse resp)throws CustomException, Exception {
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(id) && cookie.getValue().equals(pwd)) {
					User user = new User();
					user.setAccount(cookie.getName());
					user.setPwd(cookie.getValue());
					req.getSession().setAttribute("account", id);
					req.getSession().setAttribute("pwd", pwd);
					return true;
				}
			}
		} else {
			Cookie username = new Cookie("account", id);
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
		return false;
	}
	
	private String checkAccount(HttpServletRequest req, HttpServletResponse resp,User user,IUserService iu,String account) throws IOException {
		PrintWriter out = resp.getWriter();
		try {
			User u = iu.getUser(user);
			if(u != null) {
				if(u.getId() != null && !account.equals(u.getAccount())) {
					System.out.println("可以注册!");
					out.printf("ok", true);
					return "ok";
				} else {
					System.out.println("此帐号已注册!");
					out.printf("not", false);
					return "not";
				}
			} else {
				System.out.println("获取账号失败!");
				out.printf("null", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "null";
	}
	
	private void checkEmail(HttpServletRequest req, HttpServletResponse resp, User user, IUserService iu)throws IOException {
		PrintWriter out = resp.getWriter();
		String email = req.getParameter("email");
		if (email != null && email != "" && email.trim().length() > 0) {
			user.setEmail(email);
			User u = null;
			try {
				u = iu.getUser(user);
				if (u.getEmail() != null && email.equals(u.getEmail())) {
					System.out.println("此邮箱已注册!");
					out.printf("not", false);
				} else {
					System.out.println("邮箱可以注册!");
					out.printf("ok", true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("邮箱为空!");
			out.printf("null", null);
		}
	}
	
	private void checkMobile(HttpServletRequest req, HttpServletResponse resp,User user,IUserService iu,String mobile) throws IOException {
		PrintWriter out = resp.getWriter();
		if(mobile != "" && mobile != null && mobile.trim().length() > 0) {
			user.setMobile(mobile);
			User u = null;
			try {
				u = iu.getUser(user);
				if(u != null) {
					if(u.getMobile() != null && mobile.equals(u.getMobile())) {
						System.out.println("此手机号已注册!");
						out.printf("not", false);
					} else {
						System.out.println("手机号可以注册!");
						out.printf("ok", true);
					}
				} else {
					System.out.println("获取手机号错误!");
					out.printf("null", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("手机号为空!");
				req.setAttribute("null_mobile","ID账号为空!");
				req.getRequestDispatcher("/UserRegister.jsp").forward(req, resp);
				out.printf("null", null);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}

	
	private void checkEmail(HttpServletRequest req, HttpServletResponse resp,User user,IUserService iu,String email) throws IOException {
		PrintWriter out = resp.getWriter();
		if(email != "" && email != null && email.trim().length() > 0) {
			user.setEmail(email);
			User u = null;
			try {
				u = iu.getUser(user);
				if(u != null) {
					if(u.getEmail() != null && email.equals(u.getEmail())) {
						System.out.println("此邮箱已注册!");
						out.printf("not", false);
					} else {
						System.out.println("邮箱可以注册!");
						out.printf("ok", true);
					}
				} else {
					System.out.println("获取邮箱错误!");
					out.printf("null", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("邮箱为空!");
				req.setAttribute("null_email","ID账号为空!");
				req.getRequestDispatcher("/UserRegister.jsp").forward(req, resp);
				out.printf("null", null);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void logout(User u,HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(u != null) {
			Object obj = req.getSession().getAttribute(u.getId()+u.getAccount());
			if (obj != null) {
				req.getSession().removeAttribute(u.getId()+u.getAccount());
				resp.sendRedirect("index.jsp");
			} else {
				req.setAttribute("msg", "获取用户信息失败!未能退出!");
				req.getRequestDispatcher("User.jsp").forward(req, resp);
			}
		} else {
			System.out.println("获取用户信息失败!");
		}
	}
	
	/**
	 * 获取所有用户
	 * @param us
	 * @param req
	 * @param resp
	 */
	private void managerUser(IUserService us, HttpServletRequest req,HttpServletResponse resp) {
		try {
			List<Map<String,Object>> all = us.getAll();
			System.out.println("all:"+all);
			req.setAttribute("users", all);
			req.getRequestDispatcher("/UserManager.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void register(IUserService ius,HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String account = req.getParameter("account");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		User user = new User();
		PrintWriter out = resp.getWriter();
		if(account != null && "" != account && account.trim().length() > 0) {
			checkAccount(req, resp, user,ius,account);
			return;
		} else {
			System.out.println("ID账号为空!");
			req.setAttribute("id","ID账号为空!");
			req.getRequestDispatcher("/UserRegister.jsp").forward(req, resp);
			out.printf("id", null);
		}
		if(email != null && "" != email && email.trim().length() > 0) {
			checkEmail(req, resp, user,ius,email);
			return;
		}
		if(mobile != null && "" != mobile && mobile.trim().length() > 0) {
			checkMobile(req, resp, user,ius,mobile);
			return;
		}
		if(req.getParameter("pwd") == null || req.getParameter("password") == "") {
			System.out.println("密码不能为空!");
			req.setAttribute("pwd", "密码不能为空!");
			req.getRequestDispatcher("/UserRegister.jsp").forward(req, resp);
			return;
		}
		if(req.getParameter("affirmPwd") == null || req.getParameter("affirmPwd") == "") {
			System.out.println("确认密码不能为空!");
			req.setAttribute("affirmPwd", "确认密码不能为空!");
			req.getRequestDispatcher("/UserRegister.jsp").forward(req, resp);
			return;
		}
		if(!(req.getParameter("pwd").equals(req.getParameter("affirmPwd")))) {
			System.out.println("两次输入的密码不一样!");
			req.setAttribute("status", "两次输入的密码不一样!");
			System.out.println(req.getParameter("pwd") + ",\t" + req.getParameter("affirmPwd"));
			req.getRequestDispatcher("/UserRegister.jsp").forward(req, resp);
			return;
		}
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		if(req.getParameter("email") != ""){
			email = req.getParameter("email");
		}
		if(req.getParameter("mobile") != "") {
			mobile = req.getParameter("mobile");
		}
		user.setAccount(name);
		user.setAge((byte)0);
		user.setSex((byte)0);
		user.setHead(null);
		user.setImage(null);
		user.setPwd(pwd);
		user.setEmail(email);
		user.setMobile(mobile);
    	//2019年第48周,第332天 11月28日 星期:4 03:40:09
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy年第w周,第D天 MM月dd日 星期:F HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 星期:F HH:mm:ss");
		String date = sdf.format(new Date());
		user.setRegisterTime(date); //设置注册时间
		user.setModifyTime(date);	//设置修改时间
		IUserService userService = new UserServiceImpl();
		try {
			boolean bool = userService.register(user);
			if(bool) {
				System.out.println("注册成功!");
				req.setAttribute("msg","注册成功!");
				req.getRequestDispatcher("UserAddOKMessage.jsp").forward(req, resp);
			} else {
				System.out.println("注册失败!");
				req.setAttribute("msg","注册失败!");
				req.getRequestDispatcher("UserAddErrorMessage.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void get() {
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		HashMap<String, Object> hashMap = new HashMap<String, Object>();
//		hashMap.put("param", "name");
//		hashMap.put("pattern", "=");
//		hashMap.put("value", "'" + name + "'");
//		list.add(hashMap);
//		hashMap = new HashMap<String, Object>();
//		hashMap.put("param", "password");
//		hashMap.put("pattern", "=");
//		hashMap.put("value", "'" + pass + "'");
//		list.add(hashMap);
//		for (Map<String, Object> map : list) {
//			System.out.print("param:"+map.get("param")+"\t");
//			System.out.print("pattern:"+map.get("pattern")+"\t");
//			System.out.println("value:"+map.get("value"));				
//		}
//		List<Map<String, Object>> users = null;
//		try {
//			users = ius.get(list);
//		} catch (CustomException e) {
//			e.printStackTrace();
//		}
//		for (int i = 0; i < users.size(); i++) {
//			Map<String, Object> map = users.get(i);
//			Object object = map.get("1");
//			User u = (User) object;
//			user.setUid(u.getUid());
//			user.setName(u.getName());
//			if (null != u.getNickname()) {
//				user.setNickname(u.getNickname());
//			} else {
//				user.setNickname("暂无昵称");
//			}
//			if (null != u.getSignature()) {
//				user.setSignature(u.getSignature());
//			} else {
//				user.setSignature("暂无签名");
//			}
//			if (1 == u.getSex()) {
//				user.setSex(1);
//			} else if (2 == u.getSex()) {
//				user.setSex(2);
//			} else {
//				user.setSex(0);
//			}
//			user.setAge(u.getAge());
//			String[] hobby = null;
//			if (null != u.getHobby() && u.getHobby().length > 0) {
//				hobby = new String[] { u.getHobby().toString() };
//				user.setHobby(hobby);
//			} else {
//				hobby = new String[] { "暂无爱好" };
//				user.setHobby(hobby);
//			}
//			if (null != u.getHead()) {
//				user.setHead(u.getHead());
//			} else {
//				user.setHead("暂无头像");
//			}
//			if (null != u.getImage()) {
//				user.setImage(u.getImage());
//			} else {
//				user.setImage("暂无图片");
//			}
//			if (null != u.getEmail()) {
//				user.setEmail(u.getEmail());
//			} else {
//				user.setEmail("暂无邮箱");
//			}
//			if (null != u.getMobile()) {
//				user.setMobile(u.getMobile());
//			} else {
//				user.setMobile("暂无手机");
//			}
//			user.setRegisterTime(u.getRegisterTime());
//		}
//		req.setAttribute("user", user);
//		req.getRequestDispatcher("User.jsp").forward(req, resp);
//		}else
//	
//		{
//			req.setAttribute("message", "用户名或密码为空!");
//			req.getRequestDispatcher("UserLogin.jsp").forward(req, resp);
//		}
	}
	
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oldPwd = req.getParameter("old");
		String newPwd = req.getParameter("new");
		System.out.println(oldPwd);
		System.out.println(newPwd);
		Object ur = req.getSession().getAttribute("user");
//		IUserService iu = new UserServiceImpl();
		if (ur != null) {
			User u = (User) ur;
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
				user.setAge(Byte.parseByte(age));
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
			user.setModifyTime(sdf.format(new Date()));
			IUserService serviceImpl = new UserServiceImpl();
//			String sql = "update `user` set `nickname`=?,`signature`=?,`age`=?,`sex`=?,`hobby`=?,`head`=?,`image`=?,`email`=?,`mobile`=? where `name`=? and `password`=?";
//			Integer rs = null;
			try {
//				result = serviceImpl.commonCUD(sql, user.getNickname(), user.getSignature(), user.getAge(), user.getSex(),
//						user.getHobby().toString(), user.getHead(), user.getImage(), user.getEmail(), user.getMobile(), user.getName(),
//						user.getPassword());
				boolean rs = serviceImpl.update(user);
				if (rs) {
					req.setAttribute("msg", "执行操作成功!欢迎你:" + user.getAccount());
					req.setAttribute("user", user);
					req.getRequestDispatcher("/User.jsp").forward(req, resp);
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
		req.getRequestDispatcher("/UserLogin.jsp").forward(req, resp);
	}

	/**
	 * 删除用户
	 * @param us
	 * @param req
	 */
	private void delUser(User u,IUserService us,HttpServletRequest req,HttpServletResponse resp) {
		if(u != null && u.getId() != 0) {
			try {
				boolean bool = us.delete(u.getId());
				if(bool) {
					req.getSession().removeAttribute("user");
					req.setAttribute("msg","删除用户成功!");
					req.getRequestDispatcher("UserDel.jsp").forward(req, resp);
				}
				req.setAttribute("msg","删除用户失败!");
				req.getRequestDispatcher("UserLogin.jsp").forward(req, resp);
			} catch (CustomException e) {
				e.getMessage(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
