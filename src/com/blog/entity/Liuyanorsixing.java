package com.blog.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Liuyanorsixing entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "liuyanorsixing", catalog = "blog")
public class Liuyanorsixing implements java.io.Serializable {

	// Fields

	private Integer lid;
	private Integer fromid;
	private Integer toid;
	private Timestamp time;
	private String content;
	private Integer type;

	// Constructors

	/** default constructor */
	public Liuyanorsixing() {
	}

	/** full constructor */
	public Liuyanorsixing(Integer fromid, Integer toid, Timestamp time,
			String content, Integer type) {
		this.fromid = fromid;
		this.toid = toid;
		this.time = time;
		this.content = content;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "lid", unique = true, nullable = false)
	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
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

	@Column(name = "time", nullable = false, length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}