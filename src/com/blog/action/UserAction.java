package com.blog.action;

import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.UserinfoDAO;
import com.blog.entity.Userinfo;
import com.blog.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	public String username;
	public String userpass;
	public String useremail;
	public int userid;
	@Autowired
	private UserService userservice;
	@Autowired
	private UserinfoDAO userdao;
	//以下为管理员edatagrid专用属性
	private Integer uid;
	private Integer score;
	private Integer state;
	private Integer flag;
	private String word;
	
	
	public String getUsername() {
		return username;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public UserinfoDAO getUserdao() {
		return userdao;
	}
	public void setUserdao(UserinfoDAO userdao) {
		this.userdao = userdao;
	}
	/**
	 * 登陆接口
	 * 输入(useremail,userpass)
	 * 输入"success"or"error"
	 * @return
	 */
	public String login(){
		try{
			if(userservice.login(useremail, userpass)){
				ActionContext.getContext().getSession().put("useremail", useremail);
				ActionContext.getContext().getSession().put("userid", userdao.findByUseremail(useremail).get(0).getUid());
				ActionContext.getContext().put("result", "success");
			}else{
				ActionContext.getContext().put("result", "error");
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 注册接口
	 */
	public String register(){
		try{
			switch(userservice.register(useremail, userpass, username)){
				case 0:ActionContext.getContext().put("result", "success");break;
				case -1:ActionContext.getContext().put("result", "email has beeen registered");break;
				case -2:ActionContext.getContext().put("result", "internal server error");break;
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 检测email可用性
	 */
	public String checkemail(){
		try{
			if(userservice.checkuseremail(useremail)!=true){
				ActionContext.getContext().put("result", "success");
			}else{
				ActionContext.getContext().put("result", "error");
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 检测用户是否已经登陆
	 * @return
	 */
	public String getuserinfo(){
		try{
			if(ActionContext.getContext().getSession().get("useremail")!=null){
				ActionContext.getContext().put("result", "success");
				return SUCCESS;
			}else{
				return SUCCESS;
			}
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 退出登陆
	 * @return
	 */
	public String logout(){
		try{
			ActionContext.getContext().getSession().remove("useremail");
			ActionContext.getContext().getSession().remove("userid");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 获得用户信息
	 * @return
	 */
	public String getbloggerbyid(){
		try{
			if(userid<=0){
				userid = (int) ActionContext.getContext().getSession().get("userid");
			}
			String output = JSONUtil.serialize(userdao.findById(userid));
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 管理员用获得所有用户信息
	 * @return
	 */
	public String getusers(){
		try{
			String output = JSONUtil.serialize(userdao.findAll());
			ActionContext.getContext().put("result", output);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 管理员用添加用户
	 * @return
	 */
	public String adduser(){
		try{
			Userinfo user = new Userinfo(useremail, username, userpass, score, state, flag, word);
			userdao.merge(user);
			ActionContext.getContext().put("result", "");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 更新用户信息
	 */
	public String updateuser(){
		try{
			Userinfo user = userdao.findById(uid);
			user.setFlag(flag);
			user.setScore(score);
			user.setState(state);
			user.setUseremail(useremail);
			user.setUsername(username);
			user.setUserpass(userpass);
			user.setWord(word);
			userdao.merge(user);
			ActionContext.getContext().put("result", "success");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 管理员用删除用户
	 * @return
	 */
	public String deluser(){
		try{
			Userinfo user = userdao.findById(uid);
			userdao.delete(user);
			ActionContext.getContext().put("result", "success");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
}

