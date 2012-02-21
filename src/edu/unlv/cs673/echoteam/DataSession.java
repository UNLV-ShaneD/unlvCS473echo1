package edu.unlv.cs673.echoteam;

import javax.servlet.http.HttpSession;

public class DataSession {
	HttpSession httpSession;

	public DataSession(HttpSession session) {
		this.httpSession = session;
	}

	public int getUserID() {
		Object objectUserID = httpSession.getAttribute("userId");
		if (objectUserID != null) {
			String number = "" + httpSession.getAttribute("userId");
			return Integer.parseInt(number);
		}

		return -1;
	}
	
	public int authenticateUser(String username, String password) {
		if (username == null || password == null)
			return -1;
		
		UserDAO userDAO = new UserDAO();
		int userid = userDAO.authenticateUser(username, password);
		
		httpSession.setAttribute("userId", userid);
		
		return userid;
	}

	// If the user has not yet been authenticated, attempt authentication with the supplied credentials
	// If the user has already been authenticated this session, username and password are ignored
	public boolean isAuthenticated(String username, String password) {
		if (getUserID() > 0)
			return true;
		
		return authenticateUser(username, password) > 0;
	}

	public boolean shouldAddComputer() {
		return (httpSession.getAttribute("addComputerFlag") != null)
				&& (Integer) httpSession.getAttribute("addComputerFlag") == 1;
	}

	public void setAddComputer(boolean state) {
		if (state) {
			httpSession.setAttribute("addComputerFlag", 1);
			return;
		}

		httpSession.setAttribute("addComputerFlag", 0);
	}
}
