package tder.main;

import android.app.Activity;
import android.content.Context;
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

		final Context context = this;
		
		
		// Load field values from saved state
		MySaveData save = MySaveData.Load(this);

		final EditText editTextAuthenticationServer = (EditText) findViewById(R.id.editAuthenticationServer);
		final EditText editTextAuthenticationUserID = (EditText) findViewById(R.id.editAuthenticationUserID);
		final EditText editTextAuthenticationPassword = (EditText) findViewById(R.id.editAuthenticationPassword);

		final EditText editTextHomeLatitude = (EditText) findViewById(R.id.editHomeLatitude);
		final EditText editTextHomeLongitude = (EditText) findViewById(R.id.editHomeLongitude);
		final EditText editTextHomeRadius = (EditText) findViewById(R.id.editHomeRadius);
		
		ServiceConfiguration serviceConfiguration = save.serviceConfiguration;
		editTextHomeLatitude.setText("" + serviceConfiguration.homeLatitude);
		editTextHomeLongitude.setText("" + serviceConfiguration.homeLongitude);
		editTextHomeRadius.setText("" + serviceConfiguration.homeRadius);
		
		LoginData loginData = serviceConfiguration.loginData;
		editTextAuthenticationServer.setText(loginData.host);
		editTextAuthenticationUserID.setText(loginData.userid);
		editTextAuthenticationPassword.setText(loginData.password);
		

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
				ApplicationServices.Login(context, callback, loginData);
			}
		});
		
		final Button buttonHomeSave = (Button) findViewById(R.id.buttonHomeSave);
		buttonHomeSave.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Saves new home coordinates

				final EditText editTextLatitude = (EditText) findViewById(R.id.editHomeLatitude);
				final EditText editTextLongitude = (EditText) findViewById(R.id.editHomeLongitude);
				final EditText editTextRadius = (EditText) findViewById(R.id.editHomeRadius);
				
				ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
				serviceConfiguration.enable = true;
				try {
					serviceConfiguration.homeLatitude = Double.parseDouble(editTextLatitude.getText().toString());
					serviceConfiguration.homeLongitude = Double.parseDouble(editTextLongitude.getText().toString());
					serviceConfiguration.homeRadius = Float.parseFloat(editTextRadius.getText().toString());
				} catch (NumberFormatException e) {
					return;
				}

				// Update coordinates on application services layer
				ApplicationServices.UpdateCoordinates(context, serviceConfiguration);
			}
		});
	}
}
