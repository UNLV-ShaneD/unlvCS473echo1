package tder.main;

public enum ServerCommandType {
	VERIFY ("verify"),
	WAKE ("wake"),
	STANDBY ("standby"),
	HIBERNATE ("hibernate");
	
	String action;
	
	private ServerCommandType(String action) {
		this.action = action;
	}
}
