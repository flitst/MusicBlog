package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
 * zhangzhong
 * Nov 24, 2019 7:53:37 PM
 */
@WebServlet(urlPatterns = "/UserManagerServlet.do")
public class UserManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service...");
		String del = req.getParameter("del");
		String manager = req.getParameter("manager");
		String login = req.getParameter("login");
		IUserService ius = ServiceFactory.getUserService();
		if(login != null && !"".equals(login.trim()) && "LOGIN".equals(login.toUpperCase())) {
			login(ius,req,resp);
		}
		if(del != null && !"".equals(del.trim()) && "DEL".equals(login.toUpperCase())) {
			delUser(ius,req,resp);
		}
		if(manager != null && !"".equals(manager.trim()) && "MANAGER".equals(login.toUpperCase())) {
			managerUser(ius,req,resp);
		}
	}
	
	public void login(IUserService ius,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		if(account != null && pwd != null && !"".equals(account.trim()) && !"".equals(pwd.trim())){
			try {
				User u = ius.login(account,pwd);
				if (u != null && u.getId() != null) {
					String rememberMe = req.getParameter("rememberMe");
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
					req.getSession().setAttribute("user", u);
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
	
	public void register(IUserService iu,HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String account = req.getParameter("account");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		User user = new User();
		PrintWriter out = resp.getWriter();
		if(account != null && "" != account && account.trim().length() > 0) {
			checkAccount(req, resp, user,iu,account);
			return;
		} else {
			System.out.println("ID账号为空!");
			req.setAttribute("null_id","ID账号为空!");
			req.getRequestDispatcher("/UserRegister.jsp").forward(req, resp);
			out.printf("null_id", null);
		}
		if(email != null && "" != email && email.trim().length() > 0) {
			checkEmail(req, resp, user,iu,email);
			return;
		}
		if(mobile != null && "" != mobile && mobile.trim().length() > 0) {
			checkMobile(req, resp, user,iu,mobile);
			return;
		}
		if(req.getParameter("pwd") == null || req.getParameter("password") == "") {
			System.out.println("密码不能为空!");
			req.setAttribute("null_pwd", "密码不能为空!");
			req.getRequestDispatcher("/UserRegister.jsp").forward(req, resp);
			return;
		}
		if(req.getParameter("affirmPwd") == null || req.getParameter("affirmPwd") == "") {
			System.out.println("确认密码不能为空!");
			req.setAttribute("null_affirmPwd", "确认密码不能为空!");
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
		String age = req.getParameter("age");
		String sex = req.getParameter("sex");
		String[] hobby = req.getParameterValues("hobby");
		String head = req.getParameter("head");
		String image = req.getParameter("image");
		String password = req.getParameter("password");
		if(req.getParameter("email") != ""){
			email = req.getParameter("email");
		}
		if(req.getParameter("mobile") != "") {
			mobile = req.getParameter("mobile");
		}
		user.setAccount(name);
		if(age != "" && age != null) {
			user.setAge(Byte.parseByte(age));
		} else {
			user.setAge((byte)18);
		}
		if("MAN".equalsIgnoreCase(sex)){
			user.setSex((byte)1);
		} else if("LADY".equalsIgnoreCase(sex)) {
			user.setSex((byte)2);
		} else {
			user.setSex((byte)0);
		}
		System.out.println(user.getSex());
		user.setHobby(hobby);
		user.setHead(head);
		user.setImage(image);
		user.setPwd(password);
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
	private void checkUserId(HttpServletRequest req, HttpServletResponse resp, User user, IUserService iu)throws IOException {
		String account = user.getAccount();
		PrintWriter out = resp.getWriter();
		if (account != null && account != "" && account.trim().length() > 0) {
			user.setAccount(account);
			try {
				List<Map<String, Object>> list = new ArrayList<>();
				Map<String, Object> map = new HashMap<>();
				map.put("param", "name");
				map.put("pattern", "=");
				map.put("value", "'" + account + "'");
				list.add(map);
				try {
					List<Map<String, Object>> lists = iu.get(list);
					for (Map<String, Object> maps : lists) {
						Set<Entry<String, Object>> entrySet = maps.entrySet();
						Iterator<Entry<String, Object>> iterator = entrySet.iterator();
						if(iterator.hasNext()) {
							Entry<String, Object> next = iterator.next();
							String key = next.getKey();
							System.out.println("key:"+key);
							Object value = next.getValue();
							System.out.println("value:"+value);
						}
					}
				} catch (CustomException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("ID账号为空!");
			out.printf("null", null);
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
	private void checkMobile(HttpServletRequest req, HttpServletResponse resp, User user, IUserService iu)
			throws IOException {
		PrintWriter out = resp.getWriter();
		String mobile = req.getParameter("mobile");
		if (mobile != null && mobile != "" && mobile.trim().length() > 0) {
			user.setMobile(mobile);
			User u = null;
			try {
				u = iu.getUser(user);
				if (u.getMobile() != null && mobile.equals(u.getMobile())) {
					System.out.println("此手机号已注册!");
					out.printf("not", false);
				} else {
					System.out.println("手机号可以注册!");
					out.printf("ok", true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("手机号为空!");
			out.printf("null", null);
		}
	}
	
	/**
	 * 删除用户
	 * @param us
	 * @param req
	 */
	private void delUser(IUserService us,HttpServletRequest req,HttpServletResponse resp) {
		String id = req.getParameter("id");
		System.out.println(id);
		if(id != null && !"".equals(id.trim())) {
			try {
				int delId = Integer.parseInt(id);
				boolean bool = us.delete(delId);
				if(bool) {
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
}
