package com.aws.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.aws.domain.FOODY_ORDER_DETAILS;
import com.aws.utility.DbTableNames;
import com.aws.utility.DbUtil;

public class FOODY_ORDER_DETAILS_DAO extends Exception{

	private static final long serialVersionUID = -1992200230745800141L;
	Logger log = Logger.getLogger(this.getClass());
	private static boolean reflect = true; // To Check the Table Presence
	SessionFactory factory;
	Session session;
	private static FOODY_ORDER_DETAILS_DAO instance = null;

	/**
	 * Custom Exception For Passing Message
	 * 
	 * @param str
	 *            = Exception Information
	 */
	public FOODY_ORDER_DETAILS_DAO(String str) {
		super(str);
	}

	/**
	 * Private Constructor to make the object as singleTon Object
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws FOODY_USER_ORDERS_DAO
	 */
	private FOODY_ORDER_DETAILS_DAO() throws IOException, SQLException, FOODY_USER_ORDERS_DAO {
		factory = DbUtil.getSessionFactory();
		session = factory.openSession();
		if (reflect) {
			reflect=false;
			boolean flag = DbUtil.checkTableInDb(DbTableNames.FOODY_ORDER_DETAILS);
			if (flag != true) {
				log.warn("!!! " + DbTableNames.FOODY_ORDER_DETAILS + " TABLE WASN'T FOUND IN SCHEMA !!!\nCreating Table "
						+ DbTableNames.FOODY_ORDER_DETAILS + " Now...");
				boolean tStatus = DbUtil.createTable(DbTableNames.FOODY_ORDER_DETAILS_SCRIPT);
				if (tStatus == true)
					log.info(DbTableNames.FOODY_ORDER_DETAILS + " Table Created Successfull in the Schema");
				else
					throw new FOODY_USER_ORDERS_DAO(
							DbTableNames.FOODY_ORDER_DETAILS + " Table Creation Failed Throwing New Exception ...");
			}
		}
	}
	/**
	 * New Entry Insertion of Order Into the DB
	 * @param newOrder = Unique Order Id For the Customer
	 */
	public void insertDetailsForOrderID(FOODY_ORDER_DETAILS newOrderDetails) {
		Transaction trans = null;
		try{
			trans = session.beginTransaction();
			log.info("Inserting Record As --->> "+newOrderDetails.toString());
			session.save(newOrderDetails);
			trans.commit();
		}catch (Exception e) {
			log.fatal("Exception Encountered while insertion of record\n" + e + "\nRecord Info || "
					+ newOrderDetails.toString());
			trans.rollback();
			throw e;
		}
	}
	
	/**
	 * Public getInstance method to get Object for the Class
	 * 
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws FOODY_USER_ORDERS_DAO
	 */
	public static FOODY_ORDER_DETAILS_DAO getInstance() throws IOException, SQLException, FOODY_USER_ORDERS_DAO {
		if (instance == null)
			return new FOODY_ORDER_DETAILS_DAO();
		else
			return instance;
	}
	/**
	 * Closing all the connections object
	 */
	public void destory() {
		session.close();
		factory.close();
	}
}
