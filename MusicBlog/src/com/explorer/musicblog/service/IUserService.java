package com.explorer.musicblog.service;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;

/**
 * zhangzhong Sep 18, 2019 4:42:15 AM
 */
public interface IUserService extends ICommonService<User, String, Object> {
	
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 * @throws CustomException
	 * @throws Exception 
	 */
	public User login(String uname,String pwd) throws CustomException, Exception;

	/**
	 * 	修改用户密码
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
	 * 根据用户名/邮箱/手机号查询用户
	 * 
	 * @param value
	 * @return 返回用户信息
	 */
	public User checkUser(User user) throws Exception;
}
