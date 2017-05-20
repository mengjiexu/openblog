package com.blog.service;

import java.util.List;

import com.blog.entity.Article;

public interface ArticleService {
	/**
	 * 获得所有文章
	 * @return
	 */
	public List getAllArticles();
	/**
	 * 获得最新的n篇文章
	 * @return
	 */
	public List getNearestArticles(int n);
	/**
	 * 添加一篇文章
	 * @param userid
	 * @param title
	 * @param content
	 * @param type
	 * @param tag
	 * @return
	 */
	public String addOneArticle(int userid,String title,String content, int type, String tag);
	/**
	 * 删除一个文章
	 * @param articleid
	 * @return
	 */
	public String delOneArticle(int articleid);
	/**
	 * 获得特定文章
	 * @param articleid
	 * @return
	 */
	public Article getOneArticleById(int articleid);
	/**
	 * 搜索关键词文章
	 * @param keyword
	 * @return
	 */
	public List getArticlesByKeyWord(String keyword);
	/**
	 * 通过tag搜索文章
	 * @param tag
	 * @return
	 */
	public List getArticlesByTag(String tag);
	/**
	 * 获得一个用户的所有文章
	 * @param userid
	 * @return
	 */
	public List getArticlesByUserId(int userid);
	/**
	 * 点赞或踩一篇文章
	 * @param userid 点赞用户id
	 * @param articleid
	 * @param type 赞或踩
	 * @return
	 */
	public boolean dianzan(int userid,int articleid,int type);
	/**
	 * 取消点赞一篇文章
	 * @param userid
	 * @param articleid
	 * @param type 赞或踩
	 * @return
	 */
	public boolean quxiaodianzan(int userid,int articleid,int type);
	/**
	 * 收藏一篇文章
	 * @param userid
	 * @param articleid
	 * @return
	 */
	public String shoucangByArticleId(int userid,int articleid);
	/**
	 * 获取一个用户收藏的所有文章
	 * @param userid
	 * @return
	 */
	public List getAllShouCangByUserId(int userid);
	/**
	 * 取消收藏一篇文章
	 * @param userid
	 * @param articleid
	 * @return
	 */
	public String delOneShouCangByArticleId(int userid,int articleid);
	
}
