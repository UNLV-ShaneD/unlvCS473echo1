package tder.main;

import android.content.Context;
import android.widget.TextView;

// Class used as parameter to ApplicationServices.Login
// Is run() when login procedure is complete
public class LoginCallback implements Runnable {
	
	// Postback updates a textview to display status of the login
	private final class Postback implements Runnable {
		private final int message;

		private Postback(int message) {
			this.message = message;
		}

		public void run() {
			String localizedMessage = context.getString(message);
			textViewAuthenticationStatus.setText(localizedMessage);
		}
	}

	// Fields
	ServerResultType result;
	final Context context;
	final TextView textViewAuthenticationStatus;
	
	public LoginCallback(Context context, TextView textViewAuthenticationStatus) {
		this.context = context;
		this.textViewAuthenticationStatus = textViewAuthenticationStatus;
	}
	
	public void run() {
		final int message = result.message;
		
		// Runnable for posting to the textView to show status
		final Runnable postStatus = new Postback(message);
		
		textViewAuthenticationStatus.post(postStatus);
	}
}
