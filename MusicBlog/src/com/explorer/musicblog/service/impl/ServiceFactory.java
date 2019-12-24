package com.explorer.musicblog.service.impl;

import com.explorer.musicblog.service.ILyricService;
import com.explorer.musicblog.service.ISingerService;
import com.explorer.musicblog.service.ISongService;
import com.explorer.musicblog.service.IUserService;

/**
 * zhangzhong
 * Dec 8, 2019 9:43:22 PM
 */
public class ServiceFactory {

	public static IUserService getUserService() {
		return new UserServiceImpl();
	}

	public static ISingerService getSingerService() {
		return new SingerServiceImpl();
	}

	public static ILyricService getLyricService() {
		return new LyricServiceImpl();
	}
	public static ISongService getSongService() {
		return new SongServiceImpl();
	}
}
