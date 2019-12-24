package com.explorer.musicblog.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.User;

/**
 * zhangzhong Sep 18, 2019 4:40:35 AM
 */
public interface IUserDao extends ICommonDao<User, String, Object> {

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @throws SQLException 
	 * @throws CustomException 
	 */
	public Integer register(User user) throws Exception;

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
	 * 根据ID删除信息
	 * 
	 * @param id
	 * @return
	 * @throws CustomException 
	 */
	public Integer delete(Integer id) throws Exception;

	/**
	 * 根据ID修改信息
	 * 
	 * @param t
	 * @throws CustomException 
	 */
	public Integer update(User user) throws Exception;

	/**
	 * 获取用户数量
	 * @return
	 * @throws CustomException
	 */
	public Integer getSize() throws CustomException;
	
	/**
	 * 获取所有信息
	 * 
	 * @return
	 * @throws CustomException 
	 */
	public List<Map<String,Object>> getAll() throws Exception;

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws CustomException 
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
