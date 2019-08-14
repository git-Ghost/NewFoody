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
						RedirectFromMyProfile.navigateToLogout("Password has been changed successfully.",pw);
						
					} else {
						log.warn("Password change has been failed. DB Update Can't be Processed");
						RedirectFromMyProfile.navigateToMyProfile("Password update failed due to DB error.", pw);
					}
				} else {
					log.warn("Password change has been failed. Mismatch OldPwd with current User Password"
							+ user.toString());
					RedirectFromMyProfile.navigateToMyProfile("Password Can't be changed as Old Password Doesn't match with our records.", pw);
				}

			} catch (Exception e) {
				log.fatal("User Details Couldn't be found in DB. Perhaps User has been Deleted");
				log.error(e);
			} finally {
				userDAO.tearDown();
			}
		} else if(oldPwd.equals(newPwd)) {
			log.warn("Provided Old Password and new password are same");
			RedirectFromMyProfile.navigateToMyProfile("Password Can't be changed due to old Password and new Password are same.", pw);
		}else {
			log.warn("Provided New Password and confirm password are same");
			RedirectFromMyProfile.navigateToMyProfile("Password Can't be changed due to new password and confirm password don't match", pw);
		}
		pw.close();
	}
}
