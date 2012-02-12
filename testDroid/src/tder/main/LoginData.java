package tder.main;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class LoginData implements Serializable {
	private static final long serialVersionUID = -3211440917525545265L;
	String host;
	String userid;
	String password;
	
	public String getAuthenticationString() throws URISyntaxException {
		URI uri;
		try {
			uri = new URI("http", null, host, 80, "/login", URLEncoder.encode("userid=" + userid + "&password=" + password, "UTF-8"), null);
			return uri.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
