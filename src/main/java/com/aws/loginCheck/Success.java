package com.aws.loginCheck;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import com.aws.dao.UsersDAO;
import com.aws.domain.FOODY_USERS;

@WebServlet({ "/Success" })
public class Success extends HttpServlet {
	
	private static final long serialVersionUID = -8175337090332985511L;
	Logger log = Logger.getLogger(getClass());
	String errorString = null;
	UsersDAO userDAO_Obj = UsersDAO.getInstance();
	
	/**
	 * doPost method for registration page
	 */
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		final String cName = req.getParameter("name").trim();
		final String cEmail = req.getParameter("email").trim();
		final String password = req.getParameter("password").trim();
		final String confPwd = req.getParameter("confPwd").trim();
		
		if (cName == null || password == null || password.equals("") || cName.equals("")) {
			log.warn("Invalid Entry Made with CName :: " + cName + " and password :: " + password);
			log.info("Redirecting To CreateFail Page due to Invalid Entry being made...");
			errorString = "UserName or Password or Name is invalid!";
			req.setAttribute("errorString", errorString);
			req.getRequestDispatcher("./CreateFail").include(req, resp);
		} 
		else if(!password.equals(confPwd)){
			errorString = "Confirm Password & Password Must Match !!!";
			req.setAttribute("errorString", errorString);
			req.getRequestDispatcher("./CreateFail").include(req, resp);
		}
		  else {
			FOODY_USERS newUser = new FOODY_USERS();
			newUser.setCname(cName);
			newUser.setEmail(cEmail);
			newUser.setPassword(password);
			boolean status;
			try {
				status = userDAO_Obj.createUser(newUser);
				if(status!=false) {
					HttpSession session = req.getSession();
					session.setAttribute("email", newUser.getEmail());
					session.setAttribute("name", newUser.getCname());
					session.setMaxInactiveInterval(120);
					log.info("Registred Successfully Redirecting to Home Page Now...");
					resp.sendRedirect("./home");
				}
				else {
					errorString = "Entry with the Same Email Exists ..";
					req.setAttribute("errorString", errorString);
					resp.sendRedirect("./CreateFail");
				}
			}catch (ConstraintViolationException e) {
				errorString = "An Same Person exists with the same Email Id !!!";
				req.setAttribute("errorString", errorString);
				req.getRequestDispatcher("./CreateFail").include(req, resp);
			} 
			catch (SQLException | UsersDAO e) {
				log.warn(e);
				resp.sendRedirect("./error");
			}
		}
	}
	
	@Override
	public void destroy() {
		userDAO_Obj.tearDown();
		super.destroy();
	}
}