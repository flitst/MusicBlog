package com.explorer.musicblog.dao;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.pojo.Singer;

/**
 * zhangzhong
 * 2018年5月28日下午10:18:46
 */
public interface ISingerDao {

	/**
	 * 新增歌手
	 * @param singer
	 */
	public void insert(Singer singer);

	/**
	 * 根据编号删除歌手
	 * @param id
	 * @param name
	 */
	public void delete(Integer id);

	/**
	 * 修改歌手信息
	 * @param singer
	 */
	public void update(Integer id, Singer singer);

	/**
	 * 获取所有歌手
	 * @return
	 */
	public List<Map<String,Singer>> getAllSinger();

	/**
	 * 根据歌手名查询
	 * @param name
	 * @return
	 */
	public Singer getSinger(String name);

	/**
	 * 根据歌手编号查询
	 * @param id
	 * @return
	 */
	public Singer getSingerById(Integer id);

}
