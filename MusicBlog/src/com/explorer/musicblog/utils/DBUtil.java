package com.explorer.musicblog.utils;

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
public class DBUtil {

	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;

	// 获取数据库连接
	public Connection getConn() throws CustomException {
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
//			System.out.println(driver);
//			System.out.println(url);
//			System.out.println(user);
//			System.out.println(password);
			// 注册驱动
			Class.forName(driver);
			// 用驱动管理器获取数据库连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			throw new CustomException("获取配置文件错误!" + e.getMessage());
		} catch (SQLException e) {
			throw new CustomException("获取数据库连接失败!" + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new CustomException("类加载错误!" + e.getMessage());
		}
		return conn;
	}

	// 关闭数据库连接
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) throws CustomException {
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
			throw new CustomException("关闭数据库连接错误!" + e.getMessage());
		}
	}

}
