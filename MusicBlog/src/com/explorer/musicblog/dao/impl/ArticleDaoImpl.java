package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.IArticleDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Article;
import com.explorer.musicblog.util.DBUtils;
import com.explorer.musicblog.util.StringUtils;

public class ArticleDaoImpl implements IArticleDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public Integer insert(Article type) {
		String sql = "INSERT INTO `article`(`title`,`reference`,`description`,`user_id`,`body`,`views`,`comment_count`,`like`,`create_time`,`update_time`) VALUES (?,?,?,?,?,default,default,default,default,default)";
		DBUtils db = new DBUtils();
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, type.getTitle());
			ps.setString(2, type.getReference());
			ps.setObject(3, type.getDescription());
			ps.setObject(4, type.getUid());
			ps.setString(5, type.getBody());
			System.out.println(db.printSQL(ps,"新增文章SQL"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(Article type) {
		String sql = "UPDATE `article` SET `title`=?,`reference`=?,`description`=?,`user_id`=?,`body`=?,`views`=?,`comment_count`=?,`like`,`update_time`=default";// NOW()
		DBUtils db = new DBUtils();
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, type.getTitle());
			ps.setString(2, type.getReference());
			ps.setObject(3, type.getDescription());
			ps.setObject(4, type.getUid());
			ps.setString(5, type.getBody());
			ps.setInt(6, type.getViews());
			ps.setInt(7, type.getCommentCount());
			ps.setInt(8, type.getLike());
			//ps.setString(9, type.getModifyTime());
			System.out.println(db.printSQL(ps,"修改文章"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Article type) {
		String sql = "DELETE FROM `article` WHERE `id`=?";
		DBUtils db = new DBUtils();
		conn = db.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, type.getId());
			System.out.println(db.printSQL(ps,"删除文章SQL"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer renew(String sql, Object... args) {
		DBUtils db = new DBUtils();
		if (null != StringUtils.isLegal(sql)){
			try {
				for (int i = 0; i < args.length; i++) {
						ps.setObject(i + 1, args[i]);
				}
				System.out.println(db.printSQL(ps,"修改文章SQL"));
				return ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;  
	}

	@Override
	public Integer getSize() throws Exception {
		String sql = "select count(*) from `article`";
		System.out.println("获取用户数SQL:"+sql);
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				System.out.println(db.printSQL(ps,"获取文章总数"));
				Integer count = null;
				while(rs.next()) {
					count = rs.getInt(1);
				}
				return count;
			} catch (SQLException e) {
				throw new Exception("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, ps, conn);
				} catch (CustomException e) {
					new RuntimeException("关闭数据库连接失败!" + e.getMessage());
				}
			}
		}
		return null;
	}
	
	@Override
	public List<Article> getByName(String name) {
		String sql = "select * from `article` where `title` like ? or `description` like ? `body` like ?";
		DBUtils db = new DBUtils();
		conn = db.getConnection();
		List<Article> articles = new ArrayList<Article>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			ps.setString(2, "%"+name+"%");
			ps.setString(3, "%"+name+"%");
			System.out.println(db.printSQL(ps,"根据名称获取文章"));
			rs = ps.executeQuery();
			Article article = null;
			while (rs.next()) {
				article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setReference(rs.getString("reference"));
				article.setDescription(rs.getString("description"));
				article.setUid(rs.getInt("user_id"));
				article.setBody(rs.getString("body"));
				article.setViews(rs.getInt("views"));
				article.setCommentCount(rs.getInt("comment_count"));
				article.setLike(rs.getInt("like"));
				article.setCreateTime(rs.getString("create_time"));
				article.setUpdateTime(rs.getString("update_time"));
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	@Override
	public List<Article> getUserAllArticle(Integer id) {
		String sql = "SELECT  * FROM `article` article LEFT JOIN  `user` u ON article.user_id=u.id WHERE u.id=?";
		DBUtils db = new DBUtils();
		conn = db.getConnection();
		List<Article> articles = new ArrayList<Article>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println(db.printSQL(ps,"获取用户所有文章"));
			rs = ps.executeQuery();
			Article article = null;
			while (rs.next()) {
				article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setReference(rs.getString("reference"));
				article.setDescription(rs.getString("description"));
				article.setUid(rs.getInt("user_id"));
				article.setBody(rs.getString("body"));
				article.setViews(rs.getInt("views"));
				article.setCommentCount(rs.getInt("comment_count"));
				article.setLike(rs.getInt("like"));
				article.setCreateTime(rs.getString("create_time"));
				article.setUpdateTime(rs.getString("update_time"));
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	@Override
	public List<Article> getAll() {
		String sql = "SELECT * FROM `article`";
		DBUtils db = new DBUtils();
		conn = db.getConnection();
		List<Article> articles = new ArrayList<Article>();
		try {
			ps = conn.prepareStatement(sql);
			System.out.println(db.printSQL(ps,"获取所有文章"));
			rs = ps.executeQuery();
			Article article = null;
			while (rs.next()) {
				article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setReference(rs.getString("reference"));
				article.setDescription(rs.getString("description"));
				article.setUid(rs.getInt("user_id"));
				article.setBody(rs.getString("body"));
				article.setViews(rs.getInt("views"));
				article.setCommentCount(rs.getInt("comment_count"));
				article.setLike(rs.getInt("like"));
				article.setCreateTime(rs.getString("create_time"));
				article.setUpdateTime(rs.getString("update_time"));
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public List<Map<String, Object>> query(Class<Article> clazz, String sql, Object... args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
