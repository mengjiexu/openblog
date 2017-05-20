package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "article", catalog = "blog")
public class Article implements java.io.Serializable {

	// Fields

	private Integer aid;
	private Integer uid;
	private String title;
	private String content;
	private Timestamp time;
	private String tag;
	private Integer type;
	private Integer looktimes;

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** full constructor */
	public Article(Integer uid, String title, String content, Timestamp time,
			String tag, Integer type, Integer looktimes) {
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.time = time;
		this.tag = tag;
		this.type = type;
		this.looktimes = looktimes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "aid", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Column(name = "uid", nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 65535)
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

	@Column(name = "tag", nullable = false)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "looktimes", nullable = false)
	public Integer getLooktimes() {
		return this.looktimes;
	}

	public void setLooktimes(Integer looktimes) {
		this.looktimes = looktimes;
	}

}