package edu.unlv.cs673.echoteam.droid;

import java.util.List;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class ApplicationServices {
	DataLogin login;

	public ApplicationServices(DataLogin login) {
		this.login = login;
	}

	public void act(Action action) {
		DomainComputerAction domain;

		switch (action) {
		case wake:
			domain = new DomainComputerWake();
			break;
		case standby:
			domain = new DomainComputerStandby();
			break;
		case hibernate:
			// TODO: Implement separate hibernate case
			domain = new DomainComputerStandby();
			break;
		default:
			return;
		}

 		List<DataComputer> results = login.getComputers();
 		
 		for(DataComputer computer: results){
 			domain.run(computer);
 		}
	}
}
