package tder.main;

import java.io.Serializable;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

public class GpsLocationData implements Parcelable, Serializable {
	// Constants
	private static final long serialVersionUID = -7882918784334826031L;

	// Fields
	private double homeLatitude;
	private double homeLongitude;
	private float homeRadius;

	public GpsLocationData(double homeLatitude, double homeLongitude,
			float homeRadius) {
		this.homeLatitude = homeLatitude;
		this.homeLongitude = homeLongitude;
		this.homeRadius = homeRadius;
	}

	public String toString() {
		return "(" + homeLatitude + ", " + homeLongitude + "; " + homeRadius
				+ " m)";
	}

	// Populate textViews with current data
	public void populateHomeTextViews(EditText editTextHomeLatitude,
			EditText editTextHomeLongitude, EditText editTextHomeRadius) {
		editTextHomeLatitude.setText("" + homeLatitude);
		editTextHomeLongitude.setText("" + homeLongitude);
		editTextHomeRadius.setText("" + homeRadius);
	}

	// Update data from textViews
	public void updateFromHomeTextViews(EditText editTextLatitude,
			EditText editTextLongitude, EditText editTextRadius) {
		try {
			homeLatitude = Double.parseDouble(editTextLatitude.getText()
					.toString());
			homeLongitude = Double.parseDouble(editTextLongitude.getText()
					.toString());
			homeRadius = Float.parseFloat(editTextRadius.getText().toString());
		} catch (NumberFormatException e) {
			return;
		}
	}

	// Calculates if the supplied location is in range of home
	public AreaType calculateProximity(Location location) {
		if (this == null) {
			return AreaType.UNKNOWN;
		}

		float gpsDistanceResults[] = new float[3];
		Location.distanceBetween(homeLatitude, homeLongitude,
				location.getLatitude(), location.getLongitude(),
				gpsDistanceResults);

		if (gpsDistanceResults[0] < homeRadius) {
			return AreaType.HOME;
		}

		return AreaType.AWAY;
	}

	//
	// Below this line: Implementation of Parcelable interface
	// We use Parcelable instead of Serializable for IPC transport for
	// performance reasons
	//

	public int describeContents() {
		return 0;
	}

	public GpsLocationData(Parcel source) {
		homeLatitude = source.readDouble();
		homeLongitude = source.readDouble();
		homeRadius = source.readFloat();
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(homeLatitude);
		dest.writeDouble(homeLongitude);
		dest.writeFloat(homeRadius);
	}

	public static final Parcelable.Creator<GpsLocationData> CREATOR = new Parcelable.Creator<GpsLocationData>() {

		public GpsLocationData createFromParcel(Parcel source) {
			return new GpsLocationData(source);
		}

		public GpsLocationData[] newArray(int size) {
			return new GpsLocationData[size];
		}

	};
}