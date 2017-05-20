package com.blog.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.blog.dao.ArticleDAO;

public class ArticleDAOImpl extends ArticleDAO{
	@Override
	public List findByKeyWord(String keyword){
		try{
			String queryString = "from Article as model where model."
					+ "content like \'%"+keyword+"%\' or model.title like \'%"
					+ keyword+"%\' and model.type = 0";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		}catch (RuntimeException re) {
			log.error("find by keword failed", re);
			throw re;
		}
	}
	@Override
	public List myfindByTag(String tag){
		try{
			String queryString = "from Article as model where model."
					+ "tag like \'%"+tag+"%\' and model.type = 0";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		}catch (RuntimeException re) {
			log.error("find by keword failed", re);
			throw re;
		}
	}
	@Override
	public List findbyuseridandtag(int userid,String tag){
		try{
			String queryString = "from Article as model where model."
					+ "tag like \'%"+tag+"%\' and model.uid=\'"+String.valueOf(userid)+"\'";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		}catch (RuntimeException re) {
			log.error("find by userid and tag failed", re);
			throw re;
		}
	}
}
