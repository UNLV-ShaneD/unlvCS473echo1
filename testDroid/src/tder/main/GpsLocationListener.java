/**
 * 
 */
package tder.main;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author Shane
 * This class is responsible for calculating if the received GPS positions are inside the home area or not
 * This class will fire off events to the GpsEventHandler if the home area is entered or exited
 */
public class GpsLocationListener implements LocationListener {

	// Fields
	GpsSubsystemConfiguration gpsSubsystemConfiguration;
	GpsEventHandler gpsEventHandler;
	AreaType lastAreaType = AreaType.UNKNOWN;

	// Initialize the listener
	public GpsLocationListener(
			GpsSubsystemConfiguration gpsSubsystemConfiguration) {
		this.gpsSubsystemConfiguration = gpsSubsystemConfiguration;
		this.gpsEventHandler = new GpsEventHandler(gpsSubsystemConfiguration);
	}

	// Allow post-creation service configuration
	public void updateServiceConfiguration(
			ServiceConfiguration serviceConfiguration) {
		gpsSubsystemConfiguration.serviceConfiguration = serviceConfiguration;
		gpsEventHandler.cancel();
		gpsEventHandler = new GpsEventHandler(gpsSubsystemConfiguration);
	}

	// Calculates distance from the home zone
	public void onLocationChanged(Location location) {
		ServiceConfiguration serviceConfiguration = gpsSubsystemConfiguration.serviceConfiguration;
		if (serviceConfiguration == null)
			return;

		// Calculate distance between home and current location
		float gpsDistanceResults[] = new float[3];
		Location.distanceBetween(serviceConfiguration.homeLatitude,
				serviceConfiguration.homeLongitude, location.getLatitude(),
				location.getLongitude(), gpsDistanceResults);

		// Debug message output
		String message = "New GPS location: (" + location.getLongitude() + ", "
				+ location.getLatitude() + ")\nDistance from home: "
				+ gpsDistanceResults[0] + " meters.";
		Toast.makeText(gpsSubsystemConfiguration.context, message,
				Toast.LENGTH_LONG).show();

		onDistanceChange(gpsDistanceResults[0]);
	}

	// Calculates if we are entering/leaving home and fires off events if needed
	public void onDistanceChange(float distance) {
		ServiceConfiguration serviceConfiguration = gpsSubsystemConfiguration.serviceConfiguration;
		AreaType currentArea = AreaType.AWAY;

		if (distance < serviceConfiguration.homeRadius) {
			currentArea = AreaType.HOME;
		}

		if (currentArea != lastAreaType && lastAreaType != AreaType.UNKNOWN) {
			gpsEventHandler.onEnterArea(currentArea);
		}

		lastAreaType = currentArea;
	}

	// Unused
	public void onProviderDisabled(String provider) {
	}

	// Unused
	public void onProviderEnabled(String provider) {
	}

	// Unused
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
