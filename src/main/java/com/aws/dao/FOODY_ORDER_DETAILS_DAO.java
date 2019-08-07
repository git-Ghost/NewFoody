package com.aws.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.aws.domain.FOODY_ORDER_DETAILS;
import com.aws.domain.FOODY_USER_ORDERS;
import com.aws.utility.DbTableNames;
import com.aws.utility.DbUtil;

public class FOODY_ORDER_DETAILS_DAO extends Exception{

	private static final long serialVersionUID = -1992200230745800141L;
	Logger log = Logger.getLogger(this.getClass());
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
	 * @throws FOODY_ORDER_DETAILS_DAO
	 */
	private FOODY_ORDER_DETAILS_DAO() throws IOException, SQLException, FOODY_ORDER_DETAILS_DAO  {
		factory = DbUtil.getSessionFactory();
		session = factory.openSession();
		
		boolean flag = DbUtil.checkTableInDb(DbTableNames.FOODY_ORDER_DETAILS);
		if (flag == false) {
			log.warn("!!! " + DbTableNames.FOODY_ORDER_DETAILS + " TABLE WASN'T FOUND IN SCHEMA !!!\nCreating Table "
					+ DbTableNames.FOODY_ORDER_DETAILS + " Now...");
			boolean tStatus = DbUtil.createTable(DbTableNames.FOODY_ORDER_DETAILS_SCRIPT);
			if (tStatus == true)
				log.info(DbTableNames.FOODY_ORDER_DETAILS + " Table Created Successfull in the Schema");
			else
				throw new FOODY_ORDER_DETAILS_DAO(
						DbTableNames.FOODY_ORDER_DETAILS + " Table Creation Failed Throwing New Exception ...");
		}
	}
	/**
	 * New Entry Insertion of Order Into the DB
	 * @param newOrder = Unique Order Id For the Customer
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FOODY_USER_ORDERS_DAO 
	 */
	public void insertDetailsForOrderID(FOODY_ORDER_DETAILS newOrderDetails) throws IOException, SQLException, FOODY_USER_ORDERS_DAO {
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
	
	@SuppressWarnings({"deprecation","unchecked"})
	public List<FOODY_ORDER_DETAILS> getOrderDetails(FOODY_USER_ORDERS uniqueOrder){
		Criteria criteria = session.createCriteria(FOODY_ORDER_DETAILS.class);
		criteria.add(Restrictions.eq("order_id", uniqueOrder));
		List<FOODY_ORDER_DETAILS> orderDetails = (List<FOODY_ORDER_DETAILS>)(List<?>)criteria.list();
		return orderDetails;
	}
	
	/**
	 * Public getInstance method to get Object for the Class
	 * 
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws FOODY_USER_ORDERS_DAO
	 * @throws FOODY_ORDER_DETAILS_DAO 
	 */
	public static FOODY_ORDER_DETAILS_DAO getInstance() throws IOException, SQLException, FOODY_ORDER_DETAILS_DAO {
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
