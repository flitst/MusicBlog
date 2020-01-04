package com.explorer.musicblog.dao.impl;

import com.explorer.musicblog.dao.ILyricDAO;
import com.explorer.musicblog.dao.ISingerDAO;
import com.explorer.musicblog.dao.ISongDAO;
import com.explorer.musicblog.dao.ITypeDAO;
import com.explorer.musicblog.dao.IUserDAO;

/**
 * zhangzhong
 * Nov 29, 2019 12:55:26 AM
 */
public class DaoFactory {

	public static IUserDAO getUserDao() {
		return new UserDaoImpl();
	}
	public static ITypeDAO getTypeDao() {
		return new TypeDaoImpl();
	}
	public static ISingerDAO getSingerDao() {
		return new SingerDaoImpl();
	}
	public static ISongDAO getSongDao() {
		return new SongDaoImpl();
	}
	public static ILyricDAO getLyricDao() {
		return new LyricDaoImpl();
	}
}
