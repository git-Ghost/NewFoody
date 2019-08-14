package com.aws.domain;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aws.utility.FoodyProp;

@Entity
@Table(name = "FOODY_USER_ORDERS")
public class FOODY_USER_ORDERS {
	
	@Id
	@Column(name = "ORDER_ID")
	private String order_id;
	
	@Column(name = "CURRENCY_CODE")
	private String curCode;
	
	@ManyToOne(cascade=(CascadeType.PERSIST))
	@JoinColumn(name="CID")
	private FOODY_USERS user;
	
	@Column(name = "ORDER_DATE")
	private Date date;
	
	@Column(name = "ORDER_TIME")
	private Time time;
	
	@Column(name = "ORDER_AMOUT")
	private float sumAmt;
	
	/**
	 * Zero-@param Constructor for creation of object while fetching from the database 
	 */
	public FOODY_USER_ORDERS() { }
	
	/**
	 * This Order Constructor is used during initiating during object setup 
	 * @param order = Unique Order ID For the Order
	 * @param id = Customer Id Fetched from the database
	 * @param code = Currency Code for the Transaction in question
	 * @param amt = Total Amount For the particular Order
	 * @throws IOException 
	 */
	public FOODY_USER_ORDERS(String order,FOODY_USERS user,String code,float amt) throws IOException { 
		this.order_id = order;
		this.user = user;
		this.curCode = code;
		this.sumAmt = amt;
		this.setDateAndTime();
	}
	
	/**
	 * To set the Current Date and Time For the Object
	 * @throws IOException 
	 */
	public void setDateAndTime() throws IOException{
		this.date = FoodyProp.getConfiguredDate();
		this.time = FoodyProp.getConfiguredTime();
	}
	
	/**
	 * Message information for the object 
	 */
	public String toString() {
		String str = "Order Id --> " + order_id + " is Assigned for [" + user.toString() + "] with Amount of " + curCode + " "
				+ sumAmt + " at instance of " + date + " : " + time;
		return str;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCurCode() {
		return curCode;
	}

	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}

	public FOODY_USERS getCid() {
		return user;
	}

	public float getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(float sumAmt) {
		this.sumAmt = sumAmt;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}
}
