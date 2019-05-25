package com.aws.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.aws.domain.Users;
import com.aws.utility.DbUtil;

public class UsersDAO {
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * This method will insert new entry to Users table in DB
	 * @param newUser = Users Object
	 * @return boolean true = if record insertion is successful otherwise false
	 */
	public boolean createUser(Users newUser) {
		Transaction trans =null;
		boolean flag=false;
		SessionFactory factory = DbUtil.getSessionFactory();
		Session session = factory.openSession(); 
		log.info("Opening Session for saving the Users Object Now...");
		try{
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
		}
		finally {
			log.warn("Terminating Session now...");
			session.close();
			factory.close();
		}
		return flag;
	}	
}
