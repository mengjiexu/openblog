package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mingganci entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mingganci", catalog = "blog")
public class Mingganci implements java.io.Serializable {

	// Fields

	private Integer mid;
	private String content;
	private Timestamp time;
	private Integer uid;

	// Constructors

	/** default constructor */
	public Mingganci() {
	}

	/** full constructor */
	public Mingganci(String content, Timestamp time, Integer uid) {
		this.content = content;
		this.time = time;
		this.uid = uid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mid", unique = true, nullable = false)
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", nullable = false, length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "uid", nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}