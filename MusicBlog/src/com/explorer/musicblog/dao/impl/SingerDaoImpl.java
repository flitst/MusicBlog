package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISingerDAO;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Singer;
import com.explorer.musicblog.utils.DBUtil;

/**
 * @author :zhangzhong 创建时间 :2018年5月27日下午5:15:52
 */
public class SingerDaoImpl implements ISingerDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public Integer insert(Singer singer) {
		String sql = "insert into t_singer(name,sex,age,head,image,addTime,updateTime) values (?,?,?,?,?,?,?)";
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, singer.getName());
			pstmt.setObject(2, singer.getSex());
			pstmt.setInt(3, singer.getAge());
			pstmt.setString(4, singer.getHead());
			pstmt.setString(5, singer.getImage());
			pstmt.setString(6, singer.getCreateTime());
			pstmt.setString(7, singer.getUpdateTime());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		String sql = "delete from `t_singer` where id = ?";
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Integer update(Integer id, Singer singer) {
		String sql = "update `t_singer` set `name`=?,`sex`=?,`age`=?,`head`=?,`image`=?,`updateTime`=? where `id`=?";
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, singer.getName());
			pstmt.setObject(2, singer.getSex());
			pstmt.setInt(3, singer.getAge());
			pstmt.setString(4, singer.getHead());
			pstmt.setString(5, singer.getImage());
			pstmt.setString(6, singer.getUpdateTime());
			pstmt.setInt(7, singer.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Map<String,Singer>> getAllSinger() {
		String sql = "select * from `t_singer`";
		DBUtil db = new DBUtil();
		List<Map<String,Singer>> singers = null;
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt:"+pstmt);
			rs = pstmt.executeQuery();
			Singer singer = null;
			singers = new ArrayList<Map<String,Singer>>();
			while (rs.next()) {
				singer = new Singer();
				singer.setId(rs.getInt("id"));
				singer.setName(rs.getString("name"));
				singer.setSex(rs.getByte("sex"));
				singer.setAge(rs.getByte("age"));
				singer.setHead(rs.getString("head"));
				singer.setImage(rs.getString("image"));
				singer.setCreateTime(rs.getString("addTime"));
				singer.setUpdateTime(rs.getString("updateTime"));
				Map<String, Singer> map =  new HashMap<String, Singer>();
				map.put(singer.getId().toString(),singer);
				singers.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return singers;
	}

	@Override
	public List<Singer> getSinger(String name) {
		String sql = "select * from `t_singer` where `name` like '%"+name+"%'";
		Singer sin = null;
		DBUtil db = new DBUtil();
		List<Singer> singers = null;
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			singers = new ArrayList<Singer>();
			while (rs.next()) {
				sin = new Singer();
				sin.setId(rs.getInt("id"));
				sin.setName(rs.getString("name"));
				sin.setSex(rs.getByte("sex"));
				sin.setAge(rs.getByte("age"));
				sin.setHead(rs.getString("head"));
				sin.setImage(rs.getString("image"));
				sin.setCreateTime(rs.getString("addTime"));
				sin.setUpdateTime(rs.getString("updateTime"));
				singers.add(sin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return singers;
	}

	@Override
	public Singer getSingerById(Integer id) {
		Singer sin = null;
		String sql = "select * from t_singer where id = ?";
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sin = new Singer();
				sin.setName(rs.getString("name"));
				sin.setSex(rs.getByte("sex"));
				sin.setAge(rs.getByte("age"));
				sin.setHead(rs.getString("head"));
				sin.setImage(rs.getString("image"));
				sin.setCreateTime(rs.getString("addTime"));
				sin.setUpdateTime(rs.getString("updateTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return sin;
	}
}
