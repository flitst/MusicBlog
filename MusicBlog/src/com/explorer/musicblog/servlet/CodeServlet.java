package com.explorer.musicblog.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 17879
 */
@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建图片缓冲区
		BufferedImage bi = new BufferedImage(80, 35, BufferedImage.TYPE_BYTE_GRAY);
		// 创建画布
		Graphics g = bi.getGraphics();
		// 创建颜色
		Color c = new Color(134,234,150);
		g.setColor(c);
		g.fillRect(0, 0, 80, 35);
		char[] ch = "ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789abcdefghigklmnopqrstuvwxyz".toCharArray();
		int len = ch.length;
		int index = 0;
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(30),r.nextInt(65),r.nextInt(50)));
			g.drawString(ch[index]+"", (i*17)+10, 20);
			sb.append(ch[index]);
		}
		request.getSession().setAttribute("code", sb.toString());
		ImageIO.write(bi, "png", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
