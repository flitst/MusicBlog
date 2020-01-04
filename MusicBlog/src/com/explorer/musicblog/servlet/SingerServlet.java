package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.pojo.Singer;
import com.explorer.musicblog.service.ISingerService;
import com.explorer.musicblog.service.impl.ServiceFactory;

/**
 * zhangzhong
 * Dec 10, 2019 7:01:20 PM
 * 	歌手servlet
 */
@WebServlet(name = "SingerServlet",urlPatterns = "/SingerServlet.do")
public class SingerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reqStr = req.getQueryString();
		if(reqStr != null && !"".equals(reqStr) && reqStr.length() > 0) {
			System.out.println("reqStr:"+reqStr);
			ISingerService iss = ServiceFactory.getSingerService();
			switch(reqStr.toUpperCase()) {
				case "GETBYNAME":
					getByName(iss,req,resp);
					break;
				case "ADD":
					add(iss,req,resp);
					break;
				case "UPDATE":
					update(iss,req,resp);
					break;
				case "DEL":
					del(iss,req,resp);
					break;
				case "GETALL":
					getAll(iss,req,resp);
					break;
			}
		}
	}
	
	private void getByName(ISingerService iss,HttpServletRequest req, HttpServletResponse resp){
		String name = req.getParameter("singername");
		if(name != null && !"".equals(name.trim())) {
			List<Singer> singers = iss.getSinger(name);
			req.setAttribute("singers", singers);
			try {
				req.getRequestDispatcher("Singer.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("查询歌手信息失败!");
		}
	}
	
	private void add(ISingerService iss,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String head = req.getParameter("head");
		String image = req.getParameter("image");
		String[] sexs = req.getParameterValues("sex");
		Singer s = new Singer();
		if(name != null && !"".equals(name.trim()) && age != null && !"".equals(age) && sexs != null && sexs.length > 0) {
			s.setName(name);
			s.setAge(Byte.parseByte(age));
			for(int i = 0; i<sexs.length; i++) {
				if("MAN".equals(sexs[i].toUpperCase())) {
					s.setSex((byte)1);
				} else if("LADY".equals(sexs[i].toUpperCase())) {
					s.setSex((byte)2);
				} else {
					s.setSex((byte)0);
				}
			}
			s.setHead(head);
			s.setImage(image);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			s.setCreateTime(sdf.format(new Date()));
			s.setUpdateTime(sdf.format(new Date()));
			Integer i = iss.insert(s);
			if(i != null && i > 0) {
				req.setAttribute("msg","添加歌手信息成功!");
				req.getRequestDispatcher("Singer.jsp").forward(req, resp);
			}
		}
		System.out.println("添加歌手信息失败!");
		req.setAttribute("msg","添加歌手信息失败!");
	}
	
	private void update(ISingerService iss,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("getByName");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String head = req.getParameter("head");
		String image = req.getParameter("image");
		String[] sexs = req.getParameterValues("sex");
		Singer s = new Singer();
		if(name != null && !"".equals(name.trim()) && age != null && !"".equals(age) && sexs != null && sexs.length > 0) {
			s.setName(name);
			s.setAge(Byte.parseByte(age));
			for(int i = 0; i<sexs.length; i++) {
				if("MAN".equals(sexs[i].toUpperCase())) {
					s.setSex((byte)1);
				} else if("LADY".equals(sexs[i].toUpperCase())) {
					s.setSex((byte)2);
				} else {
					s.setSex((byte)0);
				}
			}
			s.setHead(head);
			s.setImage(image);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			s.setCreateTime(sdf.format(new Date()));
			s.setUpdateTime(sdf.format(new Date()));
			iss.insert(s);
		} else {
			System.out.println("修改歌手信息失败!");
		}
	}
	
	private void getAll(ISingerService iss,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("getAll");
		List<Map<String, Singer>> all = iss.getAllSinger();
		System.out.println("all:"+all);
		if(all != null && all.size() > 0) {
			req.setAttribute("all", all);
			req.setAttribute("msg","获取歌手信息成功!");
			req.getRequestDispatcher("SingerGetAll.jsp").forward(req, resp);
		} else {
			System.out.println("获取歌手信息失败!");
			req.setAttribute("msg","获取歌手信息失败!");
		}
	}
	
	private void del(ISingerService iss,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("del");
		String id = req.getParameter("id");
		System.out.println(id);
		if(id != null && !"".equals(id.trim())) {
			iss.delete(Integer.parseInt(id));
		} else {
			System.out.println("删除歌手信息失败!");
		}
	}
}
