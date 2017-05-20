package com.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userinfo", catalog = "blog")
public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String useremail;
	private String username;
	private String userpass;
	private Integer score;
	private Integer state;
	private Integer flag;
	private String word;

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** full constructor */
	public Userinfo(String useremail, String username, String userpass,
			Integer score, Integer state, Integer flag, String word) {
		this.useremail = useremail;
		this.username = username;
		this.userpass = userpass;
		this.score = score;
		this.state = state;
		this.flag = flag;
		this.word = word;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "uid", unique = true, nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "useremail", nullable = false, length = 50)
	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	@Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "userpass", nullable = false, length = 50)
	public String getUserpass() {
		return this.userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	@Column(name = "score", nullable = false)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "flag", nullable = false)
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name = "word", nullable = false)
	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}