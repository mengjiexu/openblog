package com.blog.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.CommentDAO;
import com.blog.dao.FensiDAO;
import com.blog.dao.LiuyanorsixingDAO;
import com.blog.entity.Comment;
import com.blog.entity.Liuyanorsixing;
import com.blog.service.CommentService;

public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAO commentdao;
	@Autowired
	private FensiDAO fensidao;
	@Autowired
	private LiuyanorsixingDAO lsdao;
	
	public LiuyanorsixingDAO getLsdao() {
		return lsdao;
	}

	public void setLsdao(LiuyanorsixingDAO lsdao) {
		this.lsdao = lsdao;
	}

	public CommentDAO getCommentdao() {
		return commentdao;
	}

	public void setCommentdao(CommentDAO commentdao) {
		this.commentdao = commentdao;
	}

	public FensiDAO getFensidao() {
		return fensidao;
	}

	public void setFensidao(FensiDAO fensidao) {
		this.fensidao = fensidao;
	}

	@Override
	public String addOneCommentToArticle(int fromuserid, int articleid,
			String content, String tag) {
		try{
			Timestamp time=new Timestamp(new Date().getTime());
			Comment c = new Comment(content, fromuserid, -1, articleid, time, tag, 0);
			commentdao.merge(c);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String addOneCommentToComment(int fromuserid, int tocommentid,
			int articleid, String content, String tag) {
		try{
			Timestamp time=new Timestamp(new Date().getTime());
			commentdao.merge(new Comment(content,fromuserid,tocommentid,articleid,time,tag,0));
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String addOneCommentToUser(int fromuserid, int touserid,
			String content) {
		try{
			Timestamp time=new Timestamp(new Date().getTime());
			lsdao.merge(new Liuyanorsixing(fromuserid, touserid, time, content, 0));
			return "success";
		}catch(Exception e){
			return "error";
		}
	}

	@Override
	public List getAllCommentByArticleId(int articleid) {
		try{
			return commentdao.findByAid(articleid);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List getAllReceivedCommentByUserId(int userid) {
		try{
			return lsdao.findByToid(userid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List getAllSendCommentByUserId(int userid) {
		try{
			return lsdao.findByFromid(userid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String guanZhu(int fromuserid, int touserid) {
		return null;
	}

	@Override
	public String cancelGuanZhu(int fromuserid, int touserid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllFenSi(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllGuanZhu(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
