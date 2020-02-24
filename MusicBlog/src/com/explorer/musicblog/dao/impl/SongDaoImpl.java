package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.ISongDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Song;
import com.explorer.musicblog.util.DBUtils;

/**
 * zhangzhong Jul 2, 201810:29:00 PM
 */
public class SongDaoImpl implements ISongDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public Integer insert(Song song) {
		String sql = "insert into `song`(`name`,`singer`,`lyric`,`type`,`create_time`,`update_time`) values (?,?,?,?,now(),now())";
		DBUtils db = new DBUtils();
		Integer i = null;
		try {
			ps = db.getConnection().prepareStatement(sql);
			ps.setString(1, song.getName());
			ps.setObject(2, song.getSinger());
			ps.setObject(3, song.getLyric());
			ps.setObject(4, song.getType());
			System.out.println(db.printSQL(ps,"新增歌曲"));
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, ps, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	@Override
	public Integer delete(Integer id) {
		String sql = "delete from `song` where `id`=?";
		Integer i = null;
		DBUtils db = new DBUtils();
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println(db.printSQL(ps,"删除歌曲"));
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, ps, conn);
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
		String sql = "update `song` set `name`=?,`singer`=?,`lyric`=?,`type`=? `update_time`=now()";
		Integer i = null;
		DBUtils db = new DBUtils();
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, song.getName());
			ps.setObject(2, song.getSinger());
			ps.setObject(3, song.getLyric());
			ps.setObject(4, song.getType());
			System.out.println(db.printSQL(ps,"修改歌曲"));
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, ps, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	@Override
	public List<Song> getAll() {
		//"select * from `tb_song`"
		String sql = "select `id` ID, `name` 歌名, `singer` 歌手, `lyric` 歌词, `type` 类型,  `create_time` 新增时间, `update_time` 修改时间 from `song` limit 0,3";
		DBUtils db = new DBUtils();
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			System.out.println(db.printSQL(ps,"获取所有歌曲"));
			rs = ps.executeQuery();
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
				db.close(rs, ps, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public List<Song> getByName(Integer num,String name) {
		String sql = "select `id` ID,`name` 歌名,`singer` 歌手,`lyric` 歌词,`type` 类型, `create_time` 新增时间, `update_time` 修改时间 from `song`";
		if(num != null && num >= 0) {
			sql = "select `id` ID,`name` 歌名,`singer` 歌手,`lyric` 歌词,`type` 类型,  `create_time` 新增时间, `update_time` 修改时间 from `song` where `name` like ?";
		}
		DBUtils db = new DBUtils();
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			System.out.println(db.printSQL(ps,"根据歌曲名获取"));
			rs = ps.executeQuery();
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
				db.close(rs, ps, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public Song getById(Integer id) {
		String sql = "select `id` ID,`name` 名字,`singer` 歌手,`lyric` 歌词,`type` 类型, `create_time` 新增时间, `update_time` 修改时间 from `song`";
		Song song = new Song();
		DBUtils db = new DBUtils();
		try {
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println(db.printSQL(ps,"根据歌曲ID获取"));
			rs = ps.executeQuery();
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
				db.close(rs, ps, conn);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
		return song;
	}

	@Override
	public Integer getSize() throws Exception {
		String sql = "select count(*) from `song`";
		DBUtils db = new DBUtils();
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

	@Override
	public List<Song> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Song getSongById(int id, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
