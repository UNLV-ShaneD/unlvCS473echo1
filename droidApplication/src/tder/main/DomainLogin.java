package tder.main;

import android.content.Context;

/*
 * Handles login verification requests
 */

public class DomainLogin implements Runnable {
	Context context;
	LoginCallback callback;
	LoginData loginData;
	
	public DomainLogin(Context context, LoginCallback callback, LoginData loginData) {
		this.context = context;
		this.callback = callback;
		this.loginData = loginData;
	}

	// Connect to the server with the user's authentication data and call back
	// with the result
	public void run() {
		// Perform authentication
		ServerInterfacer interfacer = new ServerInterfacer();
		callback.result = interfacer.Login(loginData);

		// If the login information is good, save it
		if (callback.result == ServerResultType.RESULT_SUCCESS) {
			MySaveData save = MySaveData.Load(context);
			save.serviceConfiguration.loginData = loginData;
			save.Save(context);
			GpsServiceInterfacer.Invoke(context,
					save.serviceConfiguration);
		}

		callback.run();
	}
}
