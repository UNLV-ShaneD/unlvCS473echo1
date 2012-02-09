/**
 * 
 */
package tder.main;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author Shane
 *
 */
public class MyLocationListener implements LocationListener {
	Context context;
	
	float gpsDistanceResults[];
	Location homeLocation;
	ServiceConfiguration configuration;
	
	public MyLocationListener(Context context){
		this.context = context;
		homeLocation = new Location("Home");
		homeLocation.setLatitude(30);
		homeLocation.setLongitude(30);
		
		gpsDistanceResults = new float[3];
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onLocationChanged(android.location.Location)
	 */
	public void onLocationChanged(Location location) {
		// Calculate distance between home and current location
		Location.distanceBetween(homeLocation.getLatitude(), homeLocation.getLongitude(), location.getLatitude(), location.getLongitude(), gpsDistanceResults);

		// Debug message output
		String message =  "New GPS location: (" + location.getLongitude() + ", " + location.getLatitude() + ")\nDistance from home: " + gpsDistanceResults[0] + " meters.";
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onStatusChanged(java.lang.String, int, android.os.Bundle)
	 */
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
