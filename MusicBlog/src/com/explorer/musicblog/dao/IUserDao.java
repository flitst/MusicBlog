package com.explorer.musicblog.dao;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;

/**
 * zhangzhong Sep 18, 2019 4:40:35 AM
 */
public interface IUserDao extends ICommonDao<User, String, Object> {

	/**
	 * 用户登录
	 * @param name
	 * @param password
	 * @return 当前用户
	 * @throws CustomException
	 */
	public User login(String uname,String pwd) throws Exception;
	
	/**
	 * 修改用户密码
	 * @param oldpwd 修改之前的密码
	 * @param user 修改的信息，包括邮箱、手机验证方式
	 */
	public Integer updatePWD(String oldpwd,User user);
	
	/**
	 * 启用/禁用用户
	 * @return
	 */
	public boolean disableUser(Integer id,Byte value);

	/**
	 * 获取用户信息
	 * @param value
	 * @return 返回用户信息
	 * @throws Exception 
	 */
	public User checkUser(User user) throws Exception;
}
