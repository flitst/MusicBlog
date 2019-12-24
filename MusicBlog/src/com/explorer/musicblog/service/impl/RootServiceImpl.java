package com.explorer.musicblog.service.impl;

import com.explorer.musicblog.dao.IRootDao;
import com.explorer.musicblog.dao.impl.RootDaoImpl;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Root;
import com.explorer.musicblog.service.IRootService;

/**
 * zhangzhong
 * 2018年5月28日下午11:47:48
 */
public class RootServiceImpl implements IRootService{

	private IRootDao rootDao = new RootDaoImpl();

	@Override
	public Root getRoot(String name,String pass) throws CustomException {
		return rootDao.getRoot(name, pass);
	}
}
