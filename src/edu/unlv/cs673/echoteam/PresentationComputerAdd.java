package edu.unlv.cs673.echoteam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class PresentationComputerAdd {
	DataSession session;
	HttpServletRequest request;
	
	public PresentationComputerAdd(HttpSession session, HttpServletRequest request) {
		 this.session = new DataSession(session);
		 this.request = request;
	}
	
	public String run() {
		String out = "";

		// Add the computer info to the db, else display the form to add a computer
		try {
			// Get information that was posted from signup.jsp
			int userId = session.getUserID();
			DataComputer computer = new DataComputer(userId, request);
			
			ApplicationServices services = new ApplicationServices(session);
			services.addComputer(computer);

			out += "<center><b>Computer Added Successfully</b><BR></center>";
		} catch (Exception e) {
			out += "<center><b>Exception occured when trying to add computer.</b><BR></center>";
			e.printStackTrace();
		}
		
		return out;
	}

}
