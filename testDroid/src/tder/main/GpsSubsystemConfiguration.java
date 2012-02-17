package tder.main;

import android.content.Context;
import android.location.Location;

public class GpsSubsystemConfiguration {
	Context context;
	ServiceConfiguration serviceConfiguration;
	
	public GpsSubsystemConfiguration(Context context, ServiceConfiguration serviceConfiguration) {
		this.context = context;
		this.serviceConfiguration = serviceConfiguration;
	}

	public AreaType calculateProximity(Location location) {
		return serviceConfiguration.calculateProximity(location);
	}
}
