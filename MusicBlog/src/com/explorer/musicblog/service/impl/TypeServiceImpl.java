package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ITypeDAO;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Type;
import com.explorer.musicblog.service.ITypeService;

/**
 * zhangzhong
 * Dec 6, 2019 8:52:20 PM
 */
public class TypeServiceImpl implements ITypeService {

	private ITypeDAO it = DaoFactory.getTypeDao();

	@Override
	public List<Type> getAll() {
		return it.getAll();
	}

	@Override
	public Integer insert(Type type) {
		return it.insert(type);
	}

	@Override
	public Integer update(Type type) {
		return it.update(type);
	}

	@Override
	public Integer delete(Type type) {
		return it.delete(type);
	}

	@Override
	public Integer commonCUD(String sql, Object... args) throws Exception {
		return it.renew(sql, args);
	}

	@Override
	public List<Map<String, Object>> commonQuery(Class<Type> clazz, String sql, Object... args)
			throws CustomException, Exception {
		return it.query(clazz, sql, args);
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) throws CustomException, Exception {
		return it.get(params);
	}
	
}
