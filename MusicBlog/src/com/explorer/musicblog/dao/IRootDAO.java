package com.explorer.musicblog.dao;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Root;

/**
 * zhangzhong
 * 2018年5月28日下午11:27:25
 */
public interface IRootDAO {
	
	/**
	 * 根据账号和密码查询管理员
	 * @param id
	 * @param name
	 * @return
	 * @throws CustomException 
	 */
	public Root getRoot(String name,String pass) throws CustomException;
}
