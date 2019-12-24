package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * 2018年5月28日下午11:28:28
 * 管理员类
 */
public class Root {

	private Integer aid;	//管理员ID
	private String name;	//管理员名
	private String pass;	//密码

	public Root() {}
	
	public Root(Integer aid, String name,String pass) {
		this.aid 	 = aid;
		this.name = name;
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", name=" + name + ", pass=" + pass + "]";
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
}
