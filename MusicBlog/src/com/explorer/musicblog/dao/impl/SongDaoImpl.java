package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISongDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Lyric;
import com.explorer.musicblog.pojo.Singer;
import com.explorer.musicblog.pojo.Song;
import com.explorer.musicblog.pojo.Type;
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
		} catch (CustomException e) {
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
			} catch (CustomException e) {
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
			} catch (CustomException e) {
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
		String sql = "select * from `tb_song`";
		List<Song> songs = new ArrayList<>();
		Song song = new Song();
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (CustomException e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				song.setName(rs.getString("name"));
				Object singer = rs.getObject("singer");
				if(singer instanceof Type) {
					song.setSinger((Singer)singer);
				}
				Object lyric = rs.getObject("lyric");
				if(lyric instanceof Lyric) {
					song.setLyric((Lyric)lyric);
				}
				Object type = rs.getObject("type");
				if(type instanceof Type) {
					song.setType((Type)type);
				}
				song.setLength(rs.getString("length"));
				songs.add(song);
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
		return songs;
	}

	@Override
	public Song getSongById(Integer id, String name) {
		String sql = "select * from `tb_song` where `id`=?";
		Song song = new Song();
		DBUtil db = new DBUtil();
		try {
			try {
				conn = db.getConn();
			} catch (CustomException e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				song.setName(rs.getString("name"));
				Object singer = rs.getObject("singer");
				if(singer instanceof Type) {
					song.setSinger((Singer)singer);
				}
				Object lyric = rs.getObject("lyric");
				if(lyric instanceof Lyric) {
					song.setLyric((Lyric)lyric);
				}
				Object type = rs.getObject("type");
				if(type instanceof Type) {
					song.setType((Type)type);
				}
				song.setLength(rs.getString("length"));
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
