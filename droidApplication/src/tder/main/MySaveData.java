package tder.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context;

public class MySaveData implements Serializable {
	// Constants
	private static final long serialVersionUID = -6102244456669174604L;
	private static final String filename = "persistent";

	// Fields
	ServiceConfiguration serviceConfiguration = new ServiceConfiguration();

	static public MySaveData Load(Context context) {
		try {
			FileInputStream fis = context.openFileInput(filename);
			ObjectInputStream in = new ObjectInputStream(fis);
			return (MySaveData) in.readObject();
		} catch (IOException e) {
			return new MySaveData();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return new MySaveData();
	}

	public void Save(Context context) {
		try {
			FileOutputStream fos = context.openFileOutput("persistent",
					Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			fos.close();
		} catch (IOException e) {

		}
	}
	
	public LoginData readLoginData() {
		if (serviceConfiguration == null)
			return null;
		return serviceConfiguration.readLoginData();
	}
}
