package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.pojo.Lyric;
import com.explorer.musicblog.pojo.Singer;
import com.explorer.musicblog.pojo.Song;
import com.explorer.musicblog.pojo.Type;
import com.explorer.musicblog.service.ISongService;
import com.explorer.musicblog.service.impl.ServiceFactory;

/**
 * 歌曲修改Servlet
 */
@WebServlet(urlPatterns = "/SongServlet.do")
public class SongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String song = req.getParameter("song");
		System.out.println("song:"+song);
		ISongService sf = ServiceFactory.getSongService();
		if(song != null && !"".equals(song.trim())) {
			switch(song.toUpperCase()) {
				case "ADD":
					addSong(sf,req,res);
					break;
				case "UPDATE":
					updateSong(sf,req,res);
					break;
				case "GETALL":
					getAllSong(sf,req,res);
					break;
				case "GETBYNAME":
					getByName(sf,req,res);
					break;
					
			}
		}
	}

	private void getByName(ISongService sf, HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("search");
		if(name != null && !"".equals(name)) {
			List<Song> songs = sf.getByName(0,name);
			System.out.println("songs:"+songs);
			req.getSession().setAttribute("songs", songs);
			try {
				req.getRequestDispatcher("index.jsp").forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void getAllSong(ISongService sf, HttpServletRequest req, HttpServletResponse res) {
		List<Song> all = sf.getAll();
		System.out.println("all:"+all);
		req.getSession().setAttribute("all", all);
		try {
			req.getRequestDispatcher("ShowAllSong.jsp").forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addSong(ISongService sf,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String singer = req.getParameter("singer");
		String lyric = req.getParameter("lyric");
		String type = req.getParameter("type");
		String length = req.getParameter("length");
		System.out.println("name"+name);
		System.out.println("singer"+singer);
		System.out.println("lyric"+lyric);
		System.out.println("type"+type);
		System.out.println("length"+length);
		if(name != null && !"".equals(name.trim()) && singer != null && !"".equals(singer.trim()) && lyric != null && !"".equals(lyric.trim())
				&& type != null && !"".equals(type.trim()) && length != null && !"".equals(length.trim())) {
			Song s = new Song();
			String str = type.substring(0, 1);
			System.out.println(str);
			if(str != null && !"".equals(str.trim())) {
				s.setId(Integer.parseInt(str));
			}
			s.setName(name);
			Singer sin = new Singer();
//			sin.setName(singer);
//			s.setSinger(sin);
			Lyric l = new Lyric();
			l.setLyric(lyric);
//			s.setLyric(l);
			Type t = new Type();
			t.setName(type);
//			s.setType(t);
			s.setLength(length);
			System.out.println("song:"+s);
			UploadServlet us = new UploadServlet(s.getName());
			us.doGet(req, res);
			sf.insert(s);
		}
	}
	
	private void updateSong(ISongService sf,HttpServletRequest req, HttpServletResponse res) {
		
	}
}
