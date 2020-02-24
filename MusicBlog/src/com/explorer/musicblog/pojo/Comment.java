package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * Dec 2, 2019 7:58:08 PM
 *   评论类
 */
public class Comment {
	
	private Integer id;	    	// 评论主键ID
	private User	user;		// 评论者
	private String content;		// 评论内容
	private String at;			// 评论回复
	private String createTime;  // 创建时间
    private String updateTime;  // 修改时间
	
	public Comment() {}

    public Comment(Integer id, User user, String content, String at, String createTime, String updateTime) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
		this.at = at;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", user=" + user + ", content=" + content + ", at=" + at + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
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
