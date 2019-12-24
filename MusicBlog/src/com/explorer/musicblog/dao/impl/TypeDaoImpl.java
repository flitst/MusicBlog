package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ITypeDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Type;
import com.explorer.musicblog.utils.DBUtil;

/**
 * zhangzhong
 * Dec 6, 2019 8:59:36 PM
 */
public class TypeDaoImpl implements ITypeDao {

	@Override
	public List<Map<String,Object>> getType(){
		System.out.println("获取所有歌曲类型");
		DBUtil db = new DBUtil();
		Connection conn = null;
		String sql = "select * from `type`";
		try {
			conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs =  ps.executeQuery();
			Type type = null;
			List<Map<String,Object>> list = new ArrayList<>();
			while(rs.next()) {
				type = new Type();
				type.setId(rs.getInt("id"));
				type.setName(rs.getString("name"));
				Map<String,Object> map = new HashMap<>();
				map.put(type.getId().toString(), type.getName());
				list.add(map);
			}
			return list;
		} catch (CustomException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
