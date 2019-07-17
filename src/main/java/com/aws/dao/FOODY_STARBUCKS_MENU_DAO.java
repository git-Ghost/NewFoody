package com.aws.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.aws.domain.FOODY_STARBUCKS_MENU;
import com.aws.utility.DbTableNames;
import com.aws.utility.DbUtil;

public class FOODY_STARBUCKS_MENU_DAO{

	Logger log = Logger.getLogger(FOODY_STARBUCKS_MENU_DAO.class);
	private boolean flag = true;
	private static 	List<FOODY_STARBUCKS_MENU> starMenu;
	private static FOODY_STARBUCKS_MENU_DAO starOptions=null;
	/**
	 * Constructor to create object which will check if table is present or not
	 * if table is not found then it will execute the script and fill up the data
	 */
	private FOODY_STARBUCKS_MENU_DAO(){
		boolean flag = DbUtil.checkTableInDb(DbTableNames.FOODY_STARBUCKS_MENU);
		if(flag!=true) {
			try {
				DbUtil.createTable(DbTableNames.FOODY_STARBUCKS_MENU_SCRIPT);
			}catch (IOException | SQLException e) {
				log.error("Exception In Creation of STARBUCKS Table ",e);
				e.printStackTrace();
			}
		}
		else
			log.info(this.getClass().getSimpleName()+" Table Found...");
	}
	
	/**
	 * Single Instance DAO Object For the FOODY MENU
	 * @return FOODY_STARBUCKS_MENU_DAO = New Object For the same class
	 */
	public static FOODY_STARBUCKS_MENU_DAO getInstance() {
		if(starOptions==null) {
			starOptions = new FOODY_STARBUCKS_MENU_DAO();
		}
		return starOptions;
	}
	
	/**
	 * Gets all the Table Content
	 * @return List<FOODY_STARBUCKS_MENU> = Full Data Present inside the DB
	 */
	@SuppressWarnings("unchecked")
	public List<FOODY_STARBUCKS_MENU> getContent() {
		Session session = null;
		if (starMenu == null) {
			try {
				session = DbUtil.getSessionFactory().openSession();
				starMenu = session.createQuery("FROM " + DbTableNames.FOODY_STARBUCKS_MENU).list();
				if (flag) {
					flag = false;
					log.warn("Checking For Image Link Validation In " + DbTableNames.FOODY_STARBUCKS_MENU + " Data");
					for (FOODY_STARBUCKS_MENU temp : starMenu) {
						temp.isValid();
					}
				} else {
					log.info("Already Checking for the URL. Hence skipping the process");
				}
			} finally {
				session.close();
			}
		}
		return starMenu;
	}
}
