package tder.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class ActivityMain extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Disable GPS service
		// GpsServiceInterfacer.Disable(this);

		MySaveData save = MySaveData.Load(this);
		save.serviceConfiguration.enable = true;
		GpsServiceInterfacer.Invoke(this, save.serviceConfiguration);

		// Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Reusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab, then add it to
		// the tabHost
		intent = new Intent().setClass(this, ActivityCommand.class);
		spec = tabHost.newTabSpec("command")
				.setIndicator("Command")
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, ActivityConfigure.class);
		spec = tabHost.newTabSpec("configure")
				.setIndicator("Configure")
				.setContent(intent);
		tabHost.addTab(spec);

		// intent = new Intent().setClass(this, SongsActivity.class);
		// spec = tabHost
		// .newTabSpec("songs")
		// .setIndicator("Songs", res.getDrawable(R.drawable.ic_tab_songs))
		// .setContent(intent);
		// tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}
}
