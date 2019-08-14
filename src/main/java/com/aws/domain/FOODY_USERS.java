package com.aws.domain;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aws.utility.FoodyProp;

@Entity
@Table(name = "FOODY_USERS")
public class FOODY_USERS {
	
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

	@Column(name = "address")
	private String address;
	
	@Column(name = "doj")
	private Date doj;
	
	/**
	 * Object Created with Date Of Joining as Today
	 * @throws IOException 
	 */
	public FOODY_USERS() throws IOException {
		this.doj = FoodyProp.getConfiguredDate();
	}
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}
	

	@Override
	public String toString() {
		return "FOODY_USERS [ cname=" + cname + ", email=" + email + ", address=" + address + ", doj="
				+ doj + "]";
	}
}