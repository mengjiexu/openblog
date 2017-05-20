package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.dao.TagDAO;
import com.blog.service.SystemService;

public class SystemServiceImpl implements SystemService{
	@Autowired
	private TagDAO tagdao;
	
	public TagDAO getTagdao() {
		return tagdao;
	}

	public void setTagdao(TagDAO tagdao) {
		this.tagdao = tagdao;
	}

	@Override
	public String jubaoByUserId(int fromuserid, int touserid, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllJuBao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dashang(int fromuserid, int touserid, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllTag() {
		try{
			return tagdao.findAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String addOneTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delOneTagByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllMingGanCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addOneMingGanCi(String mingganci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delOneMingGanCi(String mingganci) {
		// TODO Auto-generated method stub
		return null;
	}

}
