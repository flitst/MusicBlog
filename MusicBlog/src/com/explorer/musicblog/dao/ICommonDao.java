package com.explorer.musicblog.dao;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.exception.CustomException;

/**
 * zhangzhong 2018年5月28日下午11:53:10 通用方法
 */
public interface ICommonDao<T, K, V> {

	/**
	 * 通用增改
	 * 
	 * @param sql
	 * @param agrs
	 * @return
	 */
	public Integer renew(String sql, Object... args);

	/**
	 * 通用查询
	 * 
	 * @param clazz
	 * @param sql
	 * @param agrs
	 * @return
	 * @throws CustomException
	 */
	public List<Map<K, V>> query(Class<T> clazz, String sql, Object... args) throws Exception;

	/**
	 * 根据提供参数来查询(可用等值/模糊查询)
	 * 
	 * @param params
	 * @return
	 * @throws CustomException
	 */
	public List<Map<K, V>> get(List<Map<K, V>> params);

}
