package com.blog.service;

import java.util.List;

public interface SystemService {
	/**
	 * 举报一个用户
	 * @param fromuserid
	 * @param touserid
	 * @param content
	 * @return
	 */
	public String jubaoByUserId(int fromuserid,int touserid,String content);
	/**
	 * 获取所有的举报记录
	 * @return
	 */
	public List getAllJuBao();
	/**
	 * 打赏博主
	 * @param fromuserid
	 * @param touserid
	 * @param count
	 * @return
	 */
	public String dashang(int fromuserid,int touserid,int count);
	/**
	 * 获取所有的tag
	 * @return
	 */
	public List getAllTag();
	/**
	 * 添加一个tag
	 * @param tag
	 * @return
	 */
	public String addOneTag(String tag);
	/**
	 * 删除一个tag
	 * @param tag
	 * @return
	 */
	public String delOneTagByTag(String tag);
	/**
	 * 获取所有的敏感词
	 * @return
	 */
	public List getAllMingGanCi();
	/**
	 * 添加一个敏感词
	 * @param mingganci
	 * @return
	 */
	public String addOneMingGanCi(String mingganci);
	/**
	 * 删除一个敏感词
	 * @param mingganci
	 * @return
	 */
	public String delOneMingGanCi(String mingganci);
	
	
	
}
