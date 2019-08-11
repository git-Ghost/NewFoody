package com.aws.domain;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "FOODY_STARBUCKS_MENU")
public class FOODY_STARBUCKS_MENU {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ITEM_ID")
	private int ITEM_ID;
	
	@Column(name = "ITEM_NAME")
	private String ITEM_NAME;
	
	@Column(name = "ITEM_SRC")
	private String ITEM_SRC;
	
	@Column(name = "CURRENCY_CODE")
	private String CURRENCY_CODE;
	
	@Column(name="ITEM_PRICE")
	private float ITEM_PRICE;

	/**
	 * Will Give the Object Information for the current Object
	 */
	@Override
	public String toString() {
		return ("\nITEM_ID = "+ITEM_ID+"\nITEM_NAME = "+ITEM_NAME+"\nITEM_SRC = "+ITEM_SRC+"\nCURRENCY_CODE = "+CURRENCY_CODE
				+"\nITEM_PRICE = "+ITEM_PRICE);
	}
	
	/** Returns true if URL is valid  */
    public void isValid() 
    { 
		Logger log = Logger.getLogger(FOODY_STARBUCKS_MENU.class);
		try {
			HttpURLConnection huc = (HttpURLConnection) new URL(ITEM_SRC).openConnection();
			huc.setRequestMethod("GET");
			huc.connect();
			int code = huc.getResponseCode();
			if (code == 200)
				log.info("IMAGE URL For the ITEM_NAME " + ITEM_NAME + " is working fine");
			else {
				log.fatal("!!! IMAGE URL for the ITEM_NAME " + ITEM_NAME
						+ " is not working. Kindly, Change the URL --> " + ITEM_SRC);
			}
		} catch (Exception e) {
			log.fatal("!!! IMAGE URL for the ITEM_NAME " + ITEM_NAME + " is not working. Kindly, Change the URL --> "
					+ ITEM_SRC);
		}
    } 

	public int getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(int iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getITEM_NAME() {
		return ITEM_NAME;
	}

	public void setITEM_NAME(String iTEM_NAME) {
		ITEM_NAME = iTEM_NAME;
	}

	public String getITEM_SRC() {
		return ITEM_SRC;
	}

	public void setITEM_SRC(String iTEM_SRC) {
		ITEM_SRC = iTEM_SRC;
	}

	public String getCURRENCY_CODE() {
		return CURRENCY_CODE;
	}

	public void setCURRENCY_CODE(String cURRENCY_CODE) {
		CURRENCY_CODE = cURRENCY_CODE;
	}

	public float getITEM_PRICE() {
		return ITEM_PRICE;
	}

	public void setITEM_PRICE(float iTEM_PRICE) {
		ITEM_PRICE = iTEM_PRICE;
	}

}
