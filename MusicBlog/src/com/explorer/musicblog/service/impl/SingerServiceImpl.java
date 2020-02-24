package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISingerDao;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.pojo.Singer;
import com.explorer.musicblog.service.ISingerService;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午5:37:26
 */
public class SingerServiceImpl implements ISingerService{

	private ISingerDao singerDao = DaoFactory.getSingerDao();
	
	@Override
	public Integer insert(Singer singer) {
		return singerDao.insert(singer);
	}

	@Override
	public Integer delete(Integer id) throws Exception {
		return singerDao.delete(id);
	}

	@Override
	public List<Map<String,Singer>> getAllSinger() {
		return singerDao.getAllSinger();
	}

	@Override
	public List<Singer> getByName(String name) {
		return singerDao.getByName(name);
	}

	@Override
	public Singer getById(Integer id) throws Exception {
		return singerDao.getById(id);
	}

	@Override
	public Integer update(Singer type) {
		return singerDao.update(type);
	}

	@Override
	public Integer delete(Singer type) {
		return singerDao.delete(type);
	}

	@Override
	public Integer renew(String sql, Object... args) {
		return singerDao.renew(sql, args);
	}

	@Override
	public List<Singer> getAll() {
		return singerDao.getAll();
	}

	@Override
	public Integer getSize() throws Exception {
		return singerDao.getSize();
	}

	@Override
	public List<Map<String, Object>> query(Class<Singer> clazz, String sql, Object... args) throws Exception {
		return singerDao.query(clazz, sql, args);
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) {
		return singerDao.get(params);
	}

}
