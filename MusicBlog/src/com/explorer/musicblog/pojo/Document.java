package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * Nov 30, 2019 5:33:12 PM
 * 文章类
 */
public class Document {

	private Integer id;			// 文章主键ID
	private String title;		// 文章标题
	private String reference;	// 文章引用
	private String uid;			// 发表作者
	private String body;		// 文章内容
	private String publishTime;	// 文章内容
	private String createTime;  // 创建时间
	private String updateTime;  // 修改时间
	private String commend;		// 文章内容
	
	public Document() {}

	public Document(Integer id, String title, String reference, String uid, String body, String publishTime,
            String createTime, String updateTime, String commend) {
        super();
        this.id = id;
        this.title = title;
        this.reference = reference;
        this.uid = uid;
        this.body = body;
        this.publishTime = publishTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.commend = commend;
    }

    @Override
    public String toString() {
        return "Document [id=" + id + ", title=" + title + ", reference=" + reference + ", uid=" + uid + ", body="
                + body + ", publishTime=" + publishTime + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", commend=" + commend + "]";
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		if(publishTime != null && !publishTime.equals("")) {
			this.publishTime = publishTime;
		} else {
			this.publishTime = "now()";
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

	public String getCommend() {
		return commend;
	}

	public void setCommend(String commend) {
		this.commend = commend;
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
