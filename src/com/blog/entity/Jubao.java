package com.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jubao entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "jubao", catalog = "blog")
public class Jubao implements java.io.Serializable {

	// Fields

	private Integer jid;
	private Integer fromuid;
	private Integer touid;
	private String content;
	private Integer ischeck;

	// Constructors

	/** default constructor */
	public Jubao() {
	}

	/** full constructor */
	public Jubao(Integer fromuid, Integer touid, String content, Integer ischeck) {
		this.fromuid = fromuid;
		this.touid = touid;
		this.content = content;
		this.ischeck = ischeck;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "jid", unique = true, nullable = false)
	public Integer getJid() {
		return this.jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
	}

	@Column(name = "fromuid", nullable = false)
	public Integer getFromuid() {
		return this.fromuid;
	}

	public void setFromuid(Integer fromuid) {
		this.fromuid = fromuid;
	}

	@Column(name = "touid", nullable = false)
	public Integer getTouid() {
		return this.touid;
	}

	public void setTouid(Integer touid) {
		this.touid = touid;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "ischeck", nullable = false)
	public Integer getIscheck() {
		return this.ischeck;
	}

	public void setIscheck(Integer ischeck) {
		this.ischeck = ischeck;
	}

}