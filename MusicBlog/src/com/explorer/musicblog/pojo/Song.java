package com.explorer.musicblog.pojo;

/**
 * @author 	zhangzhong
    *      创建时间:	2018年5月24日下午4:05:26
    *      歌曲类
 */
public class Song {
	private Integer id;		     // 歌曲ID
	private String 	name;	     // 歌曲名
	private String	singer;	     // 歌手
	private String 	lyric;	     // 歌词
	private Integer type;	     // 类型
    private String  createTime;  // 注册时间
    private String  updateTime;  // 修改时间
	
	public Song() {}

	public Song(Integer id, String name, String singer, String lyric, Integer type, String createTime, String updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.singer = singer;
		this.lyric = lyric;
		this.type = type;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
    public String toString() {
        return "Song [id=" + id + ", name=" + name + ", singer=" + singer + ", lyric=" + lyric + ", type=" + type
                + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
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

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
