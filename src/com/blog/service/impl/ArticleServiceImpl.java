package com.blog.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.ArticleDAO;
import com.blog.dao.ShoucangDAO;
import com.blog.dao.ZanhuocaiDAO;
import com.blog.entity.Article;
import com.blog.entity.Shoucang;
import com.blog.entity.Zanhuocai;
import com.blog.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDAO articledao;
	@Autowired
	private ZanhuocaiDAO zanhuocaidao;
	@Autowired
	private ShoucangDAO shoucangdao;
	
	public ShoucangDAO getShoucangdao() {
		return shoucangdao;
	}

	public void setShoucangdao(ShoucangDAO shoucangdao) {
		this.shoucangdao = shoucangdao;
	}

	public ArticleDAO getArticledao() {
		return articledao;
	}

	public void setArticledao(ArticleDAO articledao) {
		this.articledao = articledao;
	}

	public ZanhuocaiDAO getZanhuocaidao() {
		return zanhuocaidao;
	}

	public void setZanhuocaidao(ZanhuocaiDAO zanhuocaidao) {
		this.zanhuocaidao = zanhuocaidao;
	}

	@Override
	public List getAllArticles() {
		return articledao.findAll();
	}

	@Override
	public List getNearestArticles(int n) {
		try{
			List<Article> list = articledao.findAll();
			List<Article> output = new ArrayList<Article>();
			for(int i=0;i<n;i++){
				output.add(list.get(i));
			}
			return output;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String addOneArticle(int userid,String title,String content, int type, String tag) {
		try{
			Timestamp t=new Timestamp(new Date().getTime());
			Article article = new Article(userid, title, content,t , tag,type,0);
			articledao.merge(article);
			return "success";
		}catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public String delOneArticle(int articleid) {
		try{
			Article a = articledao.findById(articleid);
			articledao.delete(a);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public Article getOneArticleById(int articleid) {
		try{
			return articledao.findById(articleid);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List getArticlesByKeyWord(String keyword) {
		return articledao.findByKeyWord(keyword);
	}

	@Override
	public List getArticlesByTag(String tag) {
		return articledao.myfindByTag(tag);
	}

	@Override
	public List getArticlesByUserId(int userid) {
		try{
			return articledao.findByUid(userid);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean dianzan(int userid, int articleid, int type) {
		try{
			Zanhuocai zanhuocai = new Zanhuocai();
			zanhuocai.setAid(articleid);
			zanhuocai.setUid(userid);
			zanhuocai.setType(type);
			Timestamp time=new Timestamp(new Date().getTime());
			zanhuocai.setTime(time);
			zanhuocaidao.merge(zanhuocai);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean quxiaodianzan(int userid, int articleid, int type) {
		try{
			List<Zanhuocai> z = zanhuocaidao.findByUidAndArticleId(userid, articleid);
			for(Zanhuocai temp:z){
				if(temp.getType()==type){
					zanhuocaidao.delete(temp);
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public String shoucangByArticleId(int userid, int articleid) {
		try{
			Timestamp time=new Timestamp(new Date().getTime());
			Shoucang s = new Shoucang(userid, articleid, time);
			shoucangdao.merge(s);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public List getAllShouCangByUserId(int userid) {
		try{
			return shoucangdao.findByUid(userid);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String delOneShouCangByArticleId(int userid, int articleid) {
		try{
			List<Shoucang> list = shoucangdao.findByAid(articleid);
			for(Shoucang temp : list){
				if(temp.getUid()==userid){
					shoucangdao.delete(temp);
				}
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

}
