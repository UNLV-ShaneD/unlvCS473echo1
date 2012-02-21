package tder.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityCommand extends Activity implements View.OnClickListener {
	
	// Fields
	ApplicationServices applicationServices;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.command);

		applicationServices = new ApplicationServices(this);
		
		Button button;
		
		button = (Button) findViewById(R.id.buttonWake);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonStandby);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonHibernate);
		button.setOnClickListener(this);
	}

	public void onClick(View v) {
		// Load field values from saved state
		MySaveData save = MySaveData.Load(this);
		LoginData loginData = save.readLoginData();
		
		if (loginData == null)
			return;
		
		switch (v.getId()) {
		case R.id.buttonWake:
			applicationServices.computerCommand(loginData, ServerCommandType.WAKE);
			break;
		case R.id.buttonStandby:
			applicationServices.computerCommand(loginData, ServerCommandType.STANDBY);
			break;
		case R.id.buttonHibernate:
			applicationServices.computerCommand(loginData, ServerCommandType.HIBERNATE);
			break;
		default:
			break;
		}
	}
}

/*
 * new Thread(new Runnable() { public void run() { // HACK: Perform click action
 * directly (Yay for mixing the interface and application services layers!)
 * MagicPacketer magicPacketer = new MagicPacketer(); try {
 * magicPacketer.Wake("192.168.1.13", "00:24:8C:9E:17:B4"); } catch
 * (SocketException e1) { // TODO Auto-generated catch block
 * e1.printStackTrace(); } } }).start();
 */