package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISongDao;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.pojo.Song;
import com.explorer.musicblog.service.ISongService;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午11:07:08
 */
public class SongServiceImpl implements ISongService{

	ISongDao songDao = DaoFactory.getSongDao();

	@Override
	public Integer insert(Song song) {
		return songDao.insert(song);
	}

	@Override
	public Integer delete(Integer id) throws Exception {
		return songDao.delete(id);
	}

	@Override
	public Integer update(Song song) {
		return songDao.update(song);
	}
	
	@Override
	public List<Song> getAll() {
		return songDao.getAll();
	}
	
	@Override
	public Song getSongById(int id, String name) {
		return songDao.getSongById(id, name);
	}

	@Override
	public List<Song> getByName(Integer num,String name) {
		return songDao.getByName(num,name);
	}

	@Override
	public Integer delete(Song type) {
		return songDao.delete(type);
	}

	@Override
	public Integer renew(String sql, Object... args) {
		return songDao.renew(sql, args);
	}

	@Override
	public List<Song> getByName(String name) {
		return songDao.getByName(name);
	}

	@Override
	public Song getById(Integer id) throws Exception {
		return songDao.getById(id);
	}

	@Override
	public Integer getSize() throws Exception {
		return songDao.getSize();
	}

	@Override
	public List<Map<String, Object>> query(Class<Song> clazz, String sql, Object... args) throws Exception {
		return songDao.query(clazz, sql, args);
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) {
		return songDao.get(params);
	}
}
