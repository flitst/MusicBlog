package com.explorer.musicblog.service;

import java.util.List;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;

/**
 * zhangzhong Sep 18, 2019 4:42:15 AM
 */
public interface IUserService extends ICommonService<User, String, Object> {

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @throws Exception 
	 */
	public Integer insert(User user);
	
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
	 * 根据用户编号删除用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) throws Exception;

	/**
	 * 	修改用户
	 * 
	 * @param t
	 */
	public Integer update(User user);

	/**
	 * 	修改用户密码
	 * @param oldpwd 修改之前的密码
	 * @param user 修改的信息，包括邮箱、手机验证方式
	 */
	public Integer updatePWD(String oldpwd,User user);

	/**
	 * 获取用户数量
	 * @return
	 * @throws CustomException
	 */
	public Integer getSize() throws CustomException;
	
	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	public List<User> getAll();

	/**
	 * 根据用户编号查询用户
	 * 
	 * @param id
	 * @return
	 */
	public User getById(Integer id) throws Exception;

	/**
	 * 根据用户名/邮箱/手机号查询用户
	 * 
	 * @param value
	 * @return 返回用户信息
	 */
	public User getUser(User user) throws Exception;
}
