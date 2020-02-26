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
	 * 用户Dao实例
	 * @return
	 */
	public static IUserDao getUserDaoInstace() {
		return new UserDaoImpl();
	}
	
	/**
	 * 歌曲类型Dao实例
	 * @return
	 */
	public static ISongTypeDao getTypeDaoInstace() {
		return new SongTypeDaoImpl();
	}
	
	/**
	 * 歌手Dao实例
	 * @return
	 */
	public static ISingerDao getSingerDaoInstace() {
		return new SingerDaoImpl();
	}
	
	/**
	 * 歌曲Dao实例
	 * @return
	 */
	public static ISongDao getSongDaoInstace() {
		return new SongDaoImpl();
	}
	
	/**
	 * 歌词Dao实例
	 * @return
	 */
	public static ILyricDao getLyricDaoInstace() {
		return new LyricDaoImpl();
	}
	
	/**
	 * 文章Dao实例
	 * @return
	 */
	public static IArticleDao getArticleDaoInstace() {
		return new ArticleDaoImpl();
	}
	
}
