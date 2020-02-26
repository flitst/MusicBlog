package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.pojo.Lyric;
import com.explorer.musicblog.pojo.Song;
import com.explorer.musicblog.pojo.SongType;
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
		ISongService songService = ServiceFactory.getSongServiceInstace();
		if(song != null){
			switch(song) {
				case "add":
					addSong(songService,req,res);
					return;
				case "update":
					updateSong(songService,req,res);
					return;
				case "all":
					getAllSong(songService,req,res);
					return;
				case "name":
					getByName(songService,req,res);
					return;
				default :
					System.out.println("没有可执行的操作！");
			}
		}
		req.getRequestDispatcher("/MusicBlog/WEB-INF/404.jsp").forward(req, res);
	}

	private void getByName(ISongService sf, HttpServletRequest req, HttpServletResponse res) {
		String keyword = req.getParameter("search");
		if (keyword != null && !"".equals(keyword.trim())) {
			List<Song> songs = sf.getByName(0,keyword);
			System.out.println("songs:"+songs);
			String name = req.getParameter("name");
			if(name != null && "".equals(name.trim())) {
				req.setAttribute("name", keyword);
			}
			req.setAttribute("songs", songs);
		} else {
			req.setAttribute("keyword", keyword);
		}
		try {
			req.getRequestDispatcher("index.jsp").forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void getAllSong(ISongService sf, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Song> all = sf.getAll();
		System.out.println("all:"+all);
		req.setAttribute("all", all);
		req.getRequestDispatcher("/WEB-INF/song/Song.jsp").forward(req, res);
	}

	private void addSong(ISongService sf,HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String song = req.getParameter("file");
		System.out.println("song:"+song);
		if (song != null) {
			System.out.println("...");
			return ;
		}
		String name = req.getParameter("name");
		String singer = req.getParameter("singer");
		String lyric = req.getParameter("lyric");
		String type = req.getParameter("type");
		String createTime = req.getParameter("createtime");
		String updateTime = req.getParameter("updatetime");
		System.out.println("name:"+name);
		System.out.println("singer:"+singer);
		System.out.println("lyric:"+lyric);
		System.out.println("type:"+type);
		if(name != null && !"".equals(name.trim()) && singer != null && !"".equals(singer.trim()) && lyric != null && !"".equals(lyric.trim())
				&& type != null && !"".equals(type.trim()) && createTime != null && !"".equals(createTime.trim())
				&& updateTime != null && !"".equals(updateTime.trim())) {
			Song s = new Song();
			String str = type.substring(0, 1);
			System.out.println(str);
			if(str != null && !"".equals(str.trim())) {
				s.setId(Integer.parseInt(str));
			}
			s.setName(name);
			//Singer sin = new Singer();
//			sin.setName(singer);
//			s.setSinger(sin);
			Lyric l = new Lyric();
			l.setLyric(lyric);
//			s.setLyric(l);
			SongType t = new SongType();
			t.setType(type);
//			s.setType(t);
			s.setCreateTime(createTime);
			s.setUpdateTime(updateTime);
			System.out.println("song:"+s);
			UploadServlet us = new UploadServlet(s.getName());
			us.doGet(req, res);
			sf.insert(s);
		}
	}
	
	private void updateSong(ISongService sf,HttpServletRequest req, HttpServletResponse res) {
		
	}
}
