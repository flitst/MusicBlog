package com.explorer.musicblog.service;

import java.util.List;

import com.explorer.musicblog.dao.ICommonDao;
import com.explorer.musicblog.pojo.Song;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午11:05:17
 */
public interface ISongService extends ICommonDao<Song, String, Object>{

	
	/**
	 * 根据歌曲ID或名称查询
	 * @param id
	 * @return
	 */
	public Song getSongById(int id, String name);

	/**
	 * 根据歌曲名查询歌曲
	 * @param num  用于获取分页当前页数
	 * @param name 查询的歌曲名
	 * @return
	 */
	public List<Song> getByName(Integer num,String name);
}
