package com.explorer.musicblog.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileUtils {

	public static BufferedImage getImage() {
		// 创建图片缓冲区
		BufferedImage bi = new BufferedImage(50, 35, BufferedImage.TYPE_BYTE_GRAY);
		// 创建画布
		Graphics g = bi.getGraphics();
		// 创建颜色
		Color c = new Color(134,234,150);
		g.setColor(c);
		g.fillRect(0, 0, 50, 35);
		char[] ch = "ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		int len = ch.length;
		int index = 0;
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(20),r.nextInt(35),r.nextInt(50)));
			g.drawString(ch[index]+"", 20, 30);
			sb.append(ch[index]);
		}
		return bi;
	}
	
	/**
	 * 获取文件夹下所有的文件（包含子目录里的文件）
	 * @param files
	 * @param path
	 */
	public static void getFiles(List<File> files, String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				if (f.isDirectory()) {
					getFiles(files, path);
				} else {
					files.add(f);
				}
			}
		}
	}
	
	/**
	 * 获取文件夹下文件列表（仅获取当前目录）
	 * @param path
	 * @return
	 */
	public static List<File> getFiles(String path){
		List<File> lists = new ArrayList<>();
		if(path != null && !"".equals(path.trim())) {
			File file = new File(path);
			if(file != null && file.exists()) {
				File[] files = file.listFiles();
				for (File f : files) {
					lists.add(f);
				}
			}
		}
		return lists;
	}
	
	/**
	 * 获取单个文件
	 * @param path
	 * @return
	 */
	public static File getFile(String path){
		if(path != null && !"".equals(path)) {
			System.out.println("path:"+path);
			File file = new File(path);
			System.out.println("file:"+file);
			if(file != null && file.exists()) {
				File f = file.getParentFile();
				System.out.println("files:"+f);
				System.out.println("name:"+f.getName());
				System.out.println("parent:"+f.getParent());
				System.out.println("parentFile:"+f.getParentFile());
				return file;
			}
		}
		return null;
	}
	
}
