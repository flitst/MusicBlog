package com.explorer.musicblog.service.impl;

import java.util.List;
import java.util.Map;

import com.explorer.musicblog.dao.IArticleDao;
import com.explorer.musicblog.dao.impl.DaoFactory;
import com.explorer.musicblog.exception.CustomException;
import com.explorer.musicblog.pojo.Article;
import com.explorer.musicblog.service.IArticleService;

public class ArticleServiceImpl implements IArticleService {

	IArticleDao articleDao = DaoFactory.getArticleDao();

	@Override
	public List<Article> getByName(String name) {
		return articleDao.getByName(name);
	}
	
	@Override
	public Integer insert(Article type) {
		return articleDao.insert(type);
	}

	@Override
	public Integer update(Article type) {
		return articleDao.update(type);
	}

	@Override
	public Integer delete(Article type) {
		return articleDao.delete(type);
	}

	@Override
	public Integer commonCUD(String sql, Object... args) throws Exception {
		return articleDao.renew(sql, args);
	}

	@Override
	public Integer getSize() throws Exception {
		return articleDao.getSize();
	}
	
	@Override
	public List<Article> getAll() {
		return articleDao.getAll();
	}

	@Override
	public List<Map<String, Object>> commonQuery(Class<Article> clazz, String sql, Object... args)
			throws CustomException, Exception {
		return articleDao.query(clazz, sql, args);
	}

	@Override
	public List<Map<String, Object>> get(List<Map<String, Object>> params) throws CustomException, Exception {
		return articleDao.get(params);
	}

	@Override
	public Article getById(Integer id) throws Exception {
		return articleDao.getById(id);
	}

	@Override
	public Integer delete(Integer id) throws Exception {
		return articleDao.delete(id);
	}

	@Override
	public List<Article> getUserAllArticle(Integer id) {
		return articleDao.getUserAllArticle(id);
	}

}
