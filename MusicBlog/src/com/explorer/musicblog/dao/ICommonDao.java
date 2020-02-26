package com.explorer.musicblog.dao;

import java.util.List;
import java.util.Map;

/**
 * zhangzhong 2018年5月28日下午11:53:10 通用方法
 */
public interface ICommonDao<T, K, V> {

	/**
	 * 新增
	 * @param type
	 * @return Integer
	 */
	public Integer insert(T type);
	
	/**
	 * 修改
	 * @param type
	 * @return Integer
	 */
	public Integer update(T type);
	
	/**
	 * 删除
	 * @param type
	 * @return Integer
	 */
	public Integer delete(T type);

	/**
	 * 根据ID删除
	 * @param type
	 * @return Integer
	 * @throws Exception 
	 */
	public Integer delete(Integer id);
	
	/**
	 * 通用增改
	 * @param sql
	 * @param agrs
	 * @return Integer
	 */
	public Integer renew(String sql, Object... args);

	/**
	 * 根据名称获取信息
	 * @return List<T>
	 */
	List<T> getByName(String name);
	
	/**
	 * 根据ID获取信息
	 * @return
	 * @throws Exception 
	 */
	T getById(Integer id);
	
	/**
	 * 	获取所有
	 * @return List<T>
	 */
	List<T> getAll();

	/**
	 * 获取对象个数
	 * @return 对象总个数
	 * @throws Exception
	 */
	Integer getSize();
	
	/**
	 * 通用查询
	 * @param clazz
	 * @param sql
	 * @param agrs
	 * @return List<Map<K, V>>
	 * @throws Exception
	 */
	public List<Map<K, V>> query(Class<T> clazz, String sql, Object... args);

	/**
	 * 根据提供参数来查询(可用等值/模糊查询)
	 * @param params
	 * @return List<Map<K, V>>
	 */
	public List<Map<K, V>> get(List<Map<K, V>> params);

}
