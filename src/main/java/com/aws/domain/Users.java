package com.aws.domain;

public class Users {
	private int cid;
	private String cname;
	private String email;
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
		return ("User Details as --->\nCName = "+cname+"\nEmail = "+email+"\nPassword = "+password);
	}
}