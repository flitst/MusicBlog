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
import com.explorer.musicblog.util.DBUtils;

/**
 * zhangzhong 2018年5月28日 下午11:58:41
 * 
 */
public class UserDaoImpl implements IUserDao {
    
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * 添加用户
	 * @throws SQLException 
	 * @throws CustomException 
	 */
	@Override
	public Integer insert(User user){
		StringBuffer sql = new StringBuffer("insert into `user` ");
		sql.append("(`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`create_time`,`update_time`,`status`) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,1)");
		Integer i = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, user.getAccount());
				ps.setString(2, user.getPwd());
				ps.setString(3, user.getNickname());
				ps.setString(4, user.getSignature());
				ps.setInt(5, user.getAge());
				ps.setInt(6, user.getSex());
				ps.setString(7, Arrays.toString(user.getHobby()));
				ps.setString(8, user.getHead());
				ps.setString(9, user.getImage());
				ps.setString(10, user.getEmail());
				ps.setString(11, user.getMobile());
				ps.setString(12, user.getCreateTime());
				ps.setString(13, user.getUpdateTime());
				System.out.println(db.printSQL(ps,"注册用户"));
				i = ps.executeUpdate();
			} catch (SQLException e) {
				new RuntimeException("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, ps, conn);
				} catch (CustomException e) {
					new RuntimeException("关闭数据库连接失败!" + e.getMessage());
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
			String sql = "select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`create_time`,`update_time`,`status` from `user`where `account`=? and `pwd`=?";
			DBUtils db = new DBUtils();
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if(conn != null) {
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, uname);
					ps.setString(2, pwd);
					System.out.println(db.printSQL(ps,"用户登录"));
					rs = ps.executeQuery();
					List<User> users = new ArrayList<User>();
					User user = new User();
					if(rs.next()) {
						getUser(rs,user,users,true);
					}
					return user;
				} catch (SQLException e) {
					throw new CustomException("执行SQL失败!" + e.getMessage());
				} finally {
					try {
						db.close(rs, ps, conn);
					} catch (CustomException e) {
						new RuntimeException("关闭数据库连接失败!" + e.getMessage());
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
	private List<User> getUser(ResultSet rs,User user,List<User> list,boolean bool) throws CustomException {
		while(bool) {
			try {
				user.setId(rs.getInt("id"));
				user.setAccount(rs.getString("account"));
				user.setNickname(rs.getString("nickname"));
				user.setSignature(rs.getString("signature"));
				user.setPwd(rs.getString("pwd"));
				user.setAge(rs.getShort("age"));
				user.setSex(rs.getByte("sex"));
				String hobby = rs.getString("hobby");
				if (hobby != null) {
					String[] str = { hobby.toString() };
					user.setHobby(str);
				}
				user.setHead(rs.getString("head"));
				user.setImage(rs.getString("image"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				user.setCreateTime(rs.getString("create_time"));
				user.setUpdateTime(rs.getString("update_time"));
				user.setStatus(rs.getByte("status"));
				System.out.println("getUser user:"+user);
				if (list != null) {
					list.add(user);
				}
				return list;
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Integer delete(User type) {
		return null;
	}
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Integer delete(Integer id) throws Exception {
		String sql = "delete from `user` where `id`=?;";
		Integer i = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				System.out.println(db.printSQL(ps,"删除用户"));
				i = ps.executeUpdate();
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
		return i;
	}

	/**
	 *	 修改用户
	 */
	@Override
	public Integer update(User user){
		StringBuffer sql = new StringBuffer("update `user` set ");
		sql.append("`nickname` = ? , ");
		sql.append("`signature` = ? , ");
		sql.append("`age` = ?, ");
		sql.append("`sex` = ?, ");
		sql.append("`hobby` = ?, ");
		sql.append("`head` = ?, ");
		sql.append("`image` = ?, ");
		sql.append("`email` = ?, ");
		sql.append("`mobile` = ?, ");
		sql.append("`update_time` = ?, ");
		sql.append("`status` = ?");
		sql.append(" where `account` = ? and `pwd`=?;");
		Integer i = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, user.getNickname());
				ps.setString(2, user.getSignature());
				ps.setInt(3, user.getAge());
				ps.setInt(4, user.getSex());
				ps.setString(5, Arrays.toString(user.getHobby()));
				ps.setString(6, user.getHead());
				ps.setString(7, user.getImage());
				ps.setString(8, user.getEmail());
				ps.setString(9, user.getMobile());
				ps.setString(10, user.getUpdateTime());
				ps.setByte(11, user.getStatus());
				ps.setString(12, user.getAccount());
				ps.setString(13, user.getPwd());
				System.out.println(db.printSQL(ps,"修改用户信息"));
				i = ps.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException("执行SQL失败!" + e.getMessage());
			} finally {
				try {
					db.close(rs, ps, conn);
				} catch (CustomException e) {
					new RuntimeException("关闭数据库连接失败!" + e.getMessage());
				}
			}
		}
		return i;
	}

	/**
	 *	修改用户密码
	 */
	@Override
	public Integer updatePWD(String oldpwd,User user) { 
		String sql = "update `user` set pwd=?,update_time=now() where mobile=? or email=?";
		Integer i = null;
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		try {
			if (conn != null) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getPwd());
				ps.setString(2, user.getMobile());
				ps.setString(3, user.getEmail());
				System.out.println(db.printSQL(ps,"修改用户密码"));
				i = ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("执行SQL失败!" + e.getMessage());
		} finally {
			try {
				db.close(rs, ps, conn);
			} catch (CustomException e) {
				new RuntimeException("关闭数据库连接失败!" + e.getMessage());
			}
		}
		return i;
	}
	
	@Override
	public Integer getSize() throws CustomException {
		String sql = "select count(*) from `user`";
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
				System.out.println(db.printSQL(ps,"获取总用户数"));
				rs = ps.executeQuery();
				Integer num = null;
				while (rs.next()) {
					num = rs.getInt(1);
				}
				return num;
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
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
	
	/**
	 *	 获取所有用户
	 */
	@Override
	public List<User> getAll(){
		String sql = "select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`create_time`,`update_time`,`status` from `user`";
		System.out.println("获取所有用户SQL:"+sql);
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				System.out.println(db.printSQL(ps,"获取所有用户"));
				rs = ps.executeQuery();
				List<User> users = new ArrayList<User>();
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
					try {
						getUser(rs,user,users,true);
					} catch (CustomException e) {
						e.printStackTrace();
						throw new RuntimeException("获取用户信息失败!" + e.getMessage());
					}
				}
				return users;
			} catch (SQLException e) {
				throw new RuntimeException("执行SQL失败!" + e.getMessage());
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
	
	/**
	 * 根据用户名查询
	 */
	@Override
	public List<User> getByName(String name) {
		if (name == null || "".equals(name) ) {
			return null;
		}
		String sql = "select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`create_time`,`update_time`,`status` from `user` where `account` like ?";
		System.out.println("获取所有用户SQL:"+sql);
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				System.out.println(db.printSQL(ps,"根据账号获取用户"));
				rs = ps.executeQuery();
				List<User> users = new ArrayList<User>();
				User user = null;
				while (rs.next()) {
					user = new User();
					try {
						getUser(rs,user,users,true);
					} catch (CustomException e) {
						e.printStackTrace();
						throw new RuntimeException("获取用户信息失败!" + e.getMessage());
					}
				}
				return users;
			} catch (SQLException e) {
				throw new RuntimeException("执行SQL失败!" + e.getMessage());
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

	/**
	 * 根据用户ID查询用户
	 * @throws Exception 
	 */
	@Override
	public User getById(Integer id) throws Exception {
		String sql = "select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`create_time`,`update_time`,`status` from `user` where `id`=?";
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, id.toString());
				System.out.println(db.printSQL(ps,"根据用户ID获取用户"));
				rs = ps.executeQuery();
				User user = new User();
				while (rs.next()) {
					getUser(rs,user,null,true);
				}
				return user;
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

	/**
	 * 根据账号/邮箱/手机 获取账号/邮箱/手机
	 * @throws CustomException 
	 */
	@Override
	public User checkUser(User user) throws CustomException {
		String sql = "select `account`,`email`,`mobile` from `user` where `account`=? or `email`=? or `mobile`=?";
		DBUtils db = new DBUtils();
		try {
			conn = db.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
		}
		if(conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getAccount());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getMobile());
				System.out.println(db.printSQL(ps,"校验用户账号/邮箱/手机号"));
				rs = ps.executeQuery();
				if (rs.next()) {
					user.setAccount(rs.getString("account"));
					user.setEmail(rs.getString("email"));
					user.setMobile(rs.getString("mobile"));
					return user;
				}
			} catch (SQLException e) {
				throw new CustomException("执行SQL失败!" + e.getMessage());
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

	/**
	 * 用户通用增改
	 * @param sql  SQL语句
	 * @param args SQL参数
	 * @throws CustomException 
	 */
	@Override
	public Integer renew(String sql, Object... args){
		if (sql != null && sql.trim() != "" && args.length >= 0) {
			DBUtils db = new DBUtils();
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if(conn != null) {
				try {
					ps = conn.prepareStatement(sql);
					for (int i = 0; i < args.length; i++) {
						ps.setObject(i + 1, args[i]);
					}
					System.out.println(db.printSQL(ps,"通用用户增删改"));
					return ps.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException("执行SQL语句失败!" + e.getMessage());
				} finally {
					try {
						db.close(rs, ps, conn);
					} catch (CustomException e) {
						new RuntimeException("关闭数据库连接失败!" + e.getMessage());
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
			DBUtils db = new DBUtils();
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if (conn != null) {
				try {
					ps = conn.prepareStatement(sql);
					System.out.println(db.printSQL(ps,"通用用户查询"));
					for (int i = 0; i < args.length; i++) {
						ps.setObject(i + 1, args[i]);
					}
					System.out.println(ps);
					rs = ps.executeQuery();
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
					try {
						db.close(rs, ps, conn);
					} catch (CustomException e) {
						new RuntimeException("关闭数据库连接失败!" + e.getMessage());
					}
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
			StringBuffer sb = new StringBuffer("select `id`,`account`,`pwd`,`nickname`,`signature`,`age`,`sex`,`hobby`,`head`,`image`,`email`,`mobile`,`create_time`,`update_time`,`status` from `user` where 1=1");
			for (int i = 0; i < params.size(); i++) {
//				Map<String, Object> map = params.get(i);
//				sb.append(" and "+map.get("param")+map.get("pattern")+map.get("value"));
				sb.append(" and " + params.get(i).get("param"));
				sb.append(" " + params.get(i).get("pattern"));
				sb.append(" " + params.get(i).get("value"));
			}
			System.out.println("sb:" + sb.toString());
			DBUtils db = new DBUtils();
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if(conn != null) {
				try {
					ps = conn.prepareStatement(sb.toString());
					System.out.println(db.printSQL(ps,"通用用户获取"));
					ResultSet rs = ps.executeQuery();
					User u = new User();
					List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
					HashMap<String, Object> user = new HashMap<String, Object>();
					while (rs.next()) {
						u.setId(rs.getInt("id"));
						u.setAccount(rs.getString("account"));
						u.setNickname(rs.getString("nickname"));
						u.setSignature(rs.getString("signature"));
						u.setSex(rs.getByte("sex"));
						u.setAge(rs.getShort("age"));
						String[] hobby = { rs.getString("hobby") };
						u.setHobby(hobby);
						u.setHead(rs.getString("head"));
						u.setImage(rs.getString("image"));
						u.setEmail(rs.getString("email"));
						u.setMobile(rs.getString("mobile"));
						u.setCreateTime(rs.getString("create_time"));
						u.setUpdateTime(rs.getString("update_time"));
						u.setStatus(rs.getByte("status"));
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
						db.close(rs, ps, conn);
					} catch (CustomException e) {
						throw new RuntimeException("关闭数据库连接失败!" + e.getMessage());
					}
				}
			}
		}
		return null;
	}

	/**
	 *启用/禁用用户
	 */
	@Override
	public boolean disableUser(Integer id,Byte value) {
		if (id != null && value != null) {
			String sql = "update `user` set `status`=? where id=?";
			DBUtils db = new DBUtils();
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				throw new RuntimeException("获取数据库连接失败!" + e.getMessage());
			}
			if (conn != null) {
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, value);
					ps.setInt(2, id);
					System.out.println(db.printSQL(ps,"启用/禁用用户"));
					int i = ps.executeUpdate();
					if (i > 0) {
						return true;
					}
				} catch (SQLException e) {
					throw new RuntimeException("执行SQL失败!" + e.getMessage());
				} finally {
					try {
						db.close(rs, ps, conn);
					} catch (CustomException e) {
						new RuntimeException("关闭数据库连接失败!" + e.getMessage());
					}
				}
			}
		}
		return false;
	}
}
