package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISongTypeDao;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.SongType;
import com.explorer.musicblog.service.ISongTypeService;

/**
 * zhangzhong
 * Dec 6, 2019 8:52:20 PM
 */
public class SongTypeServiceImpl implements ISongTypeService {

	private ISongTypeDao songTypeDao  = DaoFactory.getTypeDao();

	@Override
	public List<SongType> getByName(String name) {
		return songTypeDao.getByName(name);
	}
	

	@Override
	public List<SongType> getAll() {
		return songTypeDao .getAll();
	}

	@Override
	public Integer insert(SongType type) {
		return songTypeDao .insert(type);
	}

	@Override
	public Integer update(SongType type) {
		return songTypeDao .update(type);
	}

	@Override
	public Integer delete(SongType type) {
		return songTypeDao .delete(type);
	}

	@Override
	public Integer commonCUD(String sql, Object... args) throws Exception {
		return songTypeDao .renew(sql, args);
	}

	@Override
	public List<Map<String, Object>> commonQuery(Class<SongType> clazz, String sql, Object... args)
			throws CustomException, Exception {
		return songTypeDao .query(clazz, sql, args);
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) throws CustomException, Exception {
		return songTypeDao .get(params);
	}

	@Override
	public Integer getSize() throws Exception {
		return songTypeDao.getSize();
	}


	@Override
	public SongType getById(Integer id) throws Exception {
		return songTypeDao.getById(id);
	}


	@Override
	public Integer delete(Integer id) throws Exception {
		return songTypeDao.delete(id);
	}
}
