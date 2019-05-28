package com.aws.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ibatis.common.jdbc.ScriptRunner;


public class DbUtil {
	
	private static Logger log;
	private static DataSource dataSource ;
	private static final String JNDI_LOOKUP_SERVICE = "java:/comp/env/db/awsMysqlDb";

	static {
		log = Logger.getLogger(DbUtil.class);
	}
	
	/**
	 * This method will return DATASOURCE Object through lookup in JNDI Library
	 * @return {@link DataSource} object = on which getConnection() method is called 
	 * to get a reference object of Connection
	 */
	public static DataSource getDataSource() {
		try {
			Context context = new InitialContext();
			log.info("Looking Through JNDI Service Now....");
			Object lookup = context.lookup(JNDI_LOOKUP_SERVICE);
			if(lookup!=null) {  
				dataSource = (DataSource)lookup;
				log.info("+++ Pooling obj found and Returning the same +++");
			}
			else
				log.warn("------> DataSource Not Found <------");
		} catch (NamingException e) {
			log.fatal("No JNDI Mapped Config can be found in context.xml with the name ---> "+JNDI_LOOKUP_SERVICE);
			log.warn(e.getStackTrace());
		}
		return dataSource;
	}
	
	/**
	 * This method will return the org.hibernate.SessionFactory object
	 * upon which openSession() method can be called to get Session
	 * @return org.hibernate.SessionFactory 
	 */
	public static SessionFactory getSessionFactory() {
		final String CONF_FILE_PATH = "/com/aws/config/hibernate.cfg.xml"; 
		log.info("Creating Configuration Objet for Hibernate");
		Configuration config = new Configuration();
		config = config.configure(CONF_FILE_PATH);
		return(config.buildSessionFactory());
	}
	
	/**
	 * This method will check the table presence in the database or not 
	 * @param tName = table name which need to found out in the DB
	 * @return boolean true = if table exists else returns false
	 */
	public static boolean checkTableInDb(String tName) {
		boolean flag = false;
		try {
			Connection conn = getDataSource().getConnection();
			ResultSet rs = conn.getMetaData().getCatalogs();
			while(rs.next()) {
				String catalogs = rs.getString(1);
				if(tName.equals(catalogs)){
					flag=true;
					break;
				}
			}
			conn.close();
		} catch (SQLException e) {
			log.info("SQL Pool Connection Exception :: "+e.getStackTrace());
		}
		return flag;
	}
	
	/**
	 * This method will create table with DB Schema as awsmysql
	 * @param sqlFile = Sql File Path From Resource Directory
	 * 	Ex,
	 * 		sqlFile = "/USERS_TABLE_SCRIPT/createTable.sql"
	 * @return true = if table is created successfully
	 * 			false = if table creatation is failed
	 * 
	 */
	public static boolean createTable(String sqlFile) throws IOException,SQLException {
		boolean flag = false;
		InputStream in = MethodHandles.lookup().lookupClass().getResourceAsStream(sqlFile);
		Connection con = null;
		try {
			con = DbUtil.getDataSource().getConnection();
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con, false, false);
			log.info("Loading the File || "+sqlFile);
			// Give the input file to Reader
			Reader reader = new BufferedReader(new InputStreamReader(in));
			// Execute script
			sr.runScript(reader);
			log.info("Executed the Script in the directory --> "+sqlFile);
			flag=true;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return flag;
	}
}