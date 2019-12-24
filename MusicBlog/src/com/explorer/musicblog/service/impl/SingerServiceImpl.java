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
	public void insert(Singer singer) {
		singerDao.insert(singer);
	}

	@Override
	public void delete(Integer id) {
		singerDao.delete(id);
	}

	@Override
	public void update(Integer id,Singer singer) {
		singerDao.update(id,singer);
	}

	@Override
	public List<Map<String,Singer>> getAllSinger() {
		return singerDao.getAllSinger();
	}

	@Override
	public Singer getSinger(String name) {
		return singerDao.getSinger(name);
	}

	@Override
	public Singer getSingerById(Integer id) {
		return singerDao.getSingerById(id);
	}

}
