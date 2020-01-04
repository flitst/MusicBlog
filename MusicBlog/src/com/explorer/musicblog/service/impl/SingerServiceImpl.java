package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISingerDAO;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.pojo.Singer;
import com.explorer.musicblog.service.ISingerService;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午5:37:26
 */
public class SingerServiceImpl implements ISingerService{

	private ISingerDAO singerDao = DaoFactory.getSingerDao();
	
	@Override
	public Integer insert(Singer singer) {
		return singerDao.insert(singer);
	}

	@Override
	public Integer delete(Integer id) {
		return singerDao.delete(id);
	}

	@Override
	public Integer update(Integer id,Singer singer) {
		return singerDao.update(id,singer);
	}

	@Override
	public List<Map<String,Singer>> getAllSinger() {
		return singerDao.getAllSinger();
	}

	@Override
	public List<Singer> getSinger(String name) {
		return singerDao.getSinger(name);
	}

	@Override
	public Singer getSingerById(Integer id) {
		return singerDao.getSingerById(id);
	}

}
