package com.explorer.musicblog.dao;

import java.util.List;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;

/**
 * zhangzhong Sep 18, 2019 4:40:35 AM
 */
public interface IUserDAO extends ICommonDAO<User, String, Object> {

	/**
	 * 用户登录
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws CustomException
	 */
	public User login(String uname,String pwd) throws Exception;

	/**
	 * 	根据ID删除用户信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public Integer delete(Integer id) throws Exception;

	/**
	 * 	根据ID修改信息
	 * 
	 * @param user
	 */
	public Integer update(User user);
	
	/**
	 * 	修改用户密码
	 * @param oldpwd 修改之前的密码
	 * @param user 修改的信息，包括邮箱、手机验证方式
	 */
	public Integer updatePWD(String oldpwd,User user);

	/**
	 * 	获取用户所有个数
	 * @return
	 * @throws CustomException
	 */
	public Integer getSize() throws CustomException;
	
	/**
	 * 	获取所有信息
	 * 
	 * @return
	 * @throws CustomException 
	 */
	public List<User> getAll();

	/**
	 * 	根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public User getById(Integer id) throws Exception;

	/**
	 * 获取用户信息
	 * 
	 * @param value
	 * @return 返回用户信息
	 * @throws Exception 
	 */
	public User getUser(User user) throws Exception;
}
