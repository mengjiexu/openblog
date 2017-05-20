package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Fensi entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fensi", catalog = "blog")
public class Fensi implements java.io.Serializable {

	// Fields

	private Integer fid;
	private Integer uid;
	private Integer fensiid;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Fensi() {
	}

	/** full constructor */
	public Fensi(Integer uid, Integer fensiid, Timestamp time) {
		this.uid = uid;
		this.fensiid = fensiid;
		this.time = time;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "fid", unique = true, nullable = false)
	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@Column(name = "uid", nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "fensiid", nullable = false)
	public Integer getFensiid() {
		return this.fensiid;
	}

	public void setFensiid(Integer fensiid) {
		this.fensiid = fensiid;
	}

	@Column(name = "time", nullable = false, length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}