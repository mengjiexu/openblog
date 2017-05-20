package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment", catalog = "blog")
public class Comment implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String content;
	private Integer fromid;
	private Integer toid;
	private Integer aid;
	private Timestamp time;
	private String tag;
	private Integer ischeck;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** full constructor */
	public Comment(String content, Integer fromid, Integer toid, Integer aid,
			Timestamp time, String tag, Integer ischeck) {
		this.content = content;
		this.fromid = fromid;
		this.toid = toid;
		this.aid = aid;
		this.time = time;
		this.tag = tag;
		this.ischeck = ischeck;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cid", unique = true, nullable = false)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "fromid", nullable = false)
	public Integer getFromid() {
		return this.fromid;
	}

	public void setFromid(Integer fromid) {
		this.fromid = fromid;
	}

	@Column(name = "toid", nullable = false)
	public Integer getToid() {
		return this.toid;
	}

	public void setToid(Integer toid) {
		this.toid = toid;
	}

	@Column(name = "aid", nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Column(name = "time", nullable = false, length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "tag", nullable = false)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "ischeck", nullable = false)
	public Integer getIscheck() {
		return this.ischeck;
	}

	public void setIscheck(Integer ischeck) {
		this.ischeck = ischeck;
	}

}