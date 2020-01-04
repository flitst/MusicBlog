package com.explorer.musicblog.pojo;

import java.util.Arrays;
import java.util.Date;

/**
 * zhangzhong 2018年5月28日下午11:41:17
 * 用户类
 */
public class User {

	private Integer	 id; 		 	// 唯一ID
	private String   account; 		// 用户账号
	private String   pwd; 	 		// 用户密码
	private String   nickname; 		// 用户昵称
	private String   signature; 	// 个性签名
	private Byte  	 age; 		 	// 用户年龄
	private Byte  	 sex; 			// 用户性别(0:保密,1:男,2:女')
	private String[] hobby; 		// 业余爱好
	private String   head; 		 	// 用户头像
	private String   image; 		// 用户照片
	private String   email; 		// 用户邮箱
	private String 	 mobile; 		// 用户邮箱
	private String   createTime;    // 注册时间
	private String   updateTime;  	// 修改时间

	public User() {}
	
	public User(Integer id, String account, String pwd,String nickname, String signature, Byte age, Byte sex, 
			String[] hobby, String head, String image, String email, String mobile, String createTime,String updateTime) {
		super();
		this.id = id;
		this.account = account;
		this.pwd = pwd;
		this.nickname = nickname;
		this.signature = signature;
		this.age = age;
		this.sex = sex;
		this.hobby = hobby;
		this.head = head;
		this.image = image;
		this.email = email;
		this.mobile = mobile;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", pwd=" + pwd + ", nickname=" + nickname + ", signature=" + signature + ", age="
				+ age + ", sex=" + sex + ", hobby=" + Arrays.toString(hobby) + ", head="
				+ head + ", image=" + image + ", email=" + email + ", mobile=" + mobile + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		if (age != null && age > 0 && age < 130) {
			this.age = age;
		} else {
			this.age = 18;
		}
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		if (sex != null && sex == 0 || sex == 1 || sex == 2) {
			this.sex = sex;
		} else {
			this.sex = 0;
		}
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		if(null != createTime) {
			this.createTime = createTime;
		} else {
			this.createTime = new Date().toString();
		}
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		if(updateTime != null) {
			this.updateTime = updateTime;
		} else {
			this.updateTime = new Date().toString();
		}
	}
	
}
