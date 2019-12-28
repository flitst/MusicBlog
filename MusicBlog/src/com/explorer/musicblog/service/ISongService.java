package com.explorer.musicblog.service;

import java.util.List;

import com.explorer.musicblog.pojo.Song;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午11:05:17
 */
public interface ISongService {

	/**
	 * 添加歌曲
	 * @param song
	 */
	public Integer insert(Song song);
	
	/**
	 * 根据歌曲编号或歌曲名称删除
	 * @param id
	 * @param name
	 */
	public Integer delete(int id, String name);

	/**
	 * 修改歌曲信息
	 * @param song
	 */
	public Integer update(Song song);
	
	/**
	 * 获取所有歌曲
	 * @return
	 */
	public List<Song> getAll();
	
	/**
	 * 根据歌曲ID或名称查询
	 * @param id
	 * @return
	 */
	public Song getSongById(int id, String name);

	/**
	 *根据歌曲名查询歌曲
	 * @param num  用于获取分页当前页数
	 * @param name 查询的歌曲名
	 * @return
	 */
	public List<Song> getByName(Integer num,String name);
}
