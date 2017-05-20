package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Readhistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "readhistory", catalog = "blog")
public class Readhistory implements java.io.Serializable {

	// Fields

	private Integer hid;
	private Integer uid;
	private Integer aid;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Readhistory() {
	}

	/** full constructor */
	public Readhistory(Integer uid, Integer aid, Timestamp time) {
		this.uid = uid;
		this.aid = aid;
		this.time = time;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "hid", unique = true, nullable = false)
	public Integer getHid() {
		return this.hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
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