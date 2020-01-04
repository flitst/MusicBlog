package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ITypeDAO;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Type;
import com.explorer.musicblog.utils.DBUtil;

/**
 * zhangzhong
 * Dec 6, 2019 8:59:36 PM
 */
public class TypeDaoImpl implements ITypeDAO {

	@Override
	public List<Type> getAll(){
		DBUtil db = new DBUtil();
		Connection conn = null;
		String sql = "select id ID,type 类型 from `t_type`";
		try {
			conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("获取所有歌曲类型:"+ps);
			ResultSet rs =  ps.executeQuery();
			Type type = null;
			List<Type> list = new ArrayList<Type>();
			while(rs.next()) {
				type = new Type();
				type.setId(rs.getInt("ID"));
				type.setType(rs.getString("类型"));
				list.add(type);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(Type type) {
		Connection conn =  null;
		PreparedStatement ps = null;
		String sql = "insert into `t_type`(`type`)values(?)";
		DBUtil db = new DBUtil();
		conn = db.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, type.getType());
			int num = ps.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
			new RuntimeException("SQL执行错误 :"+e.getMessage());
		} finally {
			try {
				db.close(null, ps, conn);
			} catch (CustomException e) {
				e.printStackTrace();
				new RuntimeException("关闭数据库连接错误 :"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Integer update(Type type) {
		Connection conn =  null;
		PreparedStatement ps = null;
		String sql = "update `t_type` set type=? where id=?";
		DBUtil db = new DBUtil();
		conn = db.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, type.getType());
			ps.setInt(2, type.getId());
			int num = ps.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
			new RuntimeException("SQL执行错误 :"+e.getMessage());
		} finally {
			try {
				db.close(null, ps, conn);
			} catch (CustomException e) {
				e.printStackTrace();
				new RuntimeException("关闭数据库连接错误 :"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Integer delete(Type type) {
		Connection conn =  null;
		PreparedStatement ps = null;
//		ResultSet rs = null;
		String sql = "delete from `t_type` where id=?";
		DBUtil db = new DBUtil();
		conn = db.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, type.getId());
			int num = ps.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
			new RuntimeException("SQL执行错误 :"+e.getMessage());
		} finally {
			try {
				db.close(null, ps, conn);
			} catch (CustomException e) {
				e.printStackTrace();
				new RuntimeException("关闭数据库连接错误 :"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Integer renew(String sql, Object... args) {
		return null;
	}

	@Override
	public List<Map<String, Object>> query(Class<Type> clazz, String sql, Object... args) throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) {
		return null;
	}

}
