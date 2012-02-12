package tder.main;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginData implements Parcelable, Serializable {
	private static final long serialVersionUID = 7557766844250154733L;
	String host;
	String userid;
	String password;

	public String getAuthenticationString() throws URISyntaxException {
		URI uri;
		try {
			uri = new URI("http", null, host, 80, "/login", URLEncoder.encode(
					"userid=" + userid + "&password=" + password, "UTF-8"),
					null);
			return uri.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public LoginData() {
	}

	//
	// Below this line: Implementation of Parcelable interface
	// We use Parcelable instead of Serializable for performance reasons
	//

	public int describeContents() {
		return 0;
	}

	public LoginData(Parcel source) {
		host = source.readString();
		userid = source.readString();
		password = source.readString();
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(host);
		dest.writeString(userid);
		dest.writeString(password);
	}

	public static final Parcelable.Creator<LoginData> CREATOR = new Parcelable.Creator<LoginData>() {
		public LoginData createFromParcel(Parcel source) {
			return new LoginData(source);
		}

		public LoginData[] newArray(int size) {
			return new LoginData[size];
		}
	};
}
