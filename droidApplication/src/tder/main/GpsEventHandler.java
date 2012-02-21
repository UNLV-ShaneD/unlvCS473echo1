package tder.main;


public class GpsEventHandler {
	// Fields
	GpsSubsystemConfiguration gpsSubsystemConfiguration;
	GpsEventTimer gpsEventTimer;
	
	// Constants
	static final long DelayBeforeAction = 1000;
	
	public GpsEventHandler(GpsSubsystemConfiguration gpsSubsystemConfiguration) {
		this.gpsSubsystemConfiguration = gpsSubsystemConfiguration;
		gpsEventTimer = new GpsEventTimer(DelayBeforeAction+1, DelayBeforeAction, gpsSubsystemConfiguration);
	}
	
	// Cancels if our configuration changed
	public void cancel() {
		gpsEventTimer.cancel();
	}
	
	// Calls necessary procedures when entering/exiting home zone after a time delay
	public void onEnterArea(final AreaType area) {
		gpsEventTimer.cancel();
		gpsEventTimer.area = area;
		gpsEventTimer.start();
	}
}
