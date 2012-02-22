package edu.unlv.cs673.echoteam.droid;

import edu.unlv.cs673.echoteam.helpers.DataComputer;

public class DomainComputerWake implements DomainComputerAction {

	@Override
	public void run(DataComputer computer) {
		computer.wake();
	}

}
