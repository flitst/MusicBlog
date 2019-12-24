package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.pojo.Singer;
import com.explorer.musicblog.service.ISingerService;
import com.explorer.musicblog.service.impl.SingerServiceImpl;

/**
 * zhangzhong
 * Dec 7, 2019 12:21:28 AM
 */
@SuppressWarnings("serial")
@WebServlet(name = "GetSingerServlet",urlPatterns = "/GetSingerServlet.do")
public class GetSingerServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ISingerService iss = new SingerServiceImpl();
		List<Map<String,Singer>> all = iss.getAllSinger();
		if(all != null) {
			req.setAttribute("all", all);
			req.getRequestDispatcher("GetSinger.jsp").forward(req, resp);
		}
	}
}
