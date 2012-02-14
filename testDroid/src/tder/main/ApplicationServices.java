package tder.main;

import android.content.Context;

public class ApplicationServices {
	
	// Fields
	Context context;
	
	public ApplicationServices(Context context) {
		this.context = context;
	}

	// Connect to the server with the user's authentication data and call back
	// with the result
	void Login(final LoginCallback callback,
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

	void ComputerWake() {
		new Thread(new Runnable() {
			public void run() {
				//stub

			}
		}).run();
	}

	void ComputerStandby() {

	}

	void ComputerHibernate() {

	}
	
	void ComputerAway() {
		ComputerStandby();
	}
}
