package com.aws.loginCheck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.aws.domain.Users;

@WebServlet({ "/Success" })
public class Success extends HttpServlet {
	private static final long serialVersionUID = -8175337090332985511L;
	Configuration conf;
	SessionFactory factory;
	Session session;
	Transaction trans;
	Users userObj;
	Logger log;
	
	public Success() {
		conf = new Configuration();
		trans = null;
		log = Logger.getLogger(getClass());
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		final String cName = req.getParameter("name").trim();
		final String cEmail = req.getParameter("email").trim();
		final String password = req.getParameter("password").trim();

		if (cName == null || password == null || password.equals("") || cName.equals("")) {
			log.warn("Invalid Entry Made with CName :: " + cName + " and password :: " + password);
			resp.sendRedirect("./CreateFail");
		} else {
			log.info("Initiation of DB Object...");
			conf = conf.configure("/com/aws/config/hibernate.cfg.xml");
			factory = conf.buildSessionFactory();
			session = factory.openSession();
			userObj = new Users();
			log.info("** User Object Created **");
			userObj.setCname(cName);
			userObj.setEmail(cEmail);
			userObj.setPassword(password);

			try {
					log.info(userObj.toString());
					trans = session.beginTransaction();
					session.save(userObj);
					trans.commit();
					log.info("Record Inserted successfully...");
					resp.sendRedirect("./CreateDone");
			} catch (Exception e2) {
				log.warn("Error Occured while inserting to DB\nCheck For Unique values of EMAIL :: " + cEmail
						+ "\nRedirection to Registration Page");
				trans.rollback();
				resp.sendRedirect("./CreateFail");
			}
		}
	}

	@Override
	public void destroy() {
		log.warn("Closing DB Connection Now");
		session.close();
		factory.close();
		super.destroy();
	}
}