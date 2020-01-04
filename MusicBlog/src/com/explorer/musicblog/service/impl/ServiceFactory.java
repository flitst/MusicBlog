package com.explorer.musicblog.service.impl;

import com.explorer.musicblog.service.ILyricService;
import com.explorer.musicblog.service.ISingerService;
import com.explorer.musicblog.service.ISongService;
import com.explorer.musicblog.service.ITypeService;
import com.explorer.musicblog.service.IUserService;

/**
 * zhangzhong
 * Dec 8, 2019 9:43:22 PM
 */
public class ServiceFactory {

	/**
	 * 	获取“用户”service
	 * @return
	 */
	public static IUserService getUserService() {
		return new UserServiceImpl();
	}

	/**
	 * 	获取“歌手”service
	 * @return
	 */
	public static ISingerService getSingerService() {
		return new SingerServiceImpl();
	}

	/**
	 * 	获取“歌词”service
	 * @return
	 */
	public static ILyricService getLyricService() {
		return new LyricServiceImpl();
	}

	/**
	 * 	获取“歌曲”service
	 * @return
	 */
	public static ISongService getSongService() {
		return new SongServiceImpl();
	}

	/**
	 * 	获取“歌曲类型”service
	 * @return
	 */
	public static ITypeService getTypeService() {
		return new TypeServiceImpl();
	}
}
