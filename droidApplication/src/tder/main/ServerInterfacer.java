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
	private String Get(String uri) throws ClientProtocolException,
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
	public ServerResultType Login(LoginData loginData) {
		try {
			// Perform get-based authentication
			String authenticationString = loginData.getAuthenticationString();
			
			System.out.println("Using authentication string >" + authenticationString + "<");
			
			String html = Get(authenticationString);
			
			if (html.equals("bad")){
				return ServerResultType.RESULT_FAIL_BADLOGIN;
			}
			
		} catch (URISyntaxException e) {
			return ServerResultType.RESULT_FAIL_BADURI;
		} catch (ClientProtocolException e) {
			return ServerResultType.RESULT_FAIL_GENERAL;
		} catch (IOException e) {
			return ServerResultType.RESULT_FAIL_BADSERVER;
		}catch (Exception e) {
			// Can't figure out what other exceptions are being thrown when a
			// bad URI is used, so just catch them all
			return ServerResultType.RESULT_FAIL_BADURI;
		}

		return ServerResultType.RESULT_SUCCESS;
	}
	
	// Executes a command
	// Must be called in a non-main thread
	public void command(LoginData loginData, ServerCommandType serverCommandType) {
		try {
			String uri = loginData.getCommandString(serverCommandType);
			Get(uri);
		} catch (Exception e) {
			// Silent error
		}
	}
}
