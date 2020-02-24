package com.explorer.musicblog.dao;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.pojo.Singer;

/**
 * zhangzhong
 * 2018年5月28日下午10:18:46
 */
public interface ISingerDao extends ICommonDao<Singer, String, Object>{


	/**
	 * 修改歌手信息
	 * @param singer
	 */
	public Integer update(Integer id, Singer singer);

	/**
	 * 获取所有歌手
	 * @return
	 */
	public List<Map<String,Singer>> getAllSinger();


}
