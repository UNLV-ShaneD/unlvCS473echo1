package tder.main;

import android.content.Context;

public final class DomainUpdateCoordinates implements Runnable {
	private final Context context;
	private final ServiceConfiguration serviceConfiguration;

	public DomainUpdateCoordinates(Context context, ServiceConfiguration serviceConfiguration) {
		this.context = context;
		this.serviceConfiguration = serviceConfiguration;
	}

	public void run() {

		MySaveData save = MySaveData.Load(context);
		serviceConfiguration.loginData = save.serviceConfiguration.loginData;
		save.serviceConfiguration = serviceConfiguration;
		save.Save(context);
		GpsServiceInterfacer.Invoke(context, save.serviceConfiguration);
	}
}