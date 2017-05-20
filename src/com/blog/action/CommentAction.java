package com.blog.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.CommentDAO;
import com.blog.dao.LiuyanorsixingDAO;
import com.blog.entity.Comment;
import com.blog.entity.Liuyanorsixing;
import com.blog.service.CommentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport {
	public int aid;
	public int cid;
	public int userid;
	public String content;
	@Autowired
	private CommentService commentservice;
	@Autowired
	private LiuyanorsixingDAO liuyandao;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LiuyanorsixingDAO getLiuyandao() {
		return liuyandao;
	}
	public void setLiuyandao(LiuyanorsixingDAO liuyandao) {
		this.liuyandao = liuyandao;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public CommentService getCommentservice() {
		return commentservice;
	}
	public void setCommentservice(CommentService commentservice) {
		this.commentservice = commentservice;
	}
	/**
	 * 获得一篇文章的所有评论
	 * @return
	 */
	public String getallcommentbyaid(){
		try{
			List<Comment> commentlist = commentservice.getAllCommentByArticleId(aid);
			String output = JSONUtil.serialize(commentlist);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 添加一个评论
	 * @return
	 */
	public String addonecommentbyaid(){
		try{
			int userid = (int) ActionContext.getContext().getSession().get("userid");
			@SuppressWarnings("unused")
			String result = commentservice.addOneCommentToArticle(userid, aid, content, "");
			ActionContext.getContext().put("result", "success");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 获得一个用户的所有留言
	 */
	public String getliuyanbyuserid(){
		try{
			List<Liuyanorsixing> liuyanlist = liuyandao.findByToid(userid);
			String output = JSONUtil.serialize(liuyanlist);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 添加一个留言
	 * @return
	 */
	public String addoneliuyan(){
		try{
			int fromid = (int) ActionContext.getContext().getSession().get("userid");
			Timestamp time=new Timestamp(new Date().getTime());
			if(userid<=0){
				ActionContext.getContext().put("result", "添加失败");
				return SUCCESS;
			}
			Liuyanorsixing liuyan = new Liuyanorsixing(fromid,userid,time,content,0);
			liuyandao.merge(liuyan);
			ActionContext.getContext().put("result", "添加成功");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
}
