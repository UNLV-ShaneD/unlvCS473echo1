package tder.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ServerInterfacer {
	// Executes a http get request and returns the html string
	static private String Get(String uri) throws ClientProtocolException,
			IOException, URISyntaxException, Exception {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		request.setURI(new URI(uri));
		HttpResponse response = client.execute(request);

		InputStream in = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			str.append(line);
		}

		in.close();
		return str.toString();
	}

	// Executes login command
	// Must be called in a non-main thread
	static public LoginCallback.ResultType Login(LoginData loginData) {
		try {
			// Perform get-based authentication
			String html = Get(loginData.getAuthenticationString());
		} catch (URISyntaxException e) {
			return LoginCallback.ResultType.RESULT_FAIL_BADURI;
		} catch (ClientProtocolException e) {
			return LoginCallback.ResultType.RESULT_FAIL_GENERAL;
		} catch (IOException e) {
			return LoginCallback.ResultType.RESULT_FAIL_BADSERVER;
		}catch (Exception e) {
			// Can't figure out what other exceptions are being thrown when a
			// bad URI is used, so just catch them all
			return LoginCallback.ResultType.RESULT_FAIL_BADURI;
		}

		return LoginCallback.ResultType.RESULT_SUCCESS;
	}
}
