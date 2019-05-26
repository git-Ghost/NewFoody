package com.aws.loginCheck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.aws.dao.UsersDAO;
import com.aws.domain.Users;

@WebServlet({ "/Success" })
public class Success extends HttpServlet {
	
	private static final long serialVersionUID = -8175337090332985511L;
	Logger log = Logger.getLogger(getClass());

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

		if (cName == null || password == null || password.equals("") || cName.equals("")) {
			log.warn("Invalid Entry Made with CName :: " + cName + " and password :: " + password);
			log.info("Redirecting To CreateFail Page due to Invalid Entry being made...");
			resp.sendRedirect("./CreateFail");
		} else {
			Users newUser = new Users();
			newUser.setCname(cName);
			newUser.setEmail(cEmail);
			newUser.setPassword(password);
			boolean status = new UsersDAO().createUser(newUser);
			if(status!=false)
				resp.sendRedirect("./CreateDone");
			else
				resp.sendRedirect("./CreateFail");
		}
	}
}