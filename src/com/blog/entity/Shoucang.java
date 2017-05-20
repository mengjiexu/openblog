package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Shoucang entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shoucang", catalog = "blog")
public class Shoucang implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Integer uid;
	private Integer aid;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Shoucang() {
	}

	/** full constructor */
	public Shoucang(Integer uid, Integer aid, Timestamp time) {
		this.uid = uid;
		this.aid = aid;
		this.time = time;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sid", unique = true, nullable = false)
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "uid", nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

}