package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ILyricDao;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Lyric;
import com.explorer.musicblog.service.ILyricService;

/**
 * zhangzhong
 * Dec 11, 2019 6:54:57 PM
 */
public class LyricServiceImpl implements ILyricService {

	ILyricDao lyricDao = DaoFactory.getLyricDaoInstace();

	@Override
	public List<Lyric> getByName(String name) throws Exception {
		return lyricDao.getByName(name);
	}

	@Override
	public List<Lyric> getAll() throws Exception {
		return lyricDao.getAll();
	}

	@Override
	public Integer insert(Lyric type) throws Exception {
		return lyricDao.insert(type);
	}

	@Override
	public Integer update(Lyric type) throws Exception {
		return lyricDao.update(type);
	}

	@Override
	public Integer delete(Lyric type) throws Exception {
		return lyricDao.delete(type);
	}

	@Override
	public Integer commonCUD(String sql, Object... args) throws Exception {
		return lyricDao.renew(sql, args);
	}
	
	@Override
	public List<Map<String, Object>> commonQuery(Class<Lyric> clazz, String sql, Object... args)
			throws CustomException, Exception {
		return lyricDao.query(clazz, sql, args);
	}
	
	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) throws CustomException, Exception {
		return lyricDao.get(params);
	}

	@Override
	public Integer getSize() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lyric getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
