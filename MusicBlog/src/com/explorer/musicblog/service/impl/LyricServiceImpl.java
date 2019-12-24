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

	ILyricDao ild = DaoFactory.getLyricDao();
	@Override
	public Integer commonCUD(String sql, Object... args) throws Exception {
		return ild.renew(sql, args);
	}

	@Override
	public List<Map<String, Object>> commonQuery(Class<Lyric> clazz, String sql, Object... args)
			throws CustomException, Exception {
		return ild.query(clazz, sql, args);
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) throws CustomException, Exception {
		return ild.get(params);
	}

}
