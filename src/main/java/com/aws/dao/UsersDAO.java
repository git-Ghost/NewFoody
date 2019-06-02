package com.aws.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.aws.domain.Users;
import com.aws.utility.DbUtil;

public class UsersDAO extends Exception{

	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(this.getClass());
	ReentrantLock threadLock = new ReentrantLock();
	SessionFactory factory;
	Session session;
	private static UsersDAO instance = null;
	
	/**
	 * Zero-Param private Constructor to enable to create object
	 */
	private UsersDAO() {
		factory = DbUtil.getSessionFactory();
		session = factory.openSession();
	}
	/**
	 * This method to get object of single object of UsersDAO Object
	 * @return UsersDAO = If null then it create new object or else returns eariler object
	 */
	public static UsersDAO getInstance() {
		if(instance == null)
			instance = new UsersDAO();
		return instance;
	}
	/**
	 * Destroying single instance to enable object creation
	 */
	public void tearDown() {
		if(instance!=null) {
			log.warn("Closing Session & Factory Objects...");
			session.close();
			factory.close();
		}
	}
	
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
		int reTry = 0;

		log.info("Trying For ReentrantLock for user creation !!!");
		// Getting Outer Lock
		boolean ans = threadLock.tryLock();
		if (ans) {
			Transaction trans = null;
			dbCheck = DbUtil.checkTableInDb("Users");
			if (dbCheck == false) {
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
	
	/**
	 * Will give the User Info of the provided cred pair of mail and password
	 * @param email = Email of the user
	 * @param password = Password of the user
	 * @return User = Which is mapped against the data and returns null if no mapping was found
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public Users getData(String email, String password) {
		Users info = null;
		log.info("Fetching Related Data From Passed Cred >>>> Email :: "+email);
		Query query = session.createQuery("FROM Users user WHERE user.email =:email AND user.password =:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<Users> dataList = query.getResultList();
		
		if(dataList.size()!= 0)
		{
			log.info("Match Found with Crendital Pair for >>>> Email :: "+email);
			for(Users temp : dataList) {
				info = temp;
				break;
			}
		}
		return info;
	}
}
