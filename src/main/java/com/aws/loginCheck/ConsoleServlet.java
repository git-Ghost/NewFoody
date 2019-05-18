package com.aws.loginCheck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.aws.utility.HibernateUtility;

@WebServlet({ "/console" })
public class ConsoleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	HibernateUtility hbuObj=null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		hbuObj=HibernateUtility.getInstance();
		Session session=hbuObj.getHbSessionObj();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		res.setIntHeader("Refresh", 1);
		if(req.getParameter("email").trim()!="" && req.getParameter("password")!=""){
			this.doGet(req, res);
		}
		else
			res.sendRedirect("./loginFailed.html");
	}
	
	@Override
	public void destroy() {
		hbuObj.closeHbSessionObj();
		super.destroy();
	}
}
