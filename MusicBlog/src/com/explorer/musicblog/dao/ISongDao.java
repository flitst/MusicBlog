package com.explorer.musicblog.dao;

import java.util.List;

import com.explorer.musicblog.pojo.Song;

/**
 * @author	:zhangzhong
 * 创建时间	:2018年5月27日下午10:57:25
 */
public interface ISongDao extends ICommonDao<Song,String,Object>{

	public List<Song> getByName(Integer num,String name);

	public Song getSongById(int id, String name);
}
