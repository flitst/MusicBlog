package com.explorer.musicblog.service;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ICommonDao;
import com.explorer.musicblog.pojo.Singer;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午5:30:58
 */
public interface ISingerService extends ICommonDao<Singer, String, Object> {

	/**
	 * 查询所有歌手
	 * @return
	 */
	public List<Map<String,Singer>> getAllSinger();
	
	
}
