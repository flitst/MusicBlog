package com.explorer.musicblog.service;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.exception.CustomException;

/**
 * zhangzhong Nov 6, 2019 7:41:46 AM 通用接口
 */
public interface ICommonService<T, K, V> {
	
	/**
	 * 新增
	 * @param type
	 * @return Integer
	 */
	public Integer insert(T type) throws Exception;
	
	/**
	 * 修改
	 * @param type
	 * @return Integer
	 */
	public Integer update(T type) throws Exception;
	
	/**
	 * 删除
	 * @param type
	 * @return Integer
	 */
	public Integer delete(T type) throws Exception;	

	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer delete(Integer id) throws Exception;
	
	/**
	 * 通用新增/删除/修改
	 * @return Integer
	 */
	public Integer commonCUD(String sql, Object... args) throws Exception;

	/**
	 * 根据名称获取信息
	 * @return List<T>
	 */
	List<T> getByName(String name) throws Exception;
	
	/**
	 * 根据ID获取信息
	 * @return
	 * @throws Exception 
	 */
	T getById(Integer id) throws Exception;
	
	/**
	 * 获取所有
	 * @return List<T>
	 */
	List<T> getAll() throws Exception;

	/**
	 * 获取对象总个数
	 * @return 这个总个数
	 * @throws Exception
	 */
	Integer getSize() throws Exception;
	
	/**
	 * 通用查询
	 * @param clazz
	 * @param sql
	 * @param agrs
	 * @return List<Map<K, V>>
	 * @throws CustomException
	 * @throws Exception 
	 */
	public List<Map<K, V>> commonQuery(Class<T> clazz, String sql, Object... args) throws CustomException, Exception;

	/**
	 * 根据提供参数来查询(可用等值/模糊查询)
	 * @param params
	 * @return List<Map<K, V>>
	 * @throws CustomException
	 * @throws Exception 
	 */
	public List<Map<K, V>> get(List<Map<K, V>> params) throws CustomException, Exception;

}
