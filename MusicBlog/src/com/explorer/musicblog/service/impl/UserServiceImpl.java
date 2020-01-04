package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.IUserDAO;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;
import com.explorer.musicblog.service.IUserService;

/**
 * zhangzhong 2018年5月28日下午11:59:27
 */
public class UserServiceImpl implements IUserService {

	IUserDAO iu = DaoFactory.getUserDao();
	User u = new User();

	@Override
	public Integer insert(User user){
		if (user != null) {
			return iu.insert(user);
		}
		return null;
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
	public Integer update(User user){
		if (user != null) {
			return iu.update(user);
		}
		return null;
	}

	@Override
	public Integer updatePWD(String oldpwd,User user) {
		return iu.updatePWD(oldpwd,user);
	}
	
	@Override
	public Integer getSize() throws CustomException {
		return iu.getSize();
	}

	@Override
	public List<User> getAll(){
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

	@Override
	public Integer delete(User type) {
		return null;
	}
}
