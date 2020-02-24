package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * Nov 30, 2019 5:33:12 PM
 * 文章类
 */
public class Article {

	private Integer id;			// 文章主键ID
	private String title;		// 文章标题
	private String reference;	// 文章引用
	private String description;	// 文章描述
	private Integer uid;		// 发表作者
	private String body;		// 文章内容
	private int views;			// 浏览量
	private int commentCount;	// 评论总数
	private int like;			// 点赞数量
	private String createTime;  // 创建时间
	private String updateTime;  // 修改时间
	
	public Article() {}

	public Article(Integer id, String title, String reference,String description, Integer uid, String body,
			String createTime, String updateTime, int views, int commentCount, int like) {
		super();
		this.id = id;
		this.title = title;
		this.reference = reference;
		this.description = description;
		this.uid = uid;
		this.body = body;
		this.views = views;
		this.commentCount = commentCount;
		this.like = like;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", reference=" + reference + ", description="+description+", uid=" + uid + ", body="
				+ body + ", views="+views+", commentCount="+commentCount+", like="+like+", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		if (views <= 0) {
			views = 0;
		} else {
			this.views = views;
		}
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		if (commentCount <= 0) {
			commentCount = 0;
		} else {
			this.commentCount = commentCount;
		}
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		if (like <= 00) {
			like = 0;
		} else {
			this.like = like;
		}
	}

	public String getModifyTime() {
		return updateTime;
	}

	public void setModifyTime(String updateTime) {
		if(updateTime != null && !updateTime.equals("")) {
			this.updateTime = updateTime;
		} else {
			this.updateTime = "now()";
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
