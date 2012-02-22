package tder.main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.widget.Toast;

public class GpsService extends Service {

	// These constants control GPS update frequency
	// TODO: MinimumTime should be set to at least 60,000 ms for release builds
	// or otherwise configurable to conserve battery
	static final long MinimumTime = 1000; // milliseconds
	static final float MinimumDistance = 1.f; // meters

	// Fields
	ServiceConfiguration serviceConfiguration;
	LocationManager locationManager = null;
	GpsLocationListener myLocationListener = null;

	@Override
	public IBinder onBind(Intent arg0) {

		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// Create listener object to handle GPS events
		GpsSubsystemConfiguration gpsSubsystemConfiguration = new GpsSubsystemConfiguration(
				this, new ServiceConfiguration());
		myLocationListener = new GpsLocationListener(gpsSubsystemConfiguration);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if (intent == null)
			return;

		// Get the GpsService parameters
		serviceConfiguration = intent.getParcelableExtra("tder.main.configuration");

		// Create debugging message to tell us if the messaging service is
		// enabled/disabled
		String debugMessage = "GPS service started";

		// Configure the location manager for necessary GPS updates

		// Find the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Clear any update requests we may already have
		locationManager.removeUpdates(myLocationListener);

		// Re-install the listener
		// Configure the listener
		GpsSubsystemConfiguration gpsSubsystemConfiguration = new GpsSubsystemConfiguration(this, serviceConfiguration);
		myLocationListener.cancel();
		myLocationListener = new GpsLocationListener(gpsSubsystemConfiguration);

		// Request GPS position updates; delegate to our location listener
		// class
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, MinimumTime, MinimumDistance,
				myLocationListener);

		debugMessage += "\nHome: " + serviceConfiguration.getLocationDataString();

		// Display debug message
		Toast.makeText(this, debugMessage, Toast.LENGTH_SHORT).show();
	}

}
