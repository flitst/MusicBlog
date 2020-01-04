package com.explorer.musicblog.pojo;
/**
 * zhangzhong
 * Dec 2, 2019 7:27:14 PM
    *     歌曲类型类
 */
public class Type {

	private Integer id;	       // 主键ID
	private String 	type;      // 歌曲类型
    private String  createTime;// 注册时间
    private String  updateTime;// 修改时间
	
	public Type() {}

    public Type(Integer id, String type, String createTime, String updateTime) {
        super();
        this.id = id;
        this.type = type;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Type [id=" + id + ", type=" + type + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
