package tder.main;

public enum ServerCommandType {
	WAKE ("wake.jsp"),
	STANDBY ("standby.jsp"),
	HIBERNATE ("hibernate.jsp");
	
	String page;
	
	private ServerCommandType(String page) {
		this.page = page;
	}
}
