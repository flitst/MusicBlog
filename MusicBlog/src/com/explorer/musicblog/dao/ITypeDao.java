package com.explorer.musicblog.dao;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.exception.CustomException;

/**
 * zhangzhong
 * Dec 6, 2019 8:59:13 PM
 */
public interface ITypeDao {

	List<Map<String,Object>> getType() throws CustomException;
}
