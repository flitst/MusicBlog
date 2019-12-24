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
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Lyric;
import com.explorer.musicblog.utils.DBUtil;

/**
 * zhangzhong
 * Dec 11, 2019 6:19:29 PM
 */
public class LyricDaoImpl implements ILyricDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	@Override
	public Integer renew(String sql, Object... args){
		if (sql != null && !"".equals(sql.trim()) && args.length >= 0) {
			DBUtil db = new DBUtil();
			try {
				conn = db.getConn();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			try {
				ps = conn.prepareStatement(sql);
				System.out.println("sql:"+ps);
				for (int i = 1; i <= args.length; i++) {
					ps.setObject(i, args[i-1]);
				}
				return ps.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException("SQL执行失败!" + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> query(Class<Lyric> clazz, String sql, Object... args) throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params){
		if(params != null && params.size() > 0) {
			StringBuffer sb = new StringBuffer("select * from `tb_lyric` where 1=1");
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map = params.get(i);
				sb.append(" and "+map.get("param")+map.get("pattern")+map.get("value"));
			}
			System.out.println("sb:" + sb.toString());
			DBUtil db = new DBUtil();
			try {
				conn = db.getConn();
			} catch (CustomException e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if(conn != null) {
				try {
					ps = conn.prepareStatement(sb.toString());
					System.out.println("sql:" + ps);
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
					try {
						db.close(rs, ps, conn);
					} catch (CustomException e) {
						throw new RuntimeException("关闭数据库连接失败!" + e.getMessage());
					}
				}
			}
		}
		return null;
	}

}
