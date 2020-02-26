package com.explorer.musicblog.servlet;

import java.awt.Color;
import java.awt.Font;
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
		BufferedImage bi = new BufferedImage(120, 60, BufferedImage.TYPE_INT_BGR);
		// 创建画布
		Graphics g = bi.getGraphics();
		// 创建颜色
		//Color c = new Color(134,234,150);
		
		g.setColor(Color.gray);
		g.setFont(new Font("微软雅黑", Font.ITALIC, 20));
		g.fillRect(0, 0, 120, 60);
		char[] ch = "ABCDEFGHIGKLMNOPQRSTUVWXYZ023456789abcdefghigkmnopqrstuvwxyz".toCharArray();
		int len = ch.length;
		int index = 0;
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(30),r.nextInt(65),r.nextInt(50)));
			g.drawString(ch[index]+"", (i*25)+15, 30);
			sb.append(ch[index]);
		}
		request.getSession().setAttribute("code", sb.toString());
		ImageIO.write(bi, "png", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
