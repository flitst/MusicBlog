package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.explorer.musicblog.dao.IRootDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Root;
import com.explorer.musicblog.util.DBUtils;

/**
 * zhangzhong 2018年5月28日下午11:36:40
 */
public class RootDaoImpl implements IRootDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public Root getRoot(String name, String pass) throws CustomException {
		if(name != null && pass != null && !"".equals(name.trim()) && !"".equals(pass.trim())) {
			String sql = "select * from `root` where `name`=? and `pass`=?";
			DBUtils db = new DBUtils();
			Root root = null;
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				throw new CustomException("获取数据库连接失败!"+e.getMessage());
			}
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, pass);
				System.out.println(db.printSQL(ps,"获取管理员"));
				rs = ps.executeQuery();
				while (rs.next()) {
				    root = new Root();
					root.setAid(rs.getInt("id"));
					root.setName(rs.getString("name"));
					root.setPass(rs.getString("pass"));
					root.setCreateTime(rs.getString("create_time"));
					root.setUpdateTime(rs.getString("update_time"));
				}
				return root;
			} catch (SQLException e) {
				throw new CustomException("执行SQL错误!"+e.getMessage());
			} finally {
			    root = null;
				try {
					db.close(rs, ps, conn);
				} catch (CustomException e) {
					throw new CustomException("关闭数据库连接错误!"+e.getMessage());
				}
			}
		}
		return null;
	}

	@Override
	public Integer contactTheWebmaster(String value) {
		//String sql = "";
		return null;
	}
}
