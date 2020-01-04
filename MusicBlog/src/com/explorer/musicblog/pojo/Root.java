package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * 2018年5月28日下午11:28:28
 * 管理员类
 */
public class Root {

	private Integer aid;	   // 管理员ID
	private String name;	   // 账号
	private String pass;   	   // 密码
	private String createTime; // 创建时间
	private String updateTime; // 修改时间
	
	public Root() {}

    public Root(Integer aid, String name, String pass, String createTime, String updateTime) {
        super();
        this.aid = aid;
        this.name = name;
        this.pass = pass;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Root [aid=" + aid + ", name=" + name + ", pass=" + pass + ", createTime=" + createTime + ", updateTime="
                + updateTime + "]";
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
