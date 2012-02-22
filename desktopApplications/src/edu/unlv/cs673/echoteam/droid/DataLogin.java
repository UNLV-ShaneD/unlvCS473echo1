package edu.unlv.cs673.echoteam.droid;

import java.util.List;

import edu.unlv.cs673.echoteam.ComputerDAO;
import edu.unlv.cs673.echoteam.UserDAO;
import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class DataLogin {
	private String username;
	private String password;
	private int userid = -1;
	
	public DataLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	private void authenticate() {
		UserDAO userDAO = new UserDAO();
		userid = userDAO.authenticateUser(username, password);
	}
	
	public boolean isAuthenticated() {
		authenticate();
		
		return userid > 0;
	}

	public List<DataComputer> getComputers() {
 		ComputerDAO computerDao = new ComputerDAO();
 		return computerDao.selectAllComputersForUser(userid);
	}
}
