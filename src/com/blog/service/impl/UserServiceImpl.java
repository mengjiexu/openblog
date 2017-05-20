package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.UserinfoDAO;
import com.blog.entity.Userinfo;
import com.blog.service.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserinfoDAO userdao;
	
	public UserinfoDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserinfoDAO userdao) {
		this.userdao = userdao;
	}

	@Override
	public boolean login(String useremail, String password) {
		try{
			List<Userinfo> users=userdao.findByUseremail(useremail);
			boolean m = users.get(0).getUserpass().equals(password);
			if(users!=null&&users.size()>0&&m){
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int register(String useremail, String userpass, String username) {
		try{
			if(checkuseremail(useremail)!=true){
				Userinfo user=new Userinfo(useremail,username,userpass,0,0,0,"");
				userdao.merge(user);
				return 0;
			}else{
				return -1;//means email has been registered
			}
		}catch(Exception e){
			e.printStackTrace();
			return -2;//means some internal server error
		}
	}

	@Override
	public boolean checkuseremail(String email) {
		try{
			if(userdao.findByUseremail(email).size()>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return true;
		}
	}

}
