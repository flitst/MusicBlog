package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.IUserDao;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;
import com.explorer.musicblog.service.IUserService;

/**
 * zhangzhong 2018年5月28日下午11:59:27
 */
public class UserServiceImpl implements IUserService {

	IUserDao iu = DaoFactory.getUserDao();
	User u = new User();

	@Override
	public boolean register(User user) throws Exception {
		if (user != null) {
			iu.register(user);
			return true;
		}
		return false;
	}

	@Override
	public User login(String uname,String pwd) throws Exception {
		return iu.login(uname,pwd);
	}
	
	@Override
	public boolean delete(Integer id) throws Exception {
		if (id != 0 || id != null) {
			iu.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(User user) throws Exception {
		if (user != null) {
			iu.update(user);
			return true;
		}
		return false;
	}

	@Override
	public Integer getSize() throws CustomException {
		return iu.getSize();
	}

	@Override
	public List<Map<String, Object>> getAll() throws Exception {
		return iu.getAll();
	}

	@Override
	public User getById(Integer id) throws Exception {
		if (id != 0 || id != null) {
			u = iu.getById(id);
		}
		return u;
	}

	@Override
	public User getUser(User user) throws Exception {
		return iu.getUser(user);
	}

	@Override
	public Integer commonCUD(String sql, Object... args) throws Exception {
		if (sql != null && args != null) {
			return iu.renew(sql, args);
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> commonQuery(Class<User> clazz, String sql, Object... args) throws Exception {
		if (sql != null && args != null) {
			return iu.query(clazz, sql, args);
		} else {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) throws Exception {
		return iu.get(params);
	}

}
