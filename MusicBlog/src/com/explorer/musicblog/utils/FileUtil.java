package com.explorer.musicblog.utils;

import java.io.File;
import java.util.List;

public class FileUtil {

	/**
	 * 	获取文件列表
	 * @param path
	 * @return
	 */
	public static List<File> getFiles(String path){
		if(path != null && !"".equals(path)) {
			File file = new File(path);
			System.out.println("file:"+file);
//			file.isDirectory();
			if(file != null && file.exists()) {
				File[] files = file.listFiles();
				System.out.println("length:"+files.length);
				System.out.println("files:"+files);
				for (int i = 0; i < files.length; i++) {
					System.out.println("fileName:"+files[i].getName());
				}
			}
		}
		return null;
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
