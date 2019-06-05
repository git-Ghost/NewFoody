package com.aws.loginCheck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.aws.dao.UsersDAO;
import com.aws.domain.Users;
import com.aws.utility.DbUtil;

@WebServlet("/console")
public class ConsoleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	UsersDAO userDAO_Obj = UsersDAO.getInstance();
	// private final String sessionId = UUID.randomUUID().toString();
	Logger log = Logger.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String emailID = req.getParameter("email");
		String password = req.getParameter("password");
		boolean isPresent = DbUtil.checkTableInDb("users");
		if (isPresent == false) {
			log.fatal("No Table can be found for this ...Redirecting to Registration !!!");
			res.sendRedirect("./new");
		} else {
			try {
				Users userInfo = userDAO_Obj.getData(emailID, password);
				if (userInfo != null) {
					HttpSession session = req.getSession();
					session.setAttribute("email", emailID);
					session.setAttribute("name", userInfo.getCname());
					
					req.getRequestDispatcher("home").include(req, res);
				} else
					res.sendRedirect("./loginFailed");
			} catch (Exception e) {
				log.fatal(e);
				res.sendRedirect("./error");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.getParameter("email").trim() != "" && req.getParameter("password") != "") 
			this.doGet(req, res);
		else
			res.sendRedirect("./loginFailed");
	}

	@Override
	public void destroy() {
		userDAO_Obj.tearDown();
		super.destroy();
	}
}
