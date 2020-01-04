package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISongDAO;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Song;
import com.explorer.musicblog.utils.DBUtil;

/**
 * zhangzhong Jul 2, 201810:29:00 PM
 */
public class SongDaoImpl implements ISongDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public Integer insert(Song song) {
		String sql = "insert into `t_song`(`name`,`singer`,`lyric`,`type`,`create_time`,`update_time`) values (?,?,?,?,now(),now())";
		Integer i = null;
		DBUtil db = new DBUtil();
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, song.getName());
			pstmt.setObject(2, song.getSinger());
			pstmt.setObject(3, song.getLyric());
			pstmt.setObject(4, song.getType());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	@Override
	public Integer delete(Integer id, String name) {
		String sql = "delete from `t_song` where `id`=?";
		Integer i = null;
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	@Override
	public Integer delete(Song type) {
		return null;
	}
	@Override
	public Integer update(Song song) {
		String sql = "update `t_song` set `name`=?,`singer`=?,`lyric`=?,`type`=? `update_time`=now()";
		Integer i = null;
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getName());
			pstmt.setObject(2, song.getSinger());
			pstmt.setObject(3, song.getLyric());
			pstmt.setObject(4, song.getType());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	@Override
	public List<Song> getAll() {
		//"select * from `tb_song`"
		String sql = "select id ID, name 歌名, singer 歌手, lyric 歌词, type 类型,  create_time 新增时间, update_time 修改时间 from t_song limit 0,3";
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Song> songs = new LinkedList<Song>();
			Song song = null;
			while (rs.next()) {
				song = new Song();
				song.setId(rs.getInt("ID"));
				song.setName(rs.getString("歌名"));
				song.setSinger(rs.getString("歌手"));
				song.setLyric(rs.getString("歌词"));
				song.setType(rs.getInt("类型"));
				song.setCreateTime(rs.getString("新增时间"));
				song.setUpdateTime(rs.getString("修改时间"));
				songs.add(song);
			}
			return songs;
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
	public List<Song> getByName(Integer num,String name) {
		String sql = "select id ID,name 歌名,singer 歌手,lyric 歌词,type 类型,  create_time 新增时间, update_time 修改时间 from t_song";
		if(num != null && num >= 0) {
			sql = "select id ID,name 歌名,singer 歌手,lyric 歌词,type 类型,  create_time 新增时间, update_time 修改时间 from t_song where name like ?";
		}
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			System.out.println("pstmt:"+pstmt);
			rs = pstmt.executeQuery();
			System.out.println("rs:"+rs);
			List<Song> songs = new LinkedList<Song>();
			Song song = null;
			while (rs.next()) {
				song = new Song();
				song.setId(rs.getInt("ID"));
				song.setName(rs.getString("歌名"));
				song.setSinger(rs.getString("歌手"));
				song.setLyric(rs.getString("歌词"));
				song.setType(rs.getInt("类型"));
				song.setCreateTime(rs.getString("新增时间"));
				song.setUpdateTime(rs.getString("修改时间"));
				songs.add(song);
			}
			return songs;
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
	public Song getSongById(Integer id, String name) {
		//select * from `tb_song` where `id`=?
		String sql = "select id ID,name 名字,singer 歌手,lyric 歌词,type 类型,  create_time 新增时间, update_time 修改时间 from t_song";
		Song song = new Song();
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
				song.setId(rs.getInt("ID"));
				song.setName(rs.getString("歌名"));
				song.setSinger(rs.getString("歌手"));
				song.setLyric(rs.getString("歌词"));
				song.setType(rs.getInt("类型"));
				song.setCreateTime(rs.getString("新增时间"));
                song.setUpdateTime(rs.getString("修改时间"));
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
		return song;
	}

	@Override
	public Integer renew(String sql, Object... args) {
		return null;
	}

	@Override
	public List<Map<String, Object>> query(Class<Song> clazz, String sql, Object... args) throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) {
		return null;
	}
}
