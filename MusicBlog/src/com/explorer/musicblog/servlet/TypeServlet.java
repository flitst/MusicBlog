package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.pojo.SongType;
import com.explorer.musicblog.service.ISongTypeService;
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
		if(param != null) {
			ISongTypeService its = ServiceFactory.getSongTypeService();
			switch(param) {
				case "get":
					get(its,req,res);
					return;
				case "add":
					add(its,req,res);
					return;
				case "delete":
					delete(its,req,res);
					return;
				case "update":
					update(its,req,res);
					return;
				default:
					System.out.println("没有可执行的操作！");
			}
		}
		req.getRequestDispatcher("/MusicBlog/WEB-INF/404.jsp").forward(req, res);
	}

	private void delete(ISongTypeService its, HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		if(id != null && !"".equals(id) || id.length() > 0) {
			SongType type = new SongType();
			type.setId(Integer.parseInt(id));
			Integer i = its.delete(type);
			if(i != null && i > 0) {
				req.setAttribute("msg","删除成功！");
			} else {
				req.setAttribute("msg","删除失败！");
			}
			try {
				req.getRequestDispatcher("/WEB-INF/type/Type.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void update(ISongTypeService its, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		String type = req.getParameter("type");
		if(id != null && !"".equals(id.trim()) && type != null && !"".equals(type.trim())) {
			List<SongType> types = its.getAll();
			if(types != null && types.size() > 0) {
				req.setAttribute("title", "修改歌曲类型");
				for (SongType t : types) {
					if (t.getType().equals(type)) {
						req.setAttribute("msg", "您输入的类型已存在！");
						req.getRequestDispatcher("/WEB-INF/type/TypeUpdate.jsp").forward(req, res);
						return;
					}
					if (!t.getType().equals(type) && t.getId().equals(Integer.parseInt(id))) {
						t.setType(type);
						if (its.update(t) > 0) {
							req.setAttribute("msg", "修改类型成功！");
							req.getRequestDispatcher("/WEB-INF/type/Type.jsp").forward(req, res);
						}
						break;
					}
				}
			}
		}
	}

	private void add(ISongTypeService its, HttpServletRequest req, HttpServletResponse res) {
		String type = req.getParameter("type");
		if(type != null && !"".equals(type)) {
			List<SongType> types = its.getAll();
			if(types != null && types.size() > 0) {
				for (SongType t : types) {
					if(t.getType().equals(type)) {
						System.out.println("t:"+t);
						req.setAttribute("msg", "类型'"+t.getType()+"'已存在，请重新添加！");
						try {
							req.getRequestDispatcher("/WEB-INF/type/TypeAdd.jsp").forward(req, res);
							return;
						} catch (ServletException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				SongType t = new SongType();
				t.setType(type);
				Integer i = its.insert(t);
				if(i != null && i > 0) {
					System.out.println("i:"+i);
					req.setAttribute("msg", "添加成功！");
					List<SongType> ts = its.getAll();
					req.setAttribute("types",ts);
					try {
						req.getRequestDispatcher("/WEB-INF/type/TypeAdd.jsp").forward(req, res);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		req.setAttribute("msg","添加歌曲类型错误！请输入添加的类型！");
	}

	private void get(ISongTypeService its,HttpServletRequest req,HttpServletResponse res) {
		try {
			List<SongType> types = its.getAll();
			System.out.println("歌曲类型:"+types);
			req.setAttribute("title", "歌曲类型");
			if(types != null) {
				req.setAttribute("types", types);
				req.getRequestDispatcher("/WEB-INF/type/Type.jsp").forward(req, res);
			}
		} catch (Exception e) {
			req.setAttribute("msg","获取歌曲类型错误!");
			throw new RuntimeException("获取歌曲类型错误!\t"+e.getMessage());
		}
	}

}
