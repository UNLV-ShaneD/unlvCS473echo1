package edu.unlv.cs673.echoteam;

import javax.servlet.http.HttpSession;

public class ApplicationServices {
	HttpSession session;

	public ApplicationServices(HttpSession session) {
		this.session = session;
	}
	
	private int getUserID() {
		Object objectUserID = session.getAttribute("userId");
		if (objectUserID != null ){
			String number = "" + session.getAttribute("userId");
			return Integer.getInteger(number, -1);
		}
		
		return -1;
	}

	public boolean verifyLogin(String username, String password) {
		int userID = getUserID();
		
		if (userID > 0) {
			// We're authenticated
			return true;
		}
		
		
		return false;
	}
}
