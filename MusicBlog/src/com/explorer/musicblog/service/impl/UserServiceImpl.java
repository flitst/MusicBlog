package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.IUserDao;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.pojo.User;
import com.explorer.musicblog.service.IUserService;

/**
 * zhangzhong 2018年5月28日下午11:59:27
 */
public class UserServiceImpl implements IUserService {

	IUserDao userDao = DaoFactory.getUserDaoInstace();

	@Override
	public User getByUserName(String name) throws Exception {
		return userDao.getByUserName(name);
	}
	
	@Override
	public List<User> getByName(String name) throws Exception {
		return userDao.getByName(name);
	}
	
	@Override
	public Integer insert(User user) throws Exception {
		if (user != null) {
			return userDao.insert(user);
		}
		return null;
	}

	@Override
	public User login(String uname,String pwd) throws Exception {
		return userDao.login(uname,pwd);
	}
	
	@Override
	public Integer delete(Integer id) throws Exception {
		return userDao.delete(id);
	}

	@Override
	public Integer update(User user) throws Exception {
		if (user != null) {
			return userDao.update(user);
		}
		return null;
	}

	@Override
	public Integer updatePWD(String oldpwd,User user) throws Exception {
		return userDao.updatePWD(oldpwd,user);
	}
	
	@Override
	public boolean disableUser(Integer id,Byte value) throws Exception {
		return userDao.disableUser(id,value);
	}
	
	@Override
	public Integer getSize() throws Exception {
		return userDao.getSize();
	}

	@Override
	public List<User> getAll(){
		return userDao.getAll();
	}

	@Override
	public User getById(Integer id) throws Exception {
		User u = new User();
		if (id != 0 || id != null) {
			u = userDao.getById(id);
		}
		return u;
	}

	@Override
	public User checkUser(User user) throws Exception {
		return userDao.checkUser(user);
	}

	@Override
	public Integer commonCUD(String sql, Object... args) throws Exception {
		if (sql != null && args != null) {
			return userDao.renew(sql, args);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> commonQuery(Class<User> clazz, String sql, Object... args) throws Exception {
		if (sql != null && args != null) {
			return userDao.query(clazz, sql, args);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) throws Exception {
		return userDao.get(params);
	}

	@Override
	public Integer delete(User type) {
		return userDao.delete(type);
	}

}
