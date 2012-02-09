package tder.main;

import android.os.Parcel;
import android.os.Parcelable;

/* 
 * Service Configuration class
 * stereotype: information holder
 * Holds all the state needed by the GpsService
 * Includes GPS coordinate, radius, and authentication string
 */

public class ServiceConfiguration implements Parcelable {

	boolean enable;
	double homeLatitude;
	double homeLongitude;
	float homeRadius;
	String authentication;

	public ServiceConfiguration() {
		enable = false;
		homeLatitude = 0.0;
		homeLongitude = 0.0;
		homeRadius = 0.f;
		authentication = "";
	}
	
	//
	// Below this line: Implementation of Parcelable interface
	// We use Parcelable instead of Serializable for performance reasons
	//
	
	public int describeContents() {
		return 0;
	}

	public ServiceConfiguration(Parcel source) {
		boolean bools[] = new boolean[1];
		source.readBooleanArray(bools);
		
		enable = bools[0];
		homeLatitude = source.readDouble();
		homeLongitude = source.readDouble();
		homeRadius = source.readFloat();
		authentication = source.readString();
	}

	public void writeToParcel(Parcel dest, int flags) {
		boolean bools[] = new boolean[1];
		bools[0] = enable;
		dest.writeBooleanArray(bools);
		dest.writeDouble(homeLatitude);
		dest.writeDouble(homeLongitude);
		dest.writeFloat(homeRadius);
		dest.writeString(authentication);
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

		public ServiceConfiguration createFromParcel(Parcel source) {
			return new ServiceConfiguration(source);
		}

		public ServiceConfiguration[] newArray(int size) {
			return new ServiceConfiguration[size];
		}

	};

}
