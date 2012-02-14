package tder.main;

import android.os.CountDownTimer;
import android.widget.Toast;

public class GpsEventTimer extends CountDownTimer {
	// Fields
	GpsSubsystemConfiguration gpsSubsystemConfiguration;
	AreaType area;

	public GpsEventTimer(long millisInFuture, long countDownInterval,
			GpsSubsystemConfiguration gpsSubsystemConfiguration) {
		super(millisInFuture, countDownInterval);
		this.gpsSubsystemConfiguration = gpsSubsystemConfiguration;
	}

	@Override
	public void onFinish() {
		Toast.makeText(gpsSubsystemConfiguration.context,
				"Now " + area.display, Toast.LENGTH_LONG)
				.show();

		ApplicationServices applicationServices = new ApplicationServices(gpsSubsystemConfiguration.context);
		switch (area) {
		case HOME:
			applicationServices.ComputerWake();
			break;
		case AWAY:
			applicationServices.ComputerAway();
			break;
		default:
			break;
		}
	}

	@Override
	public void onTick(long millisUntilFinished) {
		// Do nothing
	}

}
