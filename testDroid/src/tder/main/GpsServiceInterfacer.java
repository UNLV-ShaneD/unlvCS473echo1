package tder.main;

import android.content.Context;
import android.content.Intent;
import android.location.Location;

public class GpsServiceInterfacer {
	// Invokes the GPS service with the specified arguments
	static public void Invoke(Context context, ServiceConfiguration configuration)
	{
		Intent intent = new Intent(context, GpsService.class);
		intent.putExtra("tder.main.configuration", configuration);
		context.startService(intent);
	}
	
	/* DEPRECATED: Using information holder class to hold arguments now
	// Disables the GPS service from creating messages
	static public void Disable(Context context){
		Intent intent = new Intent(context, GpsService.class);
		intent.putExtra("tder.main.gps_enable", false);
		context.startService(intent);
	}
	
	// Enable the GPS service to automatically send wake/standby/hibernate commands for the specified GPS position and radius
	static public void Enable(Context context, Location homeLocation, float radius){
		Intent intent = new Intent(context, GpsService.class);
		intent.putExtra("tder.main.gps_enable", true);
		intent.putExtra("tder.main.gps_latitude", homeLocation.getLatitude());
		intent.putExtra("tder.main.gps_longitude", homeLocation.getLongitude());
		intent.putExtra("tder.main.gps_distance", radius);
		context.startService(intent);
	}
	*/
}
