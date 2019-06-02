package com.aws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cid")
	private int cid;
	
	@Column(name = "cname")
	private String cname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	public int getCid() {
		return this.cid;
	}

	public void setCid(final int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(final String cname) {
		this.cname = cname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
	
	/**
	 * Will Give the Object Information for the current Object
	 */
	@Override
	public String toString() {
		return ("CName = "+cname+"\nEmail = "+email);
	}
}