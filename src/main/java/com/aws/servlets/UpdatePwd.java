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

@SuppressWarnings("serial")
@WebServlet("/updatePwd")
public class UpdatePwd extends HttpServlet {

	Logger log = Logger.getLogger(UpdatePwd.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String oldPwd = req.getParameter("oldPwd");
		String newPwd = req.getParameter("password");
		String confPwd = req.getParameter("confPwd");

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		if (!oldPwd.equals(newPwd) && newPwd.equals(confPwd)) {
			log.info("Fetching the User Details For Current User");
			UsersDAO userDAO = UsersDAO.getInstance();
			HttpSession session = req.getSession(false);
			try {
				FOODY_USERS user = userDAO.getUserFromEmail((String) session.getAttribute("email"));
				log.info("Completed fetching the user details");
				if (oldPwd.equals(user.getPassword())) {
					user.setPassword(newPwd);
					boolean status = userDAO.updateUser(user);
					if (status) {
						log.info("Password change has been processed");
						pw.println(
								"<html><head></head><body onload='waring()'><script type='text/javascript'>function waring(){ window.alert(\"Password has been changed successfully.\"); }</script></body></html>");
						resp.sendRedirect("./logout");
					} else {
						log.warn("Password change has been failed. DB Update Can't be Processed");
						pw.println(
								"<html><head></head><body onload='waring()'><script type='text/javascript'>function waring(){ window.alert(\" Password Change Can't be Done as it doesn't meet with standards. \"); }</script></body></html>");
					}
				} else {
					log.warn("Password change has been failed. Mismatch OldPwd with current User Password -->"
							+ user.toString());
					pw.println(
							"<html><head></head><body onload='waring()'><script type='text/javascript'>function waring(){ window.alert(\" Password Change Can't be Done as it doesn't meet with standards. \"); }</script></body></html>");
				}

			} catch (UsersDAO e) {
				log.fatal("User Details Couldn't be found in DB. Perhaps User has been Deleted");
				log.error(e);
			} finally {
				userDAO.tearDown();
			}
		} else {
			log.warn("Password change has been failed");
			pw.println(
					"<html><head></head><body onload='waring()'><script type='text/javascript'>function waring(){ window.alert(\" Password Change Can't be Done as it doesn't meet with standards. \"); }</script></body></html>");
		}
	}
}
