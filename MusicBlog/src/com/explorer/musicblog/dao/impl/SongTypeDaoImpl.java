package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISongTypeDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.SongType;
import com.explorer.musicblog.util.DBUtils;

/**
 * zhangzhong
 * Dec 6, 2019 8:59:36 PM
 */
public class SongTypeDaoImpl implements ISongTypeDao {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBUtils db = new DBUtils();

	@Override
	public Integer getSize() throws Exception {
		String sql = "select count(*) from `song_type`";
		conn = db.getConnection();
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		Integer num = null;
		while (rs.next()) {
			num = rs.getInt(1);
		}
		return num;
	}
	
	@Override
	public List<SongType> getAll(){
		String sql = "select `id` ID,`name` 类型 , `create_time` 创建时间 ,`update_time` 修改时间 from `song_type`";
		try {
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(db.printSQL(ps,"获取所有歌曲类型")+ps);
			ResultSet rs =  ps.executeQuery();
			SongType type = null;
			List<SongType> list = new ArrayList<SongType>();
			while(rs.next()) {
				type = new SongType();
				type.setId(rs.getInt("ID"));
				type.setType(rs.getString("类型"));
				type.setCreateTime(rs.getString("创建时间"));
				type.setUpdateTime(rs.getString("修改时间"));
				list.add(type);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(SongType type) {
		String sql = "insert into `song_type`(`name`,`create_time`,`update_time`)values(?,NOW(),NOW())";
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, type.getType());
			System.out.println(db.printSQL(ps,"新增歌曲类型"));
			return ps.executeUpdate();
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
	public Integer update(SongType type) {
		String sql = "update `song_type` set name=? where id=?";
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, type.getType());
			ps.setInt(2, type.getId());
			System.out.println(db.printSQL(ps,"修改歌曲类型"));
			return ps.executeUpdate();
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
	public Integer delete(SongType type) {
		String sql = "delete from `song_type` where id=?";
		DBUtils db = new DBUtils();
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, type.getId());
			System.out.println(db.printSQL(ps,"删除歌曲类型"));
			return ps.executeUpdate();
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
	public List<Map<String, Object>> query(Class<SongType> clazz, String sql, Object... args) throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) {
		return null;
	}

	@Override
	public List<SongType> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SongType getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
