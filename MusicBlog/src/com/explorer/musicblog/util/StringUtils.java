package com.explorer.musicblog.util;

public class StringUtils {

	/**
	 * 私有化构造方法，不允许外部直接实例化（保护此类不能在外部实例化）
	 */
	private StringUtils() {}
	
	// 给用户调用创建一个唯一实例
	private static StringUtils instance = null;
	
	/**
	 * 获取实例
	 * @return
	 */
	public static synchronized StringUtils getInstance() {
	    if (instance == null) {
	       instance = new StringUtils(); 
	    }
	    return instance;
	}
	
	/**
	 * 字符串不为空且类型为String类型，则返回此字符串，否则返回null
	 * @param str
	 * @return
	 */
	public static String isLegal(String str) {
		if(str != null && str.trim().length() > 0 && !"".equals(str.trim()) && !str.isEmpty() && str instanceof String) {
			return str;
		}
		return null;
	}
	
	/**
	 * 当字符串为null或去除首尾空格后为""，返回true，否则返回false
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		boolean b = false;
		if (str == null || "".equals(str.trim())) {
			b = true;
		}
		return b;
	}
	
	/**
	 * 当字符串不为null或去除首尾空格后不为""，返回true，否则返回false
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	
}
