package edu.unlv.cs673.echoteam;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class ApplicationServices {
	DataSession session;

	public ApplicationServices(HttpSession session) {
		this.session = new DataSession(session);
	}
	
	public ApplicationServices(DataSession session) {
		this.session = session;
	}

	public boolean verifyLogin(String username, String password) {
		// Check if the user is authenticated
		return session.isAuthenticated(username, password);
	}
	
	public String displayComputers(ComputerEvaluationCallback callback) {
		String out = "";
		
		int userId = session.getUserID();
 		ComputerDAO computerDao = new ComputerDAO();
 		List<DataComputer> results = computerDao.selectAllComputersForUser(userId);
 		
 		for(DataComputer ch: results){
 			if (ch == null)
 				break;
 			out += callback.evaluateComputer(ch);
 		}
 		
 		return out;
	}

	public String displayComputersAdmin(ComputerEvaluationCallback callback) {
		String out = "";
		
 		ComputerDAO computerDao = new ComputerDAO();
 		List<DataComputer> results = computerDao.selectAllComputers();
 		
 		for(DataComputer ch: results){
 			if (ch == null)
 				break;
 			out += callback.evaluateComputer(ch);
 		}
 		
 		return out;
	}
	
	public void addComputer(DataComputer computer) throws Exception {
		ComputerDAO myDao = new ComputerDAO();
		myDao.insertComputer(computer);	
	}

	public String findComputerByID(ComputerEvaluationCallback callback, DataComputer computer) {
		String out = "";
		
		ComputerDAO computerDao = new ComputerDAO();
		DataComputer resultComputer;
		try {
			resultComputer = computerDao.getComputerByID(computer);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		if (resultComputer == null)
			return null;
		
		out += callback.evaluateComputer(resultComputer);
		
		return out;
	}
}
