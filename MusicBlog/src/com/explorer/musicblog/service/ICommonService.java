package com.explorer.musicblog.service;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.exception.CustomException;

/**
 * zhangzhong Nov 6, 2019 7:41:46 AM 通用接口
 */
public interface ICommonService<T, K, V> {

	/**
	 * 	获取所有
	 * @return
	 * @throws CustomException
	 */
	List<T> getAll();
	
	/**
	 * 	新增
	 * @param type
	 * @return
	 */
	public Integer insert(T type);
	
	/**
	 * 	修改
	 * @param type
	 * @return
	 */
	public Integer update(T type);
	
	/**
	 *	 删除
	 * @param type
	 * @return
	 */
	public Integer delete(T type);	
	
	/**
	 * 通用新增/删除/修改
	 * 
	 * @return
	 */
	public Integer commonCUD(String sql, Object... args) throws Exception;

	/**
	 * 通用查询
	 * 
	 * @param clazz
	 * @param sql
	 * @param agrs
	 * @return
	 * @throws CustomException
	 * @throws Exception 
	 */
	public List<Map<K, V>> commonQuery(Class<T> clazz, String sql, Object... args) throws CustomException, Exception;

	/**
	 * 根据提供参数来查询(可用等值/模糊查询)
	 * 
	 * @param params
	 * @return
	 * @throws CustomException
	 * @throws Exception 
	 */
	public List<Map<K, V>> get(List<Map<K, V>> params) throws CustomException, Exception;
}
