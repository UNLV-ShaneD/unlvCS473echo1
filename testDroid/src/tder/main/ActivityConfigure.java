package tder.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityConfigure extends Activity implements View.OnClickListener {
	
	// Fields
	ApplicationServices applicationServices;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configure);
		
		applicationServices = new ApplicationServices(this);
		
		
		// Load field values from saved state
		MySaveData save = MySaveData.Load(this);

		final EditText editTextAuthenticationServer = (EditText) findViewById(R.id.editAuthenticationServer);
		final EditText editTextAuthenticationUserID = (EditText) findViewById(R.id.editAuthenticationUserID);
		final EditText editTextAuthenticationPassword = (EditText) findViewById(R.id.editAuthenticationPassword);

		final EditText editTextHomeLatitude = (EditText) findViewById(R.id.editHomeLatitude);
		final EditText editTextHomeLongitude = (EditText) findViewById(R.id.editHomeLongitude);
		final EditText editTextHomeRadius = (EditText) findViewById(R.id.editHomeRadius);
		
		ServiceConfiguration serviceConfiguration = save.serviceConfiguration;
		if (serviceConfiguration == null) {
			System.out.println("crash 1");
		}
		serviceConfiguration.populateHomeTextViews(editTextHomeLatitude, editTextHomeLongitude, editTextHomeRadius);
		
		LoginData loginData = serviceConfiguration.loginData;
		editTextAuthenticationServer.setText(loginData.host);
		editTextAuthenticationUserID.setText(loginData.userid);
		editTextAuthenticationPassword.setText(loginData.password);
		

		// Set button listeners
		Button button;
		button = (Button) findViewById(R.id.buttonAuthenticationLogin);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonHomeSave);
		button.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonAuthenticationLogin:
			// Test the user's authentication details by logging into the
			// server provided

			// Get user's authentication data
			final LoginData loginData = new LoginData();
			populateLoginData(loginData);

			// Runnable for handling the result of the login request
			final TextView textViewAuthenticationStatus = (TextView) findViewById(R.id.textViewAuthenticationTestStatus);
			final LoginCallback callback = new LoginCallback(this, textViewAuthenticationStatus);

			// Execute login on application services layer
			applicationServices.login(callback, loginData);
			break;
		case R.id.buttonHomeSave:
			// Saves new home coordinates
			ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
			populateServiceConfiguration(serviceConfiguration);

			// Update coordinates on application services layer
			applicationServices.updateCoordinates(serviceConfiguration);
			break;
		}
	}
	
	// Populate LoginData fields
	public void populateLoginData(LoginData loginData) {
		final EditText editTextAuthenticationServer = (EditText) findViewById(R.id.editAuthenticationServer);
		final EditText editTextAuthenticationUserID = (EditText) findViewById(R.id.editAuthenticationUserID);
		final EditText editTextAuthenticationPassword = (EditText) findViewById(R.id.editAuthenticationPassword);

		loginData.host = "" + editTextAuthenticationServer.getText();
		loginData.userid = "" + editTextAuthenticationUserID.getText();
		loginData.password = ""
				+ editTextAuthenticationPassword.getText();
	}
	
	// Populate ServiceConfiguration fields
	public void populateServiceConfiguration(ServiceConfiguration serviceConfiguration) {
		final EditText editTextLatitude = (EditText) findViewById(R.id.editHomeLatitude);
		final EditText editTextLongitude = (EditText) findViewById(R.id.editHomeLongitude);
		final EditText editTextRadius = (EditText) findViewById(R.id.editHomeRadius);
		
		serviceConfiguration.updateFromHomeTextViews(editTextLatitude, editTextLongitude, editTextRadius);
	}
}
