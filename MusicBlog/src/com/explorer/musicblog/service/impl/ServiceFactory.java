package com.explorer.musicblog.service.impl;

import com.explorer.musicblog.service.IArticleService;
import com.explorer.musicblog.service.ILyricService;
import com.explorer.musicblog.service.ISingerService;
import com.explorer.musicblog.service.ISongService;
import com.explorer.musicblog.service.ISongTypeService;
import com.explorer.musicblog.service.IUserService;

/**
 * zhangzhong
 * Dec 8, 2019 9:43:22 PM
 */
public class ServiceFactory {

	/**
	 * 获取“用户”Service实例
	 * @return
	 */
	public static IUserService getUserServiceInstace() {
		return new UserServiceImpl();
	}

	/**
	 * 	获取“歌手”Service实例
	 * @return
	 */
	public static ISingerService getSingerServiceInstace() {
		return new SingerServiceImpl();
	}

	/**
	 * 	获取“歌词”Service实例
	 * @return
	 */
	public static ILyricService getLyricServiceInstace() {
		return new LyricServiceImpl();
	}

	/**
	 * 	获取“歌曲”Service实例
	 * @return
	 */
	public static ISongService getSongServiceInstace() {
		return new SongServiceImpl();
	}

	/**
	 * 	获取“歌曲类型”Service实例
	 * @return
	 */
	public static ISongTypeService getSongTypeServiceInstace() {
		return new SongTypeServiceImpl();
	}

	/**
	 * 	获取“文章”Service实例
	 * @return
	 */
	public static IArticleService getArticleServiceInstace() {
		return new ArticleServiceImpl();
	}
}
