package com.aws.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.aws.domain.Users;
import com.aws.utility.DbUtil;
import com.ibatis.common.jdbc.ScriptRunner;

public class UsersDAO {

	Logger log = Logger.getLogger(this.getClass());
	ReentrantLock threadLock = new ReentrantLock();

	/**
	 * This method will insert new entry to Users table in DB
	 * 
	 * @param newUser
	 *            = Users Object
	 * @return boolean true = if record insertion is successful otherwise false
	 */
	public boolean createUser(Users newUser) {
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
				this.createUseTable();
				log.info("User Table Created Successfull in the Schema");
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

	/**
	 * This method will create User table in the DB Schema
	 */
	private void createUseTable() {
		final String SQLScriptFilePath = "./src/main/resources/DbScripts/users.sql";
		Connection con = null;
		try {
			con = DbUtil.getDataSource().getConnection();
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con, false, false);
			log.info("Loading the File || " + SQLScriptFilePath);
			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(SQLScriptFilePath));
			// Execute script
			sr.runScript(reader);
			log.info("Executed the Script in the directory --> " + SQLScriptFilePath);

		} catch (SQLException e) {
			log.warn("Connection Object Creation Exception while creating User Table\n" + e.getStackTrace());
		} catch (FileNotFoundException e) {
			log.warn("Script File Can't be Found in specified Path ---> " + SQLScriptFilePath);
		} catch (IOException e) {
			log.warn("Error in Executing Script For --> " + SQLScriptFilePath);
			log.warn(e.getStackTrace());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
}
