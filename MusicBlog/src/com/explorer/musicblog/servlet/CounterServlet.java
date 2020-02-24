package com.explorer.musicblog.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class CounterServlet
 */
@WebServlet("/CounterServlet")
public class CounterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//写入站点访问次数文件
	public static void writeFile(String fileName,int count) {
		System.out.println("fileName:"+fileName+" count:"+count);
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileName));
			out.println(count);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//读取站点访问次数文件
	public static int readFile(String fileName) {
		System.out.println("fileName:"+fileName);
		File file = new File(fileName);
		int count = 0;
		if(!file.exists()) {
			writeFile(fileName,count);
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			count = Integer.parseInt(br.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
