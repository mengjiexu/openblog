package com.blog.service;

public interface UserService {
	/**
	 * 登陆
	 * @param useremail
	 * @param password
	 * @return
	 */
	public boolean login(String useremail,String password);
	/**
	 * 注册
	 * @param useremail
	 * @param userpass
	 * @param username
	 * @return
	 */
	public int register(String useremail,String userpass,String username);
	/**
	 * 检查useremail是否被占用
	 * @param email
	 * @return
	 */
	public boolean checkuseremail(String email);
}
