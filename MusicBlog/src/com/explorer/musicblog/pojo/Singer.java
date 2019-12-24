package com.explorer.musicblog.pojo;

/**
 * @author 	zhangzhong
 * 创建时间:	2018年5月24日下午4:05:18
 * 歌手类
 */
public class Singer {
	private Integer id;			//歌手ID
	private String 	name;		//歌手
	private Byte 	sex;		//性别
	private Byte 	age;		//年龄
	private String 	head;		//头像
	private String 	image;		//图片
	private String 	addTime;	//添加时间
	private String 	updateTime;	//修改时间
	
	public Singer() {}

	public Singer(Integer id, String name, Byte sex, Byte age, String head, String image, String addTime,
			String updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.head = head;
		this.image = image;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Singer [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", head=" + head + ", image="
				+ image + ", addTime=" + addTime + ", updateTime=" + updateTime + "]";
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

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
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

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
