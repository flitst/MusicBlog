package com.explorer.musicblog.dao.impl;

import com.explorer.musicblog.dao.ILyricDao;
import com.explorer.musicblog.dao.ISingerDao;
import com.explorer.musicblog.dao.ITypeDao;
import com.explorer.musicblog.dao.IUserDao;

/**
 * zhangzhong
 * Nov 29, 2019 12:55:26 AM
 */
public class DaoFactory {

	public static IUserDao getUserDao() {
		return new UserDaoImpl();
	}
	public static ITypeDao getTypeDao() {
		return new TypeDaoImpl();
	}
	public static ISingerDao getSingerDao() {
		return new SingerDaoImpl();
	}
	public static ILyricDao getLyricDao() {
		return new LyricDaoImpl();
	}
}
