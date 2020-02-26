package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISingerDao;
import com.explorer.musicblog.pojo.Singer;
import com.explorer.musicblog.util.DBUtils;

/**
 * @author :zhangzhong 创建时间 :2018年5月27日下午5:15:52
 */
public class SingerDaoImpl implements ISingerDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBUtils db = new DBUtils();

	@Override
	public Integer insert(Singer singer) {
		String sql = "insert into `singer`(`name`,`sex`,`age`,`head`,`image`,`create_time`,`update_time`) values (?,?,?,?,?,?,?)";
		try {
			if (DBUtils.getDataBase() != null) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, singer.getName());
				ps.setObject(2, singer.getSex());
				ps.setInt(3, singer.getAge());
				ps.setString(4, singer.getHead());
				ps.setString(5, singer.getImage());
				ps.setString(6, singer.getCreateTime());
				ps.setString(7, singer.getUpdateTime());
				System.out.println(DBUtils.printSQL(ps,"新增歌手"));
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		String sql = "delete from `singer` where `id` = ?";
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println(DBUtils.printSQL(ps,"删除歌手"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return null;
	}

	@Override
	public Integer update(Integer id, Singer singer) {
		String sql = "update `singer` set `name`=?,`sex`=?,`age`=?,`head`=?,`image`=?,`update_time`=? where `id`=?";
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, singer.getName());
			ps.setObject(2, singer.getSex());
			ps.setInt(3, singer.getAge());
			ps.setString(4, singer.getHead());
			ps.setString(5, singer.getImage());
			ps.setString(6, singer.getUpdateTime());
			ps.setInt(7, singer.getId());
			System.out.println(DBUtils.printSQL(ps,"修改歌手"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return null;
	}

	@Override
	public List<Map<String,Singer>> getAllSinger() {
		String sql = "select * from `singer`";
		List<Map<String,Singer>> singers = null;
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			System.out.println(DBUtils.printSQL(ps,"获取所有歌手"));
			rs = ps.executeQuery();
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
				singer.setCreateTime(rs.getString("create_time"));
				singer.setUpdateTime(rs.getString("update_time"));
				Map<String, Singer> map =  new HashMap<String, Singer>();
				map.put(singer.getId().toString(),singer);
				singers.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return singers;
	}

	@Override
	public List<Singer> getByName(String name) {
		String sql = "select * from `singer` where `name` like ?";
		Singer sin = null;
		List<Singer> singers = null;
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			System.out.println(DBUtils.printSQL(ps,"根据歌手名获取歌手"));
			rs = ps.executeQuery();
			singers = new ArrayList<Singer>();
			while (rs.next()) {
				sin = new Singer();
				sin.setId(rs.getInt("id"));
				sin.setName(rs.getString("name"));
				sin.setSex(rs.getByte("sex"));
				sin.setAge(rs.getByte("age"));
				sin.setHead(rs.getString("head"));
				sin.setImage(rs.getString("image"));
				sin.setCreateTime(rs.getString("create_time"));
				sin.setUpdateTime(rs.getString("update_time"));
				singers.add(sin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return singers;
	}

	@Override
	public Singer getById(Integer id) {
		Singer sin = null;
		String sql = "select * from `singer` where `id` = ?";
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println(DBUtils.printSQL(ps,"根据歌手ID获取歌手"));
			rs = ps.executeQuery();
			while (rs.next()) {
				sin = new Singer();
				sin.setName(rs.getString("name"));
				sin.setSex(rs.getByte("sex"));
				sin.setAge(rs.getByte("age"));
				sin.setHead(rs.getString("head"));
				sin.setImage(rs.getString("image"));
				sin.setCreateTime(rs.getString("create_time"));
				sin.setUpdateTime(rs.getString("update_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return sin;
	}

	@Override
	public Integer update(Singer type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Singer type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer renew(String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Singer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSize() {
		String sql = "select count(*) from `singer`";
		conn = db.getConnection();
		Integer num = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, ps, conn);
		}
		return num;
	}

	@Override
	public List<Map<String, Object>> query(Class<Singer> clazz, String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) {
		// TODO Auto-generated method stub
		return null;
	}
}
