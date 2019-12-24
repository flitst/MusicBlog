package com.explorer.musicblog.service;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.pojo.Singer;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午5:30:58
 */
public interface ISingerService {

	/**
	 * 添加歌手
	 * @param singer
	 */
	public void insert(Singer singer);
	
	/**
	 * 根据编号或歌手名删除歌手
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 修改歌手
	 * @param singer
	 */
	public void update(Integer id,Singer singer);
	
	/**
	 * 查询所有歌手
	 * @return
	 */
	public List<Map<String,Singer>> getAllSinger();
	
	/**
	 * 根据歌手名查询
	 * @param singer
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
