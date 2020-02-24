package com.explorer.musicblog.pojo;

/**
 * @author 	zhangzhong
    *   创建时间:	2018年5月24日下午4:05:01
    *   歌词类
 */
public class Lyric {
	private Integer id;		   // 歌词ID
	private String song;	   // 作曲人
	private String lyric;	   // 作词人
	private String singer;	   // 演唱者
	private String content;	   // 歌词内容
	private String createTime; // 创建时间
    private String updateTime; // 修改时间
	
	public Lyric() {}

	public Lyric(Integer id, String song, String lyric, String singer, String content, String createTime, String updateTime) {
		super();
		this.id = id;
		this.song = song;
		this.lyric = lyric;
		this.singer = singer;
		this.content = content;
		this.createTime = createTime;
        this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Lyric [id=" + id + ", song=" + song + ", lyric=" + lyric + ", singer=" + singer + ", content=" + content
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
