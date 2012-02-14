package tder.main;

import android.content.Context;

public class ApplicationServices {

	// Fields
	final Context context;

	public ApplicationServices(Context context) {
		this.context = context;
	}

	// Connect to the server with the user's authentication data and call back
	// with the result
	void login(final LoginCallback callback, final LoginData loginData) {
		DomainLogin domainLogin = new DomainLogin(context, callback, loginData);
		Thread thread = new Thread(domainLogin);
		thread.run();
	}

	void updateCoordinates(final ServiceConfiguration serviceConfiguration) {
		DomainUpdateCoordinates domainUpdateCoordinates = new DomainUpdateCoordinates(
				context, serviceConfiguration);
		Thread thread = new Thread(domainUpdateCoordinates);
		thread.run();
	}

	void computerCommand(LoginData loginData, ServerCommandType serverCommandType) {
		DomainCommand domainCommand = new DomainCommand(loginData, serverCommandType);
		Thread thread = new Thread(domainCommand);
		thread.run();
	}

	void computerAway(LoginData loginData) {
		computerCommand(loginData, ServerCommandType.STANDBY);
	}
}
