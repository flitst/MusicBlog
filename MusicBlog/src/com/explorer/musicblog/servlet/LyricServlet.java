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

import com.explorer.musicblog.service.ILyricService;
import com.explorer.musicblog.service.impl.ServiceFactory;

/**
 * zhangzhong
 * Dec 11, 2019 7:05:45 PM
 */
@WebServlet(urlPatterns = "/LyricServlet.do")
public class LyricServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getQueryString();
		System.out.println("add:"+str);
		ILyricService ils = ServiceFactory.getLyricService();
		if(str != null && !"".equals(str.trim())) {
			if("ADD".equals(str.toUpperCase())) {
				add(ils,req,resp);
			} else if("UPDATE".equals(str.toUpperCase())) {
				try {
					update(ils,req,resp);
				} catch (Exception e) {
					throw new RuntimeException("修改歌词错误!"+e.getMessage());
				}
			} else if("DEL".equals(str.toUpperCase())) {
				try {
					del(ils,req,resp);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除歌词信息失败!"+e.getMessage());
				}
			} else if("ID".equals(str.toUpperCase())) {
				try {
					getById(ils,req,resp);
				} catch (Exception e) {
					throw new RuntimeException("获取歌词信息错误!"+e.getMessage());
				}
			} else if("ALL".equals(str.toUpperCase())) {
				try {
					getAll(ils,req,resp);
				} catch (Exception e) {
					throw new RuntimeException("获取歌词信息错误!"+e.getMessage());
				}
			}
		} else {
			throw new RuntimeException("请求参数不能为空!");
		}
	}

	private void getAll(ILyricService ils, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<Map<String, Object>> list = ils.get(null);
		if(list != null && list.size() > 0) {
			req.setAttribute("all", list);
			req.getRequestDispatcher("LyricGetAll.jsp").forward(req, resp);
		}
		req.setAttribute("msg", "获取所有歌词失败!");
		req.getRequestDispatcher("LyricMSG.jsp").forward(req, resp);
	}

	private void del(ILyricService ils,HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		if(id != null && !"".equals(id.trim())) {
			List<Map<String,Object>> list = new ArrayList<>();
			Map<String,Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> params = getParams(ils,list,map,"`id`","=",id);
			if(params != null && params.size() > 0) {
				String sql = "delete from `tb_lyric` where `id`=?";
				Integer num = ils.commonCUD(sql, id);
				if(num != null && num > 0) {
					System.out.println("删除歌词成功!");
					req.setAttribute("msg", "删除歌词成功!");
				} else {
					System.out.println("删除歌词失败!");
					req.setAttribute("msg", "删除歌词失败!");
				}
				req.getRequestDispatcher("Lyric.jsp").forward(req, resp);
			}
		}
	}
	private List<Map<String,Object>> getParams(ILyricService ils,List<Map<String,Object>> list,Map<String,Object> map,
			String param,String pattern,String value) throws Exception{
		map.put("param", param);
		map.put("pattern", pattern);
		map.put("value", value);
		list.add(map);
		List<Map<String, Object>> lyrics = ils.get(list);
		return lyrics;
	}
	private void getById(ILyricService ils, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		if(id != null && !"".equals(id.trim())) {
			List<Map<String,Object>> list = new ArrayList<>();
			Map<String,Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> params = getParams(ils,list,map,"`id`","=",id);
			if(params != null && params.size() > 0) {
				req.setAttribute("lyric",params);
				req.getRequestDispatcher("LyricGet.jsp").forward(req, resp);
				return;
			} else {
				req.setAttribute("msg","获取歌词信息失败!");
			}
			req.setAttribute("msg","获取歌词信息失败!");
		} else {
			req.setAttribute("msg","请求参数不能为空!");
		}
		req.getRequestDispatcher("LyricMSG.jsp").forward(req, resp);
	}

	private void update(ILyricService ils,HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		System.out.println("id:"+id);
		if(id != null && !"".equals(id.trim())) {
			List<Map<String,Object>> list = new ArrayList<>();
			Map<String,Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> lyrics = getParams(ils,list,map,"`id`","=",id);
			if(lyrics != null && lyrics.size() > 0) {
				String song = req.getParameter("song");
				String lyric = req.getParameter("lyric");
				String singer = req.getParameter("singer");
				String content = req.getParameter("content");
				if(song != null && !"".equals(song.trim()) && lyric != null && !"".equals(lyric.trim()) &&
						singer != null && !"".equals(singer.trim()) && content != null && !"".equals(content.trim())) {
					String sql = "update `tb_lyric` set `song`=?,`lyric`=?,`singer`=?,`content`=? where id=?";
					Integer count = ils.commonCUD(sql,song,lyric,singer,content,id);
					if(count != null && count > 0) {
						req.setAttribute("msg", "修改歌词成功!");
						System.out.println("修改歌词成功!");
						req.getRequestDispatcher("LyricMSG.jsp").forward(req, resp);
						return;
					}
				}
			} else {
				req.setAttribute("msg", "修改歌词失败!未找到歌词ID");
			}
		} else {
			req.setAttribute("msg", "修改歌词失败!歌词ID不能为空!");
		}
		req.getRequestDispatcher("Lyric.jsp").forward(req, resp);
	}
	private void add(ILyricService ils,HttpServletRequest req, HttpServletResponse resp) {
		String song = req.getParameter("song");
		String lyric = req.getParameter("lyric");
		String singer = req.getParameter("singer");
		String content = req.getParameter("content");
		if(song != null && lyric != null && singer != null && content != null) {
			String sql = "insert into `tb_lyric`(`song`,`lyric`,`singer`,`content`)values(?,?,?,?)";
			try {
				Integer count = ils.commonCUD(sql,song,lyric,singer,content);
				if(count != null && count > 0) {
					req.setAttribute("msg", "添加歌词成功!");
					req.setAttribute("count", count);
					System.out.println("添加歌词成功!"+count);
					req.getRequestDispatcher("LyricMSG.jsp").forward(req, resp);
				} else {
					req.setAttribute("msg", "添加歌词失败!");
					req.getRequestDispatcher("Lyric.jsp").forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
