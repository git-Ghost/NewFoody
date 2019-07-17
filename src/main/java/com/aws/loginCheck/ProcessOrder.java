package com.aws.loginCheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.aws.dao.FOODY_ORDER_DETAILS_DAO;
import com.aws.dao.FOODY_USER_ORDERS_DAO;
import com.aws.dao.UsersDAO;
import com.aws.domain.FOODY_ORDER_DETAILS;
import com.aws.domain.FOODY_USERS;
import com.aws.domain.FOODY_USER_ORDERS;
import com.foody.pojo.CartItem;

@WebServlet("/checkOut")
public class ProcessOrder extends HttpServlet {

	private static final long serialVersionUID = -5327246388336981637L;
	Logger log = Logger.getLogger(this.getClass());;
	FOODY_USER_ORDERS_DAO orderInstance;
	FOODY_ORDER_DETAILS_DAO orderDetailsInstance;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Checking For Current Active Session if Any...");
		HttpSession session = req.getSession(false);
		if (session.getAttribute("name") != null) {
			log.info("Session Found Currently for the User -->>> "+session.getAttribute("name"));
			String cartContent = req.getParameter("myCart");
			log.info("<<--- :: Cart Items Provided :: --->>\n"+cartContent);
			String jsonFormat[] = this.getJsonContent(cartContent);
			Set<CartItem> data = new HashSet<CartItem>(); // All the Order Substance
			for (int i = 0; i < jsonFormat.length; ++i) {
				data.add(CartItem.getObject(jsonFormat[i])); //Adding Selected Items to an Collection
			}

			float totalOrderAmt = 0;
			String currencyCode = null;
			String Order_ID = new SecureRandom().ints(0, 36).mapToObj(i -> Integer.toString(i, 36))
					.map(String::toUpperCase).distinct().limit(16).collect(Collectors.joining())
					.replaceAll("([A-Z0-9]{4})", "$1-").substring(0, 19);
			log.info("Order Item Generated For the Current Session --->> "+Order_ID);
			for (CartItem temp : data) {
				String info[] = temp.getItemPrice().split(" ");
				if (currencyCode == null)
					currencyCode = info[0];
				totalOrderAmt = totalOrderAmt + Float.parseFloat(info[1]);
			}
			
			try {
				orderInstance= FOODY_USER_ORDERS_DAO.getInstance();
				orderDetailsInstance = FOODY_ORDER_DETAILS_DAO.getInstance();

				// Fetching Email Attribute from the HTTPSession Attribute
				String identifierMail = (String) session.getAttribute("email");
				// Fetching CID for the given User Mail ID
				FOODY_USERS user = UsersDAO.getInstance().getUserFromEmail(identifierMail);
				
				// Map TO Both Table by checking if table exists or not
				FOODY_USER_ORDERS newOrder = new FOODY_USER_ORDERS(Order_ID, user, currencyCode, totalOrderAmt);
				orderInstance.insertOrderForUser(newOrder);
				
				//Inserting Order Details For the Order-ID
				
				for(CartItem temp : data) {
					String price[] = temp.getItemPrice().split(" ");
					FOODY_ORDER_DETAILS details = new FOODY_ORDER_DETAILS(newOrder, temp.getItemName(), temp.getItemCount(), Float.parseFloat(price[price.length-1]));
					orderDetailsInstance.insertDetailsForOrderID(details);
				}
				
				resp.setContentType("text/html");
				PrintWriter pw = resp.getWriter();
				pw.println(
						"<html><head></head><body onload='clearLocal()'><script type='text/javascript'>function clearLocal(){ window.alert(\" Your Food order has been placed Successfully and your Order ID is "+Order_ID+" \"); localStorage.clear(); window.location =\"./index\";}</script></body></html>");
			
			} catch (Exception e1) {
				log.error( "Unable to Get Instance Object For User Order Related Objects !", e1 );
			}
		} else {
			log.warn("No Session Found For Current Session. Redirecting to Login Page Now....");
			req.getRequestDispatcher("./login").forward(req, resp);
		}
	}

	/**
	 * This method will return the selected items present in the input String
	 * 
	 * @param input
	 *            = Input String [{A,B},{X,Y}]
	 * @return String[] = arr[0] --> {A,B} = arr[1] --> {X,Y}
	 */
	private String[] getJsonContent(String input) {
		String arr = input.replace("[", "").replace("]", "");
		String str[] = arr.split("},");
		for (int i = 0; i < str.length; ++i) {
			if ((i + 1) != str.length)
				str[i] = str[i] + "}";
		}
		return str;
	}
	
	@Override
	public void destroy() {
		orderInstance.destory();
		orderDetailsInstance.destory();
	}
}
