package com.explorer.musicblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.service.ITypeService;
import com.explorer.musicblog.service.impl.TypeServiceImpl;

/**
 * Type Servlet
 */
@WebServlet(name="/TypeServlet",urlPatterns = "/TypeServlet.do")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(req.getServletContext().getAttribute("types") == null) {
			ITypeService is = new TypeServiceImpl();
			List<Map<String,Object>> types = new ArrayList<>();
			try {
				types = is.getType();
				System.out.println("歌曲类型:"+types);
				if(types != null) {
					req.getServletContext().setAttribute("types", types);
				}
			} catch (Exception e) {
				throw new RuntimeException("获取歌曲类型错误!");
			}
		}
	}

}
