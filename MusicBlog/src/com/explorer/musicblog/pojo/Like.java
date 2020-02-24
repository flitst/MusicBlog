package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * Dec 3, 2019 9:51:08 PM
 * 点赞类
 */
public class Like {

	private Integer id;	       // 点赞ID
	private User user;		   // 点赞者
	private Boolean status;	   // 点赞状态（1 成功 0 失败
	private String createTime; // 创建时间
    private String updateTime; // 修改时间
	
	public Like() {}

    public Like(Integer id, User user, Boolean status, String createTime, String updateTime) {
		super();
		this.id = id;
		this.user = user;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", user=" + user + ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		if(status != null) {
			this.status = status;
		} else {
			this.status = false;
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
