package com.explorer.musicblog.service;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.exception.CustomException;

/**
 * zhangzhong
 * Dec 6, 2019 8:51:49 PM
 */
public interface ITypeService {
	
	List<Map<String,Object>> getType() throws CustomException;

}
