package com.blog.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.ArticleDAO;
import com.blog.dao.TagDAO;
import com.blog.dao.UserinfoDAO;
import com.blog.dao.ZanhuocaiDAO;
import com.blog.entity.Article;
import com.blog.entity.Tag;
import com.blog.entity.Userinfo;
import com.blog.entity.Zanhuocai;
import com.blog.service.ArticleService;
import com.blog.service.SystemService;
import com.blog.util.HTMLUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport {
	public int aid;//文章id
	public int pageid;
	public int rownums;
	public int userid;
	public int articleid;
	public String tag;
	public String keyword;
	@Autowired
	private UserinfoDAO userdao;
	@Autowired
	private ZanhuocaiDAO zanhuocaidao;
	@Autowired
	private ArticleDAO articledao;
	@Autowired
	private TagDAO tagdao;
	@Autowired
	private ArticleService articleservice;
	@Autowired
	private SystemService systemservice;
	//管理员专用属性
	private Integer uid;
	private String title;
	private String content;
	private Timestamp time;
	private Integer type;
	private Integer looktimes;
	
	public String getTag() {
		return tag;
	}

	public TagDAO getTagdao() {
		return tagdao;
	}

	public void setTagdao(TagDAO tagdao) {
		this.tagdao = tagdao;
	}

	public UserinfoDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserinfoDAO userdao) {
		this.userdao = userdao;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLooktimes() {
		return looktimes;
	}

	public void setLooktimes(Integer looktimes) {
		this.looktimes = looktimes;
	}

	public ZanhuocaiDAO getZanhuocaidao() {
		return zanhuocaidao;
	}

	public void setZanhuocaidao(ZanhuocaiDAO zanhuocaidao) {
		this.zanhuocaidao = zanhuocaidao;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ArticleDAO getArticledao() {
		return articledao;
	}

	public void setArticledao(ArticleDAO articledao) {
		this.articledao = articledao;
	}

	public SystemService getSystemservice() {
		return systemservice;
	}

	public void setSystemservice(SystemService systemservice) {
		this.systemservice = systemservice;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getArticleid() {
		return articleid;
	}

	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

	public int getRownums() {
		return rownums;
	}

	public void setRownums(int rownums) {
		this.rownums = rownums;
	}

	public ArticleService getArticleservice() {
		return articleservice;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}
	/**
	 * 根据pageid和rownums获得文章
	 * @return
	 */
	public String getarticles(){
		try{
			List<Article> articles = articleservice.getAllArticles();
			List<Article> newarticles=new ArrayList<Article>();
			if(pageid<1)pageid=1;
			for(int i=(pageid-1)*rownums;i<pageid*rownums&&i<articles.size();i++){
				int maxlen=100;
				if(articles.get(i).getContent().length()<maxlen){
					maxlen = articles.get(i).getContent().length()-1;
				}
				if(articles.get(i).getType()!=0){
					continue;
				}
				articles.get(i).setContent(articles.get(i).getContent().substring(0,maxlen));
				newarticles.add(articles.get(i));
			}
			String output = JSONUtil.serialize(newarticles);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 获得所有的tags
	 * @return
	 */
	public String gettags(){
		try{
			List<Tag> taglist = systemservice.getAllTag();
			String output = JSONUtil.serialize(taglist);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 获得一个用户的一个tag的博文
	 * @return
	 */
	public String getuserarticlesbytag(){
		try{
			int userid = (int) ActionContext.getContext().getSession().get("userid");
			List<Article> articlelist = articledao.findbyuseridandtag(userid,tag);
			String output = JSONUtil.serialize(articlelist);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 根据tag获得文章
	 * @return
	 */
	public String getarticlesbytag(){
		try{
			List<Article> articlelist = articleservice.getArticlesByTag(tag);
			for(int i=0;i<articlelist.size();i++){
				int maxlen=100;
				if(articlelist.get(i).getContent().length()<maxlen){
					maxlen = articlelist.get(i).getContent().length()-1;
				}
				if(articlelist.get(i).getType()!=0){
					continue;
				}
				articlelist.get(i).setContent(HTMLUtil.ToHTML(articlelist.get(i).getContent().substring(0,maxlen)));
			}
			String output = JSONUtil.serialize(articlelist);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 根据关键字获得文章
	 * @return
	 */
	public String getarticlesbykeyword(){
		try{
			List<Article> articlelist = articleservice.getArticlesByKeyWord(keyword);
			for(int i=0;i<articlelist.size();i++){
				int maxlen=100;
				if(articlelist.get(i).getContent().length()<maxlen){
					maxlen = articlelist.get(i).getContent().length()-1;
				}
				if(articlelist.get(i).getType()!=0){
					i--;
					continue;
				}
				articlelist.get(i).setContent(articlelist.get(i).getContent().substring(0,maxlen));
				articlelist.get(i).setContent(articlelist.get(i).getContent().substring(0,100));
			}
			String output = JSONUtil.serialize(articlelist);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 由一篇文章id获得一篇文章
	 * @return
	 */
	public String getarticlebyid(){
		try{
			Article article = articledao.findById(aid);
			article.setLooktimes(article.getLooktimes()+1);
			articledao.merge(article);
			article.setContent(HTMLUtil.ToHTML(article.getContent()));
			String output = JSONUtil.serialize(article);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 获得一个用户的所有文章
	 * @return
	 */
	public String getarticlebyuserid(){
		try{
			if(userid<=0){
				userid = (int) ActionContext.getContext().getSession().get("userid");
			}
			List<Article> articlelist = articleservice.getArticlesByUserId(userid);
			String output = JSONUtil.serialize(articlelist);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 获取一篇文章的点赞次数
	 * @return
	 */
	public String getdianzan(){
		try{
			List zanhuocailist = zanhuocaidao.findByAid(aid);
			ActionContext.getContext().put("result", String.valueOf(zanhuocailist.size()));
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 对一篇文章点赞
	 * @return
	 */
	public String adddianzan(){
		try{
			Timestamp time=new Timestamp(new Date().getTime());
			Zanhuocai zanhuocai = new Zanhuocai(userid,aid,time,0);
			zanhuocaidao.merge(zanhuocai);
			ActionContext.getContext().put("result", "success");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 管理员专用查看所有文章
	 * @return
	 */
	public String getallarticles(){
		try{
			List<Article> articles = articledao.findAll();
			for(int i=0;i<articles.size();i++){
				articles.get(i).setContent(HTMLUtil.ToHTML(articles.get(i).getContent()));
				
			}
			String output = JSONUtil.serialize(articles);
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 管理员专用添加文章
	 * @return
	 */
	public String addarticle(){
		try{
			Article a = new Article(uid, title, content, time, tag, type, looktimes);
			articledao.merge(a);
			ActionContext.getContext().put("result", "success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 更新文章
	 * @return
	 */
	public String newupdatearticle(){
		try{
			Article a = articledao.findById(aid);
			a.setContent(content);
			a.setLooktimes(looktimes);
			a.setTag(tag);
			a.setTime(time);
			a.setTitle(title);
			a.setTitle(title);
			a.setType(type);
			a.setUid(uid);
			articledao.merge(a);
			ActionContext.getContext().put("result", "success");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 删除文章接口
	 * @return
	 */
	public String delarticle(){
		try{
			int userid = (int) ActionContext.getContext().getSession().get("userid");
			Article a = articledao.findById(aid);
			if(userid==a.getUid()||userid==9999){
				articledao.delete(a);
				ActionContext.getContext().put("result", "删除成功");
			}else{
				ActionContext.getContext().put("result", "权限不足");
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 打赏一篇文章的作者
	 * @return
	 */
	public String dashang(){
		try{
			int userid = (int) ActionContext.getContext().getSession().get("userid");
			if(userid == -1||userid == 0){
				ActionContext.getContext().put("result", "尚未登陆");
				return SUCCESS;
			}
			Userinfo user = userdao.findById(userid);
			if(user.getScore()>=1){
				Userinfo user2 = userdao.findById(articledao.findById(aid).getUid());
				user.setScore(user.getScore()-1);
				user2.setScore(user2.getScore()+1);
				userdao.merge(user2);
				userdao.merge(user);
				ActionContext.getContext().put("result", "打赏成功!");
				return SUCCESS;
			}else{
				ActionContext.getContext().put("result", "您的积分不足,无法打赏!");
				return SUCCESS;
			}
		}catch(Exception e){
			e.printStackTrace();
			ActionContext.getContext().put("result", "打赏失败");
			return ERROR;
		}
	}
	/**
	 * 写一篇博文
	 * @return
	 */
	public String writeoneblog(){
		try{
			int userid = (int) ActionContext.getContext().getSession().get("userid");
			String[] tagarr = tag.split(",");
			Timestamp t=new Timestamp(new Date().getTime());
			for (String temp:tagarr){
				List list = tagdao.findByContent(temp);
				if(list.size()<=0){
					Tag temptag = new Tag(temp, t);
					tagdao.merge(temptag);
				}
			}
			Article a = new Article(userid, title, content, t, tag, 0, 0);
			articledao.merge(a);
			ActionContext.getContext().put("result", "success");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 写一篇草稿
	 * @return
	 */
	public String writeonecaogao(){
		try{
			int userid = (int) ActionContext.getContext().getSession().get("userid");
			String[] tagarr = tag.split(",");
			Timestamp t=new Timestamp(new Date().getTime());
			for (String temp:tagarr){
				List list = tagdao.findByContent(temp);
				if(list.size()<=0){
					Tag temptag = new Tag(temp, t);
					tagdao.merge(temptag);
				}
			}
			Article a = new Article(userid, title, content, t, tag, 1, 0);
			articledao.merge(a);
			ActionContext.getContext().put("result", "success");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
}
