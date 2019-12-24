package com.explorer.musicblog.dao;

import java.util.List;

import com.explorer.musicblog.pojo.Song;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午10:57:25
 */
public interface ISongDao extends ICommonDao<Song,String,Object>{

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
	public Integer delete(Integer id, String name);

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
	public Song getSongById(Integer id, String name);
}
