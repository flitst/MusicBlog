package com.explorer.musicblog.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.IUserDao;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;
import com.explorer.musicblog.utils.DBUtil;

/**
 * zhangzhong 2018年5月28日下午11:58:41
 */
public class UserDaoImpl implements IUserDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 给用户调用创建一个唯一实例
	private static UserDaoImpl instance = null;

	/**
	 * 获取实例
	 * @return
	 */
	public static synchronized UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	/**
	 * 添加用户
	 * @throws SQLException 
	 * @throws CustomException 
	 */
	@Override
	public Integer register(User user) throws SQLException, CustomException {
		StringBuffer sql = new StringBuffer("insert into `user` ");
		sql.append("(`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`registerTime`,`modifyTime`) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?);");
		DBUtil db = new DBUtil();
		Integer i = null;
		try {
			conn = db.getConn();
		} catch (Exception e) {
			throw new CustomException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, user.getAccount());
				pstmt.setString(2, user.getPwd());
				pstmt.setString(3, user.getNickname());
				pstmt.setString(4, user.getSignature());
				pstmt.setInt(5, user.getAge());
				pstmt.setInt(6, user.getSex());
				pstmt.setString(7, Arrays.toString(user.getHobby()));
				pstmt.setString(8, user.getHead());
				pstmt.setString(9, user.getImage());
				pstmt.setString(10, user.getEmail());
				pstmt.setString(11, user.getMobile());
				pstmt.setString(12, user.getRegisterTime());
				pstmt.setString(13, user.getModifyTime());
				System.out.println(sql);
				i = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, pstmt, conn);
				} catch (CustomException e) {
					throw new CustomException("关闭数据库连接失败!" + e.getMessage());
				}
			}
		}
		return i;
	}
	
	/**
	 * 用户登录
	 */
	@Override
	public User login(String uname,String pwd) throws CustomException {
		if (uname != null && pwd != null && !"".equals(uname.trim()) && !"".equals(pwd.trim())) {
			DBUtil db = new DBUtil();
			String sql = "select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`registerTime`,`modifyTime` from `tb_user`where `account`=? and `pwd`=?";
			try {
				conn = db.getConn();
			} catch (Exception e) {
				throw new CustomException("获取数据库连接失败!" + e.getMessage());
			}
			if(conn != null) {
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, uname);
					pstmt.setString(2, pwd);
					System.out.println(pstmt);
					rs = pstmt.executeQuery();
					List<Map<String,Object>> users = new ArrayList<Map<String,Object>>();
					User user = new User();
					if(rs.next()) {
						getUser(rs,user,users,true);
					}
					return user;
				} catch (SQLException e) {
					throw new CustomException("执行SQL失败!" + e.getMessage());
				} finally {
					try {
						db.close(rs, pstmt, conn);
					} catch (CustomException e) {
						throw new CustomException("关闭数据库连接失败!" + e.getMessage());
					}
				}
			}
		} else {
			throw new CustomException("无法取得用户信息!用户名或密码不能为空!");
		}
		return null;
	}
	
	/**
	 * 获取用户信息
	 * @param rs	获取的结果集
	 * @param user	封装用户对象
	 * @param list	存储用户对象
	 * @param bool	是否执行操作
	 * @return	存储好的用户对象list
	 * @throws CustomException
	 */
	private List<Map<String,Object>> getUser(ResultSet rs,User user,List<Map<String,Object>> list,boolean bool) throws CustomException {
		while(bool) {
			try {
				Map<String, Object> map = new HashMap<String,Object>();
				user.setId(rs.getInt("id"));
				user.setAccount(rs.getString("account"));
				user.setNickname(rs.getString("nickname"));
				user.setSignature(rs.getString("signature"));
				user.setPwd(rs.getString("pwd"));
				user.setAge(rs.getByte("age"));
				user.setSex(rs.getByte("sex"));
				String hobby = rs.getString("hobby");
				String[] str = { hobby.toString() };
				user.setHobby(str);
				user.setHead(rs.getString("head"));
				user.setImage(rs.getString("image"));
				String email = rs.getString("email");
				user.setEmail(email);
				user.setMobile(rs.getString("mobile"));
				user.setRegisterTime(rs.getString("registerTime"));
				user.setModifyTime(rs.getString("modifyTime"));
				System.out.println("getUser user:"+user);
				map.put(user.getId().toString(), user);
				list.add(map);
				return list;
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws CustomException 
	 */
	@Override
	public Integer delete(Integer id) throws CustomException {
		String sql = "delete from `user` where `id`=?;";
		Integer i = null;
		DBUtil db = new DBUtil();
		try {
			conn = db.getConn();
		} catch (Exception e) {
			throw new CustomException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				System.out.println(sql);
				i = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, pstmt, conn);
				} catch (CustomException e) {
					throw new CustomException("关闭数据库连接失败!" + e.getMessage());
				}
			}
		}
		return i;
	}

	/**
	 * 修改用户
	 * @throws CustomException 
	 */
	@Override
	public Integer update(User user) throws CustomException {
		StringBuffer sql = new StringBuffer("update `user` set ");
		sql.append("`nickname` = ? , ");
		sql.append("`signature` = ? , ");
		sql.append("`age` = ?, ");
		sql.append("`sex` = ?, ");
		sql.append("`hobby` = ?, ");
		sql.append("`head` = ?, ");
		sql.append("`image` = ?, ");
		sql.append("`email` = ?, ");
		sql.append("`mobile` = ?,");
		sql.append("`modifyTime` = ?");
		sql.append(" where `account` = ? and `pwd`=?;");
		Integer i = null;
		DBUtil db = new DBUtil();
		try {
			conn = db.getConn();
		} catch (Exception e) {
			throw new CustomException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, user.getNickname());
				pstmt.setString(2, user.getSignature());
				pstmt.setInt(3, user.getAge());
				pstmt.setInt(4, user.getSex());
				pstmt.setString(5, Arrays.toString(user.getHobby()));
				pstmt.setString(6, user.getHead());
				pstmt.setString(7, user.getImage());
				pstmt.setString(8, user.getEmail());
				pstmt.setString(9, user.getMobile());
				pstmt.setString(10, user.getModifyTime());
				pstmt.setString(11, user.getAccount());
				pstmt.setString(12, user.getPwd());
				System.out.println("修改用户信息:"+pstmt);
				i = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, pstmt, conn);
				} catch (CustomException e) {
					throw new CustomException("关闭数据库连接失败!" + e.getMessage());
				}
			}
		}
		return i;
	}

	@Override
	public Integer getSize() throws CustomException {
		String sql = "select count(*) from `user`";
		System.out.println("获取用户数SQL:"+sql);
		DBUtil db = new DBUtil();
		try {
			conn = db.getConn();
		} catch (Exception e) {
			throw new CustomException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				Integer count = null;
				while(rs.next()) {
					count = rs.getInt(1);
					System.out.println("size:"+count);
				}
				return count;
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * 获取所有用户
	 * @throws CustomException 
	 */
	@Override
	public List<Map<String,Object>> getAll() throws CustomException {
		String sql = "select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`registerTime`,`modifyTime` from `user`";
		System.out.println("获取所有用户SQL:"+sql);
		DBUtil db = new DBUtil();
		try {
			conn = db.getConn();
		} catch (Exception e) {
			throw new CustomException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				System.out.println("获取所有用户pstmt:"+pstmt);
				rs = pstmt.executeQuery();
				List<Map<String,Object>> users = new ArrayList<Map<String,Object>>();
				User user = null;
				while (rs.next()) {
					//有一个用户就得新创建一个用户,在循环外面统一创建用户的话,后查询的用户会覆盖前面所查询的用户
					/* 错误代码:
					 * User user = new User();
					 * while (rs.next()) {
					 * 	   getUser(rs,user,users,true);
					 * }
					 * users:[{1=User [uid=3, name=小晴, nickname=null, signature=null, age=18, sex=2, password=xiaoq, hobby=[[跑步, 看书, 旅游, 游泳, 乒乓球]], head=null, image=null, email=, mobile=, registerTime=2019-11-24 12:52:47]}, 
					 * {2=User [uid=3, name=小晴, nickname=null, signature=null, age=18, sex=2, password=xiaoq, hobby=[[跑步, 看书, 旅游, 游泳, 乒乓球]], head=null, image=null, email=, mobile=, registerTime=2019-11-24 12:52:47]}, 
					 * {3=User [uid=3, name=小晴, nickname=null, signature=null, age=18, sex=2, password=xiaoq, hobby=[[跑步, 看书, 旅游, 游泳, 乒乓球]], head=null, image=null, email=, mobile=, registerTime=2019-11-24 12:52:47]}]
					 */
					user = new User();
					getUser(rs,user,users,true);
				}
				return users;
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, pstmt, conn);
				} catch (CustomException e) {
					throw new CustomException("关闭数据库连接失败!" + e.getMessage());
				}
			}
		}
		return null;
	}

	/**
	 * 根据用户ID查询用户
	 * @throws CustomException 
	 */
	@Override
	public User getById(Integer id) throws CustomException {
		String sql = "select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`registerTime`,`modifyTime` from `user` where `uid`=?";
		User user = new User();
		DBUtil db = new DBUtil();
		try {
			conn = db.getConn();
		} catch (Exception e) {
			throw new CustomException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id.toString());
				System.out.println(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					getUser(rs,user,null,true);
				}
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, pstmt, conn);
				} catch (CustomException e) {
					throw new CustomException("关闭数据库连接失败!" + e.getMessage());
				}
			}
		}
		return user;
	}

	/**
	 * 根据账号/邮箱/手机 获取账号/邮箱/手机
	 * @throws CustomException 
	 */
	@Override
	public User getUser(User user) throws CustomException {
		String sql = "select `account`,`email`,`mobile` from `user` where `account`=? or `email`=? or `mobile`=?";
		DBUtil db = new DBUtil();
		try {
			conn = db.getConn();
		} catch (Exception e) {
			throw new CustomException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getAccount());
				pstmt.setString(2, user.getEmail());
				pstmt.setString(3, user.getMobile());
				System.out.println(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					user.setAccount(rs.getString("account"));
					user.setEmail(rs.getString("email"));
					user.setMobile(rs.getString("mobile"));
				}
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, pstmt, conn);
				} catch (CustomException e) {
					throw new CustomException("关闭数据库连接失败!" + e.getMessage());
				}
			}
		}
		return user;
	}

	/**
	 * 用户通用增改
	 * @param sql  SQL语句
	 * @param args SQL参数
	 * @throws CustomException 
	 */
	@Override
	public Integer renew(String sql, Object... args){
		if (sql != null && sql.trim() != "" && args.length >= 0) {
			DBUtil db = new DBUtil();
			try {
				conn = db.getConn();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if(conn != null) {
				try {
					pstmt = conn.prepareStatement(sql);
					for (int i = 0; i < args.length; i++) {
						pstmt.setObject(i + 1, args[i]);
					}
					System.out.println("用户通用增改:"+pstmt);
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException("执行SQL语句失败!" + e.getMessage());
				} finally {
					try {
						db.close(rs, pstmt, conn);
					} catch (CustomException e) {
						throw new RuntimeException("关闭数据库连接失败!" + e.getMessage());
					}
				}
			}
		}
		return null;
	}

	/**
	 * 用户通用查询
	 * 
	 * @param clazz 类型
	 * @param sql   SQL语句
	 * @param args  SQL语句的可变参数
	 */
	@Override
	public List<Map<String, Object>> query(Class<User> clazz, String sql, Object... args) throws Exception{
		if (clazz != null && sql != null && sql.trim() != "") {
			System.out.println(sql);
			DBUtil db = new DBUtil();
			try {
				conn = db.getConn();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if (conn != null) {
				try {
					pstmt = conn.prepareStatement(sql);
					System.out.println(pstmt);
					for (int i = 0; i < args.length; i++) {
						pstmt.setObject(i + 1, args[i]);
					}
					System.out.println(pstmt);
					rs = pstmt.executeQuery();
					ResultSetMetaData metaData = rs.getMetaData();
					int count = rs.getMetaData().getColumnCount();
					List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
					Map<String, Object> map = new HashMap<String, Object>();
					while (rs.next()) {
						User user = clazz.getDeclaredConstructor().newInstance();
						System.out.println(user);
						for (int i = 1; i <= count; i++) {
							Object columnValue = rs.getObject(i);
							String columnName = metaData.getColumnName(i);
							String columnLabel = metaData.getColumnLabel(i);
							System.out.println(rs.getString(i));
							System.out.println(rs.getObject(i).toString());
							System.out.println("columnName:" + columnName);
							System.out.println("columnLabel:" + columnLabel);
							System.out.println("type:" + metaData.getColumnType(i));
							System.out.println("typeName:" + metaData.getColumnTypeName(i));
							map.put(columnName, columnValue);
						}
						users.add(map);
					}
					return users;
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("SQL执行错误!" + e.getMessage());
				} finally {
					db.close(rs, pstmt, conn);
				}
			}
		}
		return null;
	}

	/**
	 * 根据提供参数来查询(可用等值/模糊查询)
	 * 
	 * @throws CustomException
	 */
	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params){
		if (params != null && params.size() > 0) {
			StringBuffer sb = new StringBuffer("select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`registerTime`,`modifyTime` from `user` where 1=1");
			for (int i = 0; i < params.size(); i++) {
//				Map<String, Object> map = params.get(i);
//				sb.append(" and "+map.get("param")+map.get("pattern")+map.get("value"));
				sb.append(" and " + params.get(i).get("param"));
				sb.append(" " + params.get(i).get("pattern"));
				sb.append(" " + params.get(i).get("value"));
			}
			System.out.println("sb:" + sb.toString());
			DBUtil db = new DBUtil();
			try {
				conn = db.getConn();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if(conn != null) {
				try {
					pstmt = conn.prepareStatement(sb.toString());
					System.out.println("sql:" + pstmt);
					ResultSet rs = pstmt.executeQuery();
					User u = new User();
					List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
					HashMap<String, Object> user = new HashMap<String, Object>();
					while (rs.next()) {
						u.setId(rs.getInt("id"));
						u.setAccount(rs.getString("account"));
						u.setNickname(rs.getString("nickname"));
						u.setSignature(rs.getString("signature"));
						u.setSex(rs.getByte("sex"));
						u.setAge(rs.getByte("age"));
						String[] hobby = { rs.getString("hobby") };
						u.setHobby(hobby);
						u.setHead(rs.getString("head"));
						u.setImage(rs.getString("image"));
						u.setEmail(rs.getString("email"));
						u.setMobile(rs.getString("mobile"));
						u.setRegisterTime(rs.getString("registerTime"));
						u.setModifyTime(rs.getString("modifyTime"));
						System.out.println("user:" + u);
						String str = u.getId().toString();
						System.out.println("key:" + str);
						user.put(str, u);
						users.add(user);
						return users;
					}
				} catch (SQLException e) {
					throw new RuntimeException("执行SQL失败!" + e.getMessage());
				} finally {
					try {
						db.close(rs, pstmt, conn);
					} catch (CustomException e) {
						throw new RuntimeException("关闭数据库连接失败!" + e.getMessage());
					}
				}
			}
		}
		return null;
	}
}
