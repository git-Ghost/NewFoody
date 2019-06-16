package com.aws.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.aws.domain.FOODY_KFC_MENU;
import com.aws.utility.DbTableNames;
import com.aws.utility.DbUtil;

public class FOODY_KFC_MENU_DAO{
	
	Logger log = Logger.getLogger(FOODY_KFC_MENU_DAO.class);
	private boolean flag = true;
	private List<FOODY_KFC_MENU> kfcMenu = null;
	private static FOODY_KFC_MENU_DAO menuOptions=null;
	/**
	 * Constructor to create object which will check if table is present or not
	 * if table is not found then it will execute the script and fill up the data
	 */
	private FOODY_KFC_MENU_DAO(){
		boolean flag = DbUtil.checkTableInDb(DbTableNames.FOODY_KFC_MENU);
		if(flag!=true) {
			try {
				DbUtil.createTable(DbTableNames.FOODY_KFC_MENU_SCRIPT);
			}catch (IOException | SQLException e) {
				log.fatal(e);
				e.printStackTrace();
			}
		}
		else
			log.info(this.getClass().getSimpleName()+" Table Found...");
	}
	
	/**
	 * Single Instance DAO Object For the FOODY MENU
	 * @return FOODY_KFC_MENU_DAO = New Object For the same class
	 */
	public static synchronized FOODY_KFC_MENU_DAO getInstance() {
		if(menuOptions == null) {
			menuOptions = new FOODY_KFC_MENU_DAO();
		}
		return menuOptions;
	}
	
	/**
	 * Gets all the Table Content
	 * @return List<FOODY_KFC_MENU> = Full Data Present inside the DB
	 */
	@SuppressWarnings("unchecked")
	public List<FOODY_KFC_MENU> getContent(){
		Session session = null;
		if (kfcMenu == null) {
			try {
				session = DbUtil.getSessionFactory().openSession();
				kfcMenu = session.createQuery("FROM " + DbTableNames.FOODY_KFC_MENU).list();
				if (flag) {
					flag = false;
					log.warn("Checking For Image Link Validation In " + DbTableNames.FOODY_KFC_MENU + " Data");
					for (FOODY_KFC_MENU temp : kfcMenu) {
						temp.isValid();
					}
				} else
					log.info("Already Checking for the URL. Hence skipping the process");
			} finally {
				session.close();
			}
		}
		return kfcMenu;
	}
}
