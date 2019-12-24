package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * Dec 2, 2019 7:27:14 PM
 * 歌曲类型类
 */
public class Type {

	private Integer id;	 //类型主键ID
	private String 	name;//歌曲类型
	
	public Type() {}

	public Type(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "SongType [id=" + id + ", name=" + name + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
