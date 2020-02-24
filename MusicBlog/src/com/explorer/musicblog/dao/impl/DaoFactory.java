package com.explorer.musicblog.dao.impl;

import com.explorer.musicblog.dao.IArticleDao;
import com.explorer.musicblog.dao.ILyricDao;
import com.explorer.musicblog.dao.ISingerDao;
import com.explorer.musicblog.dao.ISongDao;
import com.explorer.musicblog.dao.ISongTypeDao;
import com.explorer.musicblog.dao.IUserDao;

/**
 * zhangzhong
 * Nov 29, 2019 12:55:26 AM
 */
public class DaoFactory {

	/**
	 * 用户实例
	 * @return
	 */
	public static IUserDao getUserDao() {
		return new UserDaoImpl();
	}
	
	/**
	 * 歌曲类型实例
	 * @return
	 */
	public static ISongTypeDao getTypeDao() {
		return new SongTypeDaoImpl();
	}
	
	/**
	 * 歌手实例
	 * @return
	 */
	public static ISingerDao getSingerDao() {
		return new SingerDaoImpl();
	}
	
	/**
	 * 歌曲实例
	 * @return
	 */
	public static ISongDao getSongDao() {
		return new SongDaoImpl();
	}
	
	/**
	 * 歌词实例
	 * @return
	 */
	public static ILyricDao getLyricDao() {
		return new LyricDaoImpl();
	}
	
	/**
	 * 文章实例
	 * @return
	 */
	public static IArticleDao getArticleDao() {
		return new ArticleDaoImpl();
	}
	
}
