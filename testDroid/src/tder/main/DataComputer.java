package tder.main;

public class DataComputer {
	String displayName;
	String ipAddress;
	String macAddress;
	
	public DataComputer(DataComputer other)
	{
		displayName = other.displayName;
		ipAddress = other.ipAddress;
		macAddress = other.macAddress;
	}
}
