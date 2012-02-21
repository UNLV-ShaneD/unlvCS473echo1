package edu.unlv.cs673.echoteam;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InterfaceVerifyLogin {
	HttpSession session;
	
	public InterfaceVerifyLogin(HttpSession session) {
		this.session = session;
	}
	
	// Send user to home page
	private void redirectHome(HttpServletResponse response) {
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verify(HttpServletResponse response) {
		// Check credentials

		String username = "" + session.getAttribute("username");
		String password = "" + session.getAttribute("password");
		
		ApplicationServices services = new ApplicationServices(session);
		
		if (!services.verifyLogin(username, password)) {
			redirectHome(response);
		}
	}
}
