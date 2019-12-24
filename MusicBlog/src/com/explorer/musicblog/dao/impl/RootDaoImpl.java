package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.explorer.musicblog.dao.IRootDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Root;
import com.explorer.musicblog.utils.DBUtil;

/**
 * zhangzhong 2018年5月28日下午11:36:40
 */
public class RootDaoImpl implements IRootDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public Root getRoot(String name, String pass) throws CustomException {
		if(name != null && pass != null && !"".equals(name.trim()) && !"".equals(pass.trim())) {
			String sql = "select * from `admin` where `name`=? and `pass`=?";
			DBUtil util = new DBUtil();
			try {
				conn = util.getConn();
			} catch (CustomException e) {
				throw new CustomException("获取数据库连接失败!"+e.getMessage());
			}
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, pass);
				System.out.println("获取管理员:"+pstmt);
				rs = pstmt.executeQuery();
				Root root = new Root();
				while (rs.next()) {
					root.setAid(rs.getInt("aid"));
					root.setName(rs.getString("name"));
					root.setPass(rs.getString("pass"));
				}
				return root;
			} catch (SQLException e) {
				throw new CustomException("执行SQL错误!"+e.getMessage());
			} finally {
				try {
					util.close(rs, pstmt, conn);
				} catch (CustomException e) {
					throw new CustomException("关闭数据库连接错误!"+e.getMessage());
				}
			}
		}
		return null;
	}
}
