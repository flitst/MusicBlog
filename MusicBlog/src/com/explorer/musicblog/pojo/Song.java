package com.explorer.musicblog.pojo;

/**
 * @author 	zhangzhong
 * 创建时间:	2018年5月24日下午4:05:26
 * 歌曲类
 */
public class Song {
	private Integer id;		//歌曲ID
	private String 	name;	//歌曲名
	private Singer 	singer;	//歌手
	private Lyric 	lyric;	//歌词
	private Type 	type;	//类型
	private String 	length;	//时长
	
	public Song() {}

	public Song(Integer id, String name, Singer singer, Lyric lyric, Type type, String length) {
		super();
		this.id = id;
		this.name = name;
		this.singer = singer;
		this.lyric = lyric;
		this.type = type;
		this.length = length;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", singer=" + singer + ", lyric=" + lyric + ", type=" + type
				+ ", length=" + length + "]";
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

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public Lyric getLyric() {
		return lyric;
	}

	public void setLyric(Lyric lyric) {
		this.lyric = lyric;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
}
