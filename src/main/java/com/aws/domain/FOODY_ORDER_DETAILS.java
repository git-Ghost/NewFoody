package com.aws.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FOODY_ORDER_DETAILS")
public class FOODY_ORDER_DETAILS {
	
	@Id
	@ManyToOne(cascade=(CascadeType.PERSIST))
	@JoinColumn(name="ORDER_ID")
	private FOODY_USER_ORDERS order_id;
	
	@Column(name = "ORDERED_ITEM")
	private String order_item;
	
	@Column(name = "ORDERED_QUANTITY")
	private int quantity;
	
	@Column(name = "ITEM_UNIT_PRICE")
	private float item_unit_price;
	
	public FOODY_ORDER_DETAILS() {}
	
	/**
	 * To Init the Order Details Object
	 * @param order
	 * @param order_item
	 * @param quantity
	 * @param item_unit_price
	 */
	public FOODY_ORDER_DETAILS(FOODY_USER_ORDERS order, String order_item , int quantity , float item_unit_price) {
		this.order_id = order;
		this.order_item = order_item;
		this.quantity = quantity;
		this.item_unit_price = item_unit_price;
	}
	
	public FOODY_USER_ORDERS getOrder_id() {
		return order_id;
	}
	public void setOrder_id(FOODY_USER_ORDERS order_id) {
		this.order_id = order_id;
	}
	public String getOrder_item() {
		return order_item;
	}
	public void setOrder_item(String order_item) {
		this.order_item = order_item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getItem_unit_price() {
		return item_unit_price;
	}
	public void setItem_unit_price(float item_unit_price) {
		this.item_unit_price = item_unit_price;
	}
	@Override
	public String toString() {
		return "FOODY_ORDER_DETAILS [order_id=" + order_id + ", order_item=" + order_item + ", quantity=" + quantity
				+ ", item_unit_price=" + item_unit_price + "]";
	}
}
