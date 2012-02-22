package edu.unlv.cs673.echoteam;

import javax.servlet.http.HttpSession;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class PresentationComputerListAll implements ComputerEvaluationCallback {
	HttpSession session;
	
	public PresentationComputerListAll(HttpSession session) {
		this.session = session;
	}
	
	public String display() {
		ApplicationServices services = new ApplicationServices(session);
		return services.displayComputers(this);
	}

	@Override
	public String evaluateComputer(DataComputer computer) {
		return computer.printListRow();
	}
}
