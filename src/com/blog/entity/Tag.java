package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tag", catalog = "blog")
public class Tag implements java.io.Serializable {

	// Fields

	private Integer tid;
	private String content;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Tag() {
	}

	/** full constructor */
	public Tag(String content, Timestamp time) {
		this.content = content;
		this.time = time;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tid", unique = true, nullable = false)
	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
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

}