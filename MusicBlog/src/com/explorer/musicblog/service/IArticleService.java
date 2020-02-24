package com.explorer.musicblog.service;

import java.util.List;

import com.explorer.musicblog.pojo.Article;

public interface IArticleService extends ICommonService<Article, String, Object> {

	/**
	 * 获取用户所有文章
	 * @param id 用户ID
	 * @return 查询的文章
	 */
	List<Article> getUserAllArticle(Integer id);
}
