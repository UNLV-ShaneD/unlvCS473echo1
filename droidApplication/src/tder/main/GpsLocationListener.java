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
	
	// Stop any live events
	public void cancel() {
		gpsEventHandler.cancel();
	}

	// Calculates distance from the home zone
	public void onLocationChanged(Location location) {
		// Calculate distance between home and current location
		AreaType area = gpsSubsystemConfiguration.calculateProximity(location);

		// Debug message output
		String message = "New GPS location: (" + location.getLongitude() + ", "
				+ location.getLatitude() + ")\nDesignation: " + area;
		Toast.makeText(gpsSubsystemConfiguration.context, message,
				Toast.LENGTH_LONG).show();

		onAreaChange(area);
	}

	// Calculates if we are entering/leaving home and fires off events if needed
	public void onAreaChange(AreaType currentArea) {
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
