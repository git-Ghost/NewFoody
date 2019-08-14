package com.aws.servlets.interfaces;

import java.io.PrintWriter;

public interface RedirectFromMyProfile {

	/**
	 * Content Type must be set for text/html
	 * @param msg = Message to be displayed in Pop-up
	 * @param out = PrintWriter Object which is fetched from Response Object
	 */
	public static void navigateToMyProfile(String msg, PrintWriter out) {
		out.println("<script type=\"text/javascript\">");  
		out.println("alert(' "+msg+" ');"); 
		out.println("window.location = \"./myProfile\";");
		out.println("</script>");
	}
	
	/**
	 * Content Type must be set for text/html
	 * @param msg = Message to be displayed in Pop-up
	 * @param out = PrintWriter Object which is fetched from Response Object
	 */
	public static void navigateToLogout(String msg, PrintWriter out) {
		out.println("<script type=\"text/javascript\">");  
		out.println("alert(' "+msg+" ');"); 
		out.println("window.location = \"./logout\";");
		out.println("</script>");
	}
}
