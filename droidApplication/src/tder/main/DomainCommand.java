package tder.main;

/*
 * Processes a command (e.g. WAKE, STANDBY, HIBERNATE) to transmit to server
 */
public class DomainCommand implements Runnable {
	// Fields
	final LoginData loginData;
	final ServerCommandType serverCommandType;
	
	public DomainCommand(LoginData loginData, ServerCommandType serverCommandType) {
		this.loginData = loginData;
		this.serverCommandType = serverCommandType;
	}

	public void run() {
		ServerInterfacer serverInterfacer = new ServerInterfacer();
		serverInterfacer.command(loginData, serverCommandType);
	}

}
