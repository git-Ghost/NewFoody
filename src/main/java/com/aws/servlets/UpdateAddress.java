package com.aws.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.aws.dao.UsersDAO;
import com.aws.domain.FOODY_USERS;
import com.aws.servlets.interfaces.RedirectFromMyProfile;

@SuppressWarnings("serial")
@WebServlet("/updateAdd")
public class UpdateAddress extends HttpServlet {

	Logger log = Logger.getLogger(UpdateAddress.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String addressUpdate = req.getParameter("address");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		if (addressUpdate.length() <= 100) {
			HttpSession session = req.getSession(false);
			UsersDAO userDAO = UsersDAO.getInstance();
			try {
				log.info("Fetching the User Details For Current User");
				FOODY_USERS user = userDAO.getUserFromEmail((String) session.getAttribute("email"));
				log.info("Completed fetching the user details -- > " +user.toString());
				user.setAddress(addressUpdate);
				boolean status = userDAO.updateUser(user);
				if (status) {
					log.info("Address Has Been Updated For User :: "+user.toString());
					RedirectFromMyProfile.navigateToMyProfile("Your Address Has been updated Successfully.", out);	
				} else {
					log.warn("Couldn't able to process address change for given user :: "+user.toString());
					RedirectFromMyProfile.navigateToMyProfile("Address Update Failed to insert into DB." , out);		
				}

			} catch (UsersDAO e) {
				log.error(e);
				resp.sendError(404);
			} finally {
				userDAO.tearDown();
			}

		} else {
			log.info("Found Address Length Greater than 100 :: "+addressUpdate);
			RedirectFromMyProfile.navigateToMyProfile("Your Address update has been failed. Length limit 100 .", out);
		}
		out.close();
	}
}
