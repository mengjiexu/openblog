package com.blog.service;

import java.util.List;

public interface CommentService {
	/**
	 * 添加一个到文章的评论
	 * @param fromuserid
	 * @param articleid
	 * @param content 
	 * @param tag
	 * @return
	 */
	public String addOneCommentToArticle(int fromuserid,int articleid,String content,String tag);
	/**
	 * 添加一个针对评论的评论
	 * @param fromuserid
	 * @param tocommentid
	 * @param articleid
	 * @param content
	 * @param tag
	 * @return
	 */
	public String addOneCommentToComment(int fromuserid,int tocommentid,int articleid,String content,String tag);
	/**
	 * 添加一个针对用户的私信
	 * @param fromuserid
	 * @param touserid
	 * @param content
	 * @return
	 */
	public String addOneCommentToUser(int fromuserid,int touserid,String content);
	/**
	 * 获取一篇文章的所有评论
	 * @param articleid
	 * @return
	 */
	public List getAllCommentByArticleId(int articleid);
	/**
	 * 获取一个用户收到的所有私信
	 * @param userid
	 * @return
	 */
	public List getAllReceivedCommentByUserId(int userid);
	/**
	 * 获取一个用户发出的所有私信
	 * @param userid
	 * @return
	 */
	public List getAllSendCommentByUserId(int userid);
	/**
	 * 关注一个用户（即成为粉丝）
	 * @param fromuserid
	 * @param touserid
	 * @return
	 */
	public String guanZhu(int fromuserid,int touserid);
	/**
	 * 取消关注
	 * @param fromuserid
	 * @param touserid
	 * @return
	 */
	public String cancelGuanZhu(int fromuserid,int touserid);
	/**
	 * 获得一个用户的所有粉丝
	 * @param userid
	 * @return
	 */
	public String getAllFenSi(int userid);
	/**
	 * 获得一个用户关注的所有人
	 * @param userid
	 * @return
	 */
	public String getAllGuanZhu(int userid);
}
