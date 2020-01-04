package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * Dec 2, 2019 7:58:08 PM
    *   评论类
 */
public class Comment {
	
	private Integer 	id;	    // 评论ID
	private User 		user;	// 评论人
	private String 		time;	// 评论时间
	private String 		content;// 评论内容
	private String 		at;		// 评论回复
	private Character 	del;	// 删除评论	
	private String createTime;  // 创建时间
    private String updateTime;  // 修改时间
	
	public Comment() {}

	public Comment(Integer id, User user, String time, String content, String at, Character del, String createTime,
            String updateTime) {
        super();
        this.id = id;
        this.user = user;
        this.time = time;
        this.content = content;
        this.at = at;
        this.del = del;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", user=" + user + ", time=" + time + ", content=" + content + ", at=" + at
                + ", del=" + del + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public Character getDel() {
        return del;
    }

    public void setDel(Character del) {
        this.del = del;
    }

    public void setIsdel(Character isdel) {
		if(isdel != null && isdel.charValue() == 'Y' || isdel.charValue() == 'N') {
			this.del = isdel;
		} else {
			this.del = 'N';
		}
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
