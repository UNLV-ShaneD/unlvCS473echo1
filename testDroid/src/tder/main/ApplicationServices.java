package tder.main;

import java.net.SocketException;

public class ApplicationServices {
	
	// Connect to the server with the user's authentication data and call back with the result
	static void Login(final LoginCallback callback, final LoginData loginData) {
		new Thread(new Runnable() {
			public void run() {
				callback.result = ServerInterfacer.Login(loginData);
				callback.run();
			}
		}).run();
	}

	void AddComputer(DataComputer computer) {

	}

	void ModifyComputer(DataComputer computer) {

	}

	void DeleteComputer(DataComputer computer) {

	}

	DataComputer[] GetComputers() {
		return null;
	}

	void ComputerWake(final DataComputer computer) {

		new Thread(new Runnable() {
			public void run() {

				MagicPacketer magicPacketer = new MagicPacketer();
				try {
					magicPacketer.Wake(computer.ipAddress, computer.macAddress);
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
