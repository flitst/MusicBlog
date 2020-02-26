package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ILyricDao;
import com.explorer.musicblog.pojo.Lyric;
import com.explorer.musicblog.util.DBUtils;

/**
 * zhangzhong
 * Dec 11, 2019 6:19:29 PM
 */
public class LyricDaoImpl implements ILyricDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Lyric> getByName(String name) {
		String sql = "select * from `t_document` where title like %?% or body like %?%";
		List<Lyric> lyrics = new ArrayList<>();
		try {
			if (DBUtils.getDataBase() != null ) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, name);
				rs = ps.executeQuery();
				System.out.println(DBUtils.printSQL(ps,"根据歌词名获取歌词"));
				Lyric lyric = null;
				while (rs.next()) {
					lyric = new Lyric();
					lyric.setId(rs.getInt("id"));
					lyric.setSong(rs.getString("song"));
					lyric.setLyric(rs.getString("lyric"));
					lyric.setSinger(rs.getString("singer"));
					lyric.setContent(rs.getString("content"));
					lyric.setCreateTime(rs.getString("create_time"));
					lyric.setUpdateTime(rs.getString("update_time"));
					lyrics.add(lyric);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return lyrics;
	}

	
	@Override
	public Integer renew(String sql, Object... args){
		if (sql != null && !"".equals(sql.trim()) && args.length >= 0) {
			try {
				if (DBUtils.getDataBase() != null) {
					ps = conn.prepareStatement(sql);
					System.out.println(DBUtils.printSQL(ps,"通用增/删/改"));
					for (int i = 1; i <= args.length; i++) {
						ps.setObject(i, args[i-1]);
					}
				}
				return ps.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException("SQL执行失败!" + e.getMessage());
			} finally {
				DBUtils.close(rs, ps, conn);
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> query(Class<Lyric> clazz, String sql, Object... args) {
		return null;
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params){
		if(params != null && params.size() > 0) {
			StringBuffer sb = new StringBuffer("select * from `t_lyric` where 1=1");
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map = params.get(i);
				sb.append(" and "+map.get("param")+map.get("pattern")+map.get("value"));
			}
			System.out.println("sb:" + sb.toString());
			if(DBUtils.getDataBase() != null) {
				try {
					ps = conn.prepareStatement(sb.toString());
					System.out.println(DBUtils.printSQL(ps,"获取歌词"));
					ResultSet rs = ps.executeQuery();
					List<Map<String, Object>> lyrics = new ArrayList<Map<String, Object>>();
					HashMap<String, Object> lyric = new HashMap<String, Object>();
					Lyric l = new Lyric();
					while (rs.next()) {
						l.setId(rs.getInt("id"));
						l.setSong(rs.getString("song"));
						l.setLyric(rs.getString("lyric"));
						l.setSinger(rs.getString("singer"));
						l.setContent(rs.getString("content"));
						String str = l.getId().toString();
						System.out.println("key:" + str);
						lyric.put(str, l);
						lyrics.add(lyric);
						return lyrics;
					}
				} catch (SQLException e) {
					throw new RuntimeException("执行SQL失败!" + e.getMessage());
				} finally {
					DBUtils.close(rs, ps, conn);
				}
			}
		}
		return null;
	}

	@Override
	public List<Lyric> getAll() {
		return null;
	}

	@Override
	public Integer insert(Lyric type) {
		return null;
	}

	@Override
	public Integer update(Lyric type) {
		return null;
	}

	@Override
	public Integer delete(Lyric type) {
		return null;
	}


	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Lyric getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
