package tder.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityConfigure extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configure);

		final Button buttonLogin = (Button) findViewById(R.id.buttonAuthenticationLogin);
		buttonLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Test the user's authentication details by logging into the
				// server provided

				final EditText editTextAuthenticationServer = (EditText) findViewById(R.id.editAuthenticationServer);
				final EditText editTextAuthenticationUserID = (EditText) findViewById(R.id.editAuthenticationUserID);
				final EditText editTextAuthenticationPassword = (EditText) findViewById(R.id.editAuthenticationPassword);
				final TextView textViewAuthenticationStatus = (TextView) findViewById(R.id.textViewAuthenticationTestStatus);

				// Get user's authentication data
				final LoginData loginData = new LoginData();

				loginData.host = "" + editTextAuthenticationServer.getText();
				loginData.userid = "" + editTextAuthenticationUserID.getText();
				loginData.password = ""
						+ editTextAuthenticationPassword.getText();

				// Runnable for handling the result of the login request
				final LoginCallback callback = new LoginCallback() {
					public void run() {
						final int message = result.message;
						
						// Runnable for posting to the textView to show status
						final Runnable postStatus = new Runnable() {
							public void run() {
								String localizedMessage = getString(message);
								textViewAuthenticationStatus.setText(localizedMessage);
							}
						};
						
						textViewAuthenticationStatus.post(postStatus);
					}
				};

				// Execute login on application services layer
				ApplicationServices.Login(callback, loginData);
			}
		});
	}
}
