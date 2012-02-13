package tder.main;

import java.net.SocketException;

import android.content.Context;

public class ApplicationServices {

	// Connect to the server with the user's authentication data and call back
	// with the result
	static void Login(final Context context, final LoginCallback callback,
			final LoginData loginData) {
		new Thread(new Runnable() {
			public void run() {
				callback.result = ServerInterfacer.Login(loginData);

				// If the login information is good, save it
				if (callback.result == LoginCallback.ResultType.RESULT_SUCCESS) {
					MySaveData save = MySaveData.Load(context);
					save.serviceConfiguration.loginData = loginData;
					save.Save(context);
					GpsServiceInterfacer.Invoke(context,
							save.serviceConfiguration);
				}

				callback.run();
			}
		}).run();
	}

	static void UpdateCoordinates(final Context context,
			final ServiceConfiguration serviceConfiguration) {
		new Thread(new Runnable() {
			public void run() {

				MySaveData save = MySaveData.Load(context);
				serviceConfiguration.loginData = save.serviceConfiguration.loginData;
				save.serviceConfiguration = serviceConfiguration;
				save.Save(context);
				GpsServiceInterfacer.Invoke(context, save.serviceConfiguration);
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
