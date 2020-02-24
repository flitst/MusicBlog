package com.explorer.musicblog.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UploadUtils {

	@SuppressWarnings("unused")
	private String directory = null;
	
	public UploadUtils() {}
	
	public UploadUtils(String directory) {
		this.directory = directory;
	}
	
	public static File[] getFiles(String directory) {
		if(directory != null && !directory.isEmpty()) {
			File dir = new File(directory);
			if(dir.isDirectory()) {
				File file = dir.getParentFile();
				if(file.isFile()) {
					File[] files = file.listFiles();
					return files;
				}
			}
		}
		return null;
	}
	
	public static String getDirectory(String file) {
		if(file != null && !file.isEmpty()) {
			
			return "";
		}
		return null;
	}
	
	public static String isDirectory(String name) {
		String str = StringUtils.isLegal(name);
		if(str != null) {
			File file = new File(str);
			if(file != null && file.exists()) {
				if(file.isDirectory()) {
					return file.getPath();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	public static List<File> isFile(String fileName) {
		String str = StringUtils.isLegal(fileName);
		if (str != null) {
			List<File> all = new ArrayList<File>();
			File file = new File(str);
			if (file == null && !file.exists()) {
				return null;
			}
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isHidden() && !f.canRead()) {
					continue;
				}
				if (f.isFile()) {
					all.add(file);
				}
				if (f.isDirectory()) {
					isFile(f.getPath());
				}
			}
			return all;
		}
		return null;
	}
}
