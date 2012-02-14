package tder.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context;

public class MySaveData implements Serializable {
	private static final long serialVersionUID = -6102244456669174604L;

	ServiceConfiguration serviceConfiguration = new ServiceConfiguration();

	static final String filename = "persistent";

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

		return null;
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
}
