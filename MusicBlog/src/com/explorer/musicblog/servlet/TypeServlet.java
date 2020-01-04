package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.pojo.Type;
import com.explorer.musicblog.service.ITypeService;
import com.explorer.musicblog.service.impl.ServiceFactory;

/**
 * Type Servlet
 */
@WebServlet(name="/TypeServlet",urlPatterns = "/TypeServlet.do")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String param = req.getParameter("control");
		System.out.println("param:"+param);
		if(param != null && !"".equals(param)) {
			ITypeService its = ServiceFactory.getTypeService();
			switch(param.toUpperCase()) {
				case "GET":
					get(its,req,res);
					break;
				case "ADD":
					add(its,req,res);
					break;
				case "DELETE":
					delete(its,req,res);
					break;
				case "UPDATE":
					update(its,req,res);
					break;
				default:
					req.setAttribute("msg", "没有可执行的操作！");
			}
		}
	}

	private void delete(ITypeService its, HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		if(id != null && !"".equals(id) || id.length() > 0) {
			Type type = new Type();
			type.setId(Integer.parseInt(id));
			Integer i = its.delete(type);
			if(i != null && i > 0) {
				req.setAttribute("msg","删除成功！");
			} else {
				req.setAttribute("msg","删除失败！");
			}
			try {
				req.getRequestDispatcher("Type.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void update(ITypeService its, HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String type = req.getParameter("type");
		if(id != null && !"".equals(id) && type != null && !"".equals(type)) {
			List<Type> types = its.getAll();
			if(types != null && types.size() > 0) {
				for (Type t : types) {
					if(t.getId().equals(id) && t.getType().equals(type)) {
						
					}
				}
			}
			Type t = new Type();
			t.setType(type);
			its.update(t);
		}
	}

	private void add(ITypeService its, HttpServletRequest req, HttpServletResponse res) {
		String type = req.getParameter("type");
		if(type != null && !"".equals(type)) {
			List<Type> types = its.getAll();
			if(types != null && types.size() > 0) {
				for (Type t : types) {
					if(t.getType().equals(type)) {
						System.out.println("t:"+t);
						req.setAttribute("msg", "类型'"+t.getType()+"'已存在，请重新添加！");
						try {
							req.getRequestDispatcher("TypeAdd.jsp").forward(req, res);
							return;
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				Type t = new Type();
				t.setType(type);
				Integer i = its.insert(t);
				if(i != null && i > 0) {
					System.out.println("i:"+i);
					req.setAttribute("msg", "添加成功！");
					List<Type> ts = its.getAll();
					req.setAttribute("types",ts);
					try {
						req.getRequestDispatcher("TypeAdd.jsp").forward(req, res);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
//		req.setAttribute("msg","添加歌曲类型错误！请输入添加的类型！");
	}

	private void get(ITypeService its,HttpServletRequest req,HttpServletResponse res) {
		List<Type> types = new ArrayList<>();
		try {
			types = its.getAll();
			System.out.println("歌曲类型:"+types);
			if(types != null) {
				req.setAttribute("types", types);
				req.getRequestDispatcher("Type.jsp").forward(req, res);
			}
		} catch (Exception e) {
			req.setAttribute("msg","获取歌曲类型错误!");
			throw new RuntimeException("获取歌曲类型错误!"+e.getMessage());
		}
	}

}
