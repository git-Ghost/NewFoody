package com.aws.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.aws.domain.FOODY_USER_ORDERS;
import com.aws.utility.DbTableNames;
import com.aws.utility.DbUtil;

public class FOODY_USER_ORDERS_DAO extends Exception {

	private static final long serialVersionUID = -1992200230745800141L;
	Logger log = Logger.getLogger(this.getClass());
	private static boolean reflect = true; // To Check the Table Presence
	SessionFactory factory;
	Session session;
	private static FOODY_USER_ORDERS_DAO instance = null;

	/**
	 * Custom Exception For Passing Message
	 * 
	 * @param str
	 *            = Exception Information
	 */
	public FOODY_USER_ORDERS_DAO(String str) {
		super(str);
	}

	/**
	 * Private Constructor to make the object as singleTon Object
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws FOODY_USER_ORDERS_DAO
	 */
	private FOODY_USER_ORDERS_DAO() throws IOException, SQLException, FOODY_USER_ORDERS_DAO {
		factory = DbUtil.getSessionFactory();
		session = factory.openSession();
		if (reflect) {
			reflect=false;
			boolean flag = DbUtil.checkTableInDb(DbTableNames.FOODY_USER_ORDERS);
			if (flag != true) {
				log.warn("!!! " + DbTableNames.FOODY_USER_ORDERS + " TABLE WASN'T FOUND IN SCHEMA !!!\nCreating Table "
						+ DbTableNames.FOODY_USER_ORDERS + " Now...");
				boolean tStatus = DbUtil.createTable(DbTableNames.FOODY_USER_ORDERS_SCRIPT);
				if (tStatus == true)
					log.info(DbTableNames.FOODY_USER_ORDERS + " Table Created Successfull in the Schema");
				else
					throw new FOODY_USER_ORDERS_DAO(
							DbTableNames.FOODY_USER_ORDERS + " Table Creation Failed Throwing New Exception ...");
			}
		}
	}
	/**
	 * New Entry Insertion of Order Into the DB
	 * @param newOrder = Unique Order Id For the Customer
	 */
	public void insertOrderForUser(FOODY_USER_ORDERS newOrder) {
		Transaction trans = null;
		try{
			trans = session.beginTransaction();
			log.info("Inserting Record As --->> "+newOrder.toString());
			session.save(newOrder);
			trans.commit();
		}catch (Exception e) {
			log.fatal("Exception Encountered while insertion of record\n" + e + "\nRecord Info || "
					+ newOrder.toString());
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
	public static FOODY_USER_ORDERS_DAO getInstance() throws IOException, SQLException, FOODY_USER_ORDERS_DAO {
		if (instance == null)
			return new FOODY_USER_ORDERS_DAO();
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
