package edu.unlv.cs673.echoteam.droid;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class DomainComputerStandby implements DomainComputerAction {

	@Override
	public void run(DataComputer computer) {
		computer.standby();
	}

}
