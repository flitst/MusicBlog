package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISongDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Song;
import com.explorer.musicblog.utils.DBUtil;

/**
 * zhangzhong Jul 2, 201810:29:00 PM
 */
public class SongDaoImpl implements ISongDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public Integer insert(Song song) {
		String sql = "insert into `tb_song`(`name`,`singer`,`lyric`,`type`,`length`) values (?,?,?,?,?)";
		Integer i = null;
		DBUtil db = new DBUtil();
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, song.getName());
			pstmt.setObject(2, song.getSinger());
			pstmt.setObject(3, song.getLyric());
			pstmt.setObject(4, song.getType());
			pstmt.setString(5, song.getLength());
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
		String sql = "delete from `tb_song` where `id`=?";
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
	public Integer update(Song song) {
		String sql = "update `tb_song` set `name`=?,`singer`=?,`lyric`=?,`type`=?,`length`=?";
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
			pstmt.setString(5, song.getLength());
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
		String sql = "select id ID,name 歌名,singer 歌手,lyric 歌词,type 类型,length 时长 from tb_song limit 0,3";
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
				song.setLength(rs.getString("时长"));
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
		String sql = "select id ID,name 歌名,singer 歌手,lyric 歌词,type 类型,length 时长 from tb_song";
		if(num != null && !"".equals(num)) {
			sql = "select id ID,name 歌名,singer 歌手,lyric 歌词,type 类型,length 时长 from tb_song where name like ?";
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
				song.setLength(rs.getString("时长"));
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
		String sql = "select id ID,name 名字,singer 歌手,lyric 歌词,type 类型,length 长度 from tb_song";
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
				song.setLength(rs.getString("时长"));
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
