package tder.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyStartupIntentReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//Launch our GPS service
		GpsServiceInterfacer.Start(context);
		
		System.out.println("MyStartupIntentReceiver invoked!\n");
		
//		Intent serviceIntent = new Intent();
//		serviceIntent.setAction("com.wissen.startatboot.MyService");
//		context.startService(serviceIntent);
	}

}
