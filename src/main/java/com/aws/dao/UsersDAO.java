package com.aws.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.aws.domain.Users;
import com.aws.utility.DbUtil;

public class UsersDAO extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(this.getClass());
	ReentrantLock threadLock = new ReentrantLock();
	
	/**
	 * Zero-Param Constructor to enable to create object
	 */
	public UsersDAO() {}
	
	/**
	 * Handling Custom Exception in the Hibernate Object Creation
	 * @param str = Exception information
	 */
	public UsersDAO(String str) {
		super(str);
	}
	
	/**
	 * This method will insert new entry to Users table in DB
	 * 
	 * @param newUser
	 *            = Users Object
	 * @return boolean true = if record insertion is successful otherwise false
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public boolean createUser(Users newUser) throws IOException, SQLException ,UsersDAO {
		boolean flag = false, dbCheck;
		SessionFactory factory = DbUtil.getSessionFactory();
		Session session = factory.openSession();
		int reTry = 0;

		log.info("Trying For ReentrantLock for user creation !!!");
		// Getting Outer Lock
		boolean ans = threadLock.tryLock();
		if (ans) {
			Transaction trans = null;
			log.info("##### Checking User Table in DB Schema #####");
			dbCheck = DbUtil.checkTableInDb("Users");
			if (dbCheck != true) {
				log.warn("!!! 'USERS' TABLE WASN'T FOUND IN SCHEMA !!!\nCreating Table 'USERS' Now...");
				boolean tStatus = DbUtil.createTable("/USERS_TABLE_SCRIPT/createTable.sql");
				if(tStatus == true)
					log.info("User Table Created Successfull in the Schema");
				else 
					throw new UsersDAO("User Table Creation Failed Throwing New Exception ...");
			}
			log.info("Opening Session for saving the Users Object Now...");
			try {
				trans = session.beginTransaction();
				session.save(newUser);
				trans.commit();
				log.info("-------------- Record Inserted Succesfully --------------\n" + newUser.toString());
				log.info("------------------------------------------");
				flag = true;

			} catch (Exception e) {
				log.fatal("Exception Encountered while insertion of record\n" + e.getStackTrace() + "\nRecord Info || "
						+ newUser.toString());
				trans.rollback();
				throw new UsersDAO(e.getStackTrace().toString());
			} finally {
				log.warn("Terminating Session now...");
				session.close();
				factory.close();
				log.info("Released From ReentrantLock for user creation !!!");
				threadLock.unlock();
			}
		} else if(reTry<=3){
			log.warn("Can't lock this method now || "+new Throwable() 
                    .getStackTrace()[0] 
                    .getMethodName());
			log.info("Retry Count for Lock --- > "+reTry);
			++reTry;
			this.createUser(newUser);
		}
		return flag;
	}
}
