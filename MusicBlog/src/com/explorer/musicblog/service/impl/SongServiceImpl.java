package com.explorer.musicblog.service.impl;

import java.util.List;

import com.explorer.musicblog.dao.ISongDao;
import com.explorer.musicblog.dao.impl.SongDaoImpl;
import com.explorer.musicblog.pojo.Song;
import com.explorer.musicblog.service.ISongService;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午11:07:08
 */
public class SongServiceImpl implements ISongService{

	ISongDao iSongDao = new SongDaoImpl();

	@Override
	public Integer insert(Song song) {
		return iSongDao.insert(song);
	}

	@Override
	public Integer delete(int id, String name) {
		return iSongDao.delete(id, name);
	}

	@Override
	public Integer update(Song song) {
		return iSongDao.update(song);
	}
	
	@Override
	public List<Song> getAll() {
		return iSongDao.getAll();
	}
	
	@Override
	public Song getSongById(int id, String name) {
		return iSongDao.getSongById(id, name);
	}

}
