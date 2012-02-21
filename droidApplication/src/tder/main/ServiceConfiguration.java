package tder.main;

import java.io.Serializable;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

/* 
 * Service Configuration class
 * stereotype: information holder
 * Holds all the state needed by the GpsService
 * Includes GPS coordinate, radius, and authentication string
 */

public class ServiceConfiguration implements Parcelable, Serializable {
	// Constants
	private static final long serialVersionUID = 2750061703736628200L;

	
	// Data
	GpsLocationData gpsLocationData = new GpsLocationData(0.0, 0.0, 0.f);

	// Fields
	LoginData loginData = new LoginData();

	public ServiceConfiguration() {
	}

	public LoginData readLoginData() {
		return loginData;
	}

	public void populateHomeTextViews(EditText editTextHomeLatitude,
			EditText editTextHomeLongitude, EditText editTextHomeRadius) {
		
		if (gpsLocationData == null) {
			System.out.println("crash 2");
		}
		gpsLocationData.populateHomeTextViews(editTextHomeLatitude, editTextHomeLongitude, editTextHomeRadius);
	}
	public void updateFromHomeTextViews(EditText editTextLatitude,
			EditText editTextLongitude, EditText editTextRadius) {
		gpsLocationData.updateFromHomeTextViews(editTextLatitude, editTextLongitude, editTextRadius);
	}

	// Find distance between location and home
	public AreaType calculateProximity(Location location) {
		return gpsLocationData.calculateProximity(location);
	}

	public String getLocationDataString() {
		if (gpsLocationData == null) {
			return "(No location)";
		}
		
		return "" + gpsLocationData;
	}

	//
	// Below this line: Implementation of Parcelable interface
	// We use Parcelable instead of Serializable for IPC transport for performance reasons
	//

	public int describeContents() {
		return 0;
	}

	public ServiceConfiguration(Parcel source) {
		gpsLocationData = source.readParcelable(GpsLocationData.class.getClassLoader());
		loginData = source.readParcelable(LoginData.class.getClassLoader());
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(gpsLocationData, 0);
		dest.writeParcelable(loginData, 0);
	}

	public static final Parcelable.Creator<ServiceConfiguration> CREATOR = new Parcelable.Creator<ServiceConfiguration>() {

		public ServiceConfiguration createFromParcel(Parcel source) {
			return new ServiceConfiguration(source);
		}

		public ServiceConfiguration[] newArray(int size) {
			return new ServiceConfiguration[size];
		}

	};
}
