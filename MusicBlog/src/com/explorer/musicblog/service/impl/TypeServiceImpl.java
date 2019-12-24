package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ITypeDao;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.service.ITypeService;

/**
 * zhangzhong
 * Dec 6, 2019 8:52:20 PM
 */
public class TypeServiceImpl implements ITypeService {

	private ITypeDao it = DaoFactory.getTypeDao();
	
	@Override
	public List<Map<String,Object>> getType() throws CustomException {
		return it.getType();
	}

}
