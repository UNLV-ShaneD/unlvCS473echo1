package tder.main;

import android.content.Context;

public class GpsSubsystemConfiguration {
	Context context;
	ServiceConfiguration serviceConfiguration;
	
	public GpsSubsystemConfiguration(Context context, ServiceConfiguration serviceConfiguration) {
		this.context = context;
		this.serviceConfiguration = serviceConfiguration;
	}
}
