package edu.unlv.cs673.echoteam;

import javax.servlet.http.HttpSession;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class PresentationAdminComputerListAll implements ComputerEvaluationCallback {
	DataSession session;

	public PresentationAdminComputerListAll(HttpSession session) {
		this.session = new DataSession(session);
	}

	public String display() {
		ApplicationServices services = new ApplicationServices(session);
		return services.displayComputersAdmin(this);
	}

	@Override
	public String evaluateComputer(DataComputer computer) {
		return computer.printListRowAdmin();
	}
}
