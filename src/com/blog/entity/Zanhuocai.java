package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Zanhuocai entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "zanhuocai", catalog = "blog")
public class Zanhuocai implements java.io.Serializable {

	// Fields

	private Integer zid;
	private Integer uid;
	private Integer aid;
	private Timestamp time;
	private Integer type;

	// Constructors

	/** default constructor */
	public Zanhuocai() {
	}

	/** full constructor */
	public Zanhuocai(Integer uid, Integer aid, Timestamp time, Integer type) {
		this.uid = uid;
		this.aid = aid;
		this.time = time;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "zid", unique = true, nullable = false)
	public Integer getZid() {
		return this.zid;
	}

	public void setZid(Integer zid) {
		this.zid = zid;
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

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}