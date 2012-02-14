/**
 * 
 */
package tder.main;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author Shane
 * 
 */
public class GpsLocationListener implements LocationListener {
	// Actions to perform when entering/leaving home
	private static TimerTask onHome = new TimerTask() {
		public void run() {
			System.out.println("GPSLOCATIONLISTENER: At home, waking computers.");
		}
	};
	private static TimerTask onAway = new TimerTask() {
		public void run() {
			System.out.println("GPSLOCATIONLISTENER: Leaving home, powering down computers.");
		}
	};
	private static TimerTask onUnknown = new TimerTask() {
		public void run() {
			
		}
	};
	
	enum AreaType {
		HOME (onHome),
		AWAY (onAway),
		UNKNOWN (onUnknown);
		
		TimerTask task;
		
		private AreaType(TimerTask task) {
			this.task = task;
		}
	}
	
	// Fields
	
	static Context context;
	float gpsDistanceResults[];
	ServiceConfiguration configuration;
	AreaType lastAreaType = AreaType.UNKNOWN;
	Timer timer = new Timer();
	static final long DelayBeforeAction = 1000;

	public GpsLocationListener(Context context) {
		this.context = context;
		gpsDistanceResults = new float[3];
	}

	// Calculates if we are entering/leaving home
	public void CheckDistance(float distance) {
		AreaType currentArea = AreaType.AWAY;
		
		if (distance < configuration.homeRadius) {
			currentArea = AreaType.HOME;
		}
		
		if (currentArea != lastAreaType && lastAreaType != AreaType.UNKNOWN) {
			OnEnterArea(currentArea);
		}
		
		lastAreaType = currentArea;
	}

	// Calls necessary procedures when entering/exiting home zone after a time delay
	public void OnEnterArea(final AreaType area) {
//		timer.schedule(new TimerTask() {
//			public void run() {
//				if (area == lastAreaType) {
//					area.task.run();
//				}
//			}
//		}, DelayBeforeAction);

//		timer.schedule(area.task, DelayBeforeAction);
		
		area.task.run();
	}

	// Calculates distance from the home zone
	public void onLocationChanged(Location location) {
		if (configuration == null)
			return;

		// Calculate distance between home and current location
		Location.distanceBetween(configuration.homeLatitude,
				configuration.homeLongitude, location.getLatitude(),
				location.getLongitude(), gpsDistanceResults);

		// Debug message output
		String message = "New GPS location: (" + location.getLongitude() + ", "
				+ location.getLatitude() + ")\nDistance from home: "
				+ gpsDistanceResults[0] + " meters.";
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();

		CheckDistance(gpsDistanceResults[0]);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
