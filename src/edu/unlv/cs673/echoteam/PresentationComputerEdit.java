package edu.unlv.cs673.echoteam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class PresentationComputerEdit implements ComputerEvaluationCallback {
	DataSession session;
	
	public PresentationComputerEdit(HttpSession session) {
		this.session = new DataSession(session);
	}
	
	public String display(HttpServletRequest request) {
		String out = "";

		int id = Integer.parseInt(request.getParameter("computerId"));
		DataComputer computer = new DataComputer(id, session.getUserID());
		ApplicationServices services = new ApplicationServices(session);
		out += services.findComputerByID(this, computer);
		
		return out;
	}

	@Override
	public String evaluateComputer(DataComputer computer) {
		return computer.printEditHTML();
	}
}
