package com.explorer.musicblog.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.explorer.musicblog.exception.CustomException;

/**
 * @author :zhangzhong 创建时间 :2018年5月27日下午4:44:43
 */
public class DBUtils {

	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;

	public static String printSQL(PreparedStatement ps,String msg) {
		return msg + " SQL " + ps.toString().substring(ps.toString().indexOf(":"));
	}
	
	public static DBUtils getDataBase() {
		DBUtils db = new DBUtils();
		conn = db.getConnection();
		return db;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection(){
		try {
			// 获取配置文件
			InputStream in = getClass().getResourceAsStream("./driver.properties");

			// 创建对象
			Properties jdbc = new Properties();
			// 加载配置文件
			jdbc.load(in);

			// 获取配置文件属性
			String driver = jdbc.getProperty("DRIVER");
			String url = jdbc.getProperty("URL");
			String user = jdbc.getProperty("USER");
			String password = jdbc.getProperty("PWD");
			// 注册驱动
			Class.forName(driver);
			// 用驱动管理器获取数据库连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			throw new RuntimeException("获取配置文件错误!" + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("类加载错误!" + e.getMessage());
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * @param rs
	 * @param ps
	 * @param conn
	 * @throws CustomException
	 */
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("关闭数据库连接错误!" + e.getMessage());
		}
	}

}
