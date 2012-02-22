package tder.main;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginData implements Parcelable, Serializable {
	// Constants
	private static final long serialVersionUID = 7557766844250154733L;
	
	// Fields
	String host;
	String userid;
	String password;

	public String getAuthenticationString() throws URISyntaxException {
		URI uri;
		uri = new URI("http", null, host, 8080, "/CS673Project1DynamicWebProject/DroidServlet", "action=verify&username=" + userid + "&password=" + password,
				null);
//			uri = new URI("http", host, /login, fragment)
		return uri.toString();
	}

	public String getCommandString(ServerCommandType serverCommandType) throws URISyntaxException {
		URI uri;
		uri = new URI("http", null, host, 8080, "/CS673Project1DynamicWebProject/DroidServlet", "action=" + serverCommandType.action + "&username=" + userid + "&password=" + password,
				null);
		return uri.toString();
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
