package edu.unlv.cs673.echoteam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PresentationVerifyLogin {
	HttpSession session;
	
	public PresentationVerifyLogin(HttpSession session) {
		this.session = session;
	}
	
	public boolean verify(HttpServletRequest request) {
		// Reset session data
		session.setAttribute("userId", -1);
		
		// Check credentials

		String username = "" + request.getParameter("username");
		String password = "" + request.getParameter("password");
		
		ApplicationServices services = new ApplicationServices(session);
		
		if (!services.verifyLogin(username, password)) {
			return false;
		}
		return true;
	}
}
