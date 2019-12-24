package com.explorer.musicblog.servlet;

import java.io.IOException;

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
@WebServlet(name = "/SongServlet",urlPatterns = "/SongServlet.do")
public class SongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String song = req.getParameter("song");
		ISongService sf = ServiceFactory.getSongService();
		if(song != null && !"".equals(song.trim())) {
			if("ADD".equals(song.toUpperCase())) {
				addSong(sf,req,res);
			}
		}
	}

	private void addSong(ISongService sf,HttpServletRequest req, HttpServletResponse res) {
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
			sin.setName(singer);
			s.setSinger(sin);
			Lyric l = new Lyric();
			l.setLyric(lyric);
			s.setLyric(l);
			Type t = new Type();
			t.setName(type);
			s.setType(t);
			s.setLength(length);
			System.out.println("song:"+s);
//			sf.insert(s);
		}
	}
}
