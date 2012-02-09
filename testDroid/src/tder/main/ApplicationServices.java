package tder.main;

import java.net.SocketException;

public class ApplicationServices {
	void AddComputer(DataComputer computer) {

	}

	void ModifyComputer(DataComputer computer) {

	}

	void DeleteComputer(DataComputer computer) {

	}

	DataComputer[] GetComputers() {
		return null;
	}

	void ComputerWake(DataComputer computer) {
		final DataComputer finalcomputer = new DataComputer(computer);
		
		new Thread(new Runnable() {
			public void run() {

				MagicPacketer magicPacketer = new MagicPacketer();
				try {
					magicPacketer.Wake(finalcomputer.ipAddress, finalcomputer.macAddress);
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).run();
	}

	void ComputerStandby(DataComputer computer) {

	}

	void ComputerHibernate(DataComputer computer) {

	}
}
