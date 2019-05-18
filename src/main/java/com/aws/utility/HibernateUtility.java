package com.aws.utility;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.AnnotationConfiguration;

import com.aws.domain.Users;

@SuppressWarnings({"deprecation","rawtypes"})
public class HibernateUtility {
	
	Logger log;
	Configuration conf = new Configuration();
	SessionFactory factory;
	Session session;
	private static HibernateUtility obj=null;
	
	/**
	 * Private Constructor for Hibernate Utility
	 */
	private HibernateUtility() {
		log=Logger.getLogger(getClass());
	}
	
	/**
	 * Only One Object will be returned
	 * @return HibernateUtility = Returns unique if obeject isn't present otherwise returns same object
	 */
	public static HibernateUtility getInstance(){
		if(obj == null)
			obj=new HibernateUtility();
		return obj;
	}
	
	/**
	 * Returns Session Object for the Hibernate Connection
	 * @return Session = Hibernate Session Object
	 */
	public Session getHbSessionObj(){
		log.info("Initiation of DB Object...");
		conf.addAnnotatedClass(Users.class);
        conf = conf.configure("./com/aws/config/hibernate.cfg.xml");
        factory = conf.buildSessionFactory();
        session = factory.openSession();
		return session;
	}
	
	/**
	 * Closing the Hibernate Object Safely
	 */
	public void closeHbSessionObj(){
		log.warn("Closing DB Connection Now");
        session.close();
        factory.close();
	}
	
	public boolean checkForUserNamePresence(Session session,String email){
		log.info("Fetching Customer Info for >>>> "+email);
	    Query query = session.createQuery("from users where EMAIL = :email");
	    query.setParameter("email", email);
	    return (query.uniqueResult()!=null);
	}
	
	/*public static void main(String[] args) {
		try{
		HibernateUtility obj=HibernateUtility.getInstance();
		Session session = obj.getHbSessionObj();
		System.out.println(obj.checkForUserNamePresence(session, "no-reply@gmail.com"));
		}finally {
			obj.closeHbSessionObj();
		}
	}*/
	
}
