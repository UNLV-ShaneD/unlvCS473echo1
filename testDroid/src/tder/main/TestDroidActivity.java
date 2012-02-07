package tder.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TestDroidActivity extends ListActivity {
	
	static String[] TARGETS = new String[] {
		"shaneserv", "shanenet", "sed"
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, TARGETS));
        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final TextView frmTV = (TextView)findViewById(R.id.textView1);
        final Button frmButWake = (Button)findViewById(R.id.buttonWake);
        

        frmButWake.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v)
        	{
        		new Thread(new Runnable() {
        			public void run() {
	            		// HACK: Perform click action directly (Yay for mixing the interface and application services layers!)
	            		WakeOnLan wol = new WakeOnLan();
	            		wol.Wake("00:24:8C:9E:17:B4");
	            		
	            		try
	            		{
	    	        		HttpClient client = new DefaultHttpClient();
	    	        		HttpGet request = new HttpGet();
	    	        		request.setURI(new URI("http://www.google.com/"));
	    	        		HttpResponse response = client.execute(request);
	    	        		
	    	        		InputStream in = response.getEntity().getContent();
	    	        		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	    	        		StringBuilder str = new StringBuilder();
	    	        		String line = null;
	    	        		while ((line = reader.readLine()) != null)
	    	        		{
	    	        			str.append(line);
	    	        		}
	    				    
	    				    in.close();
	    				    final String html = str.toString();
	    
	    			        final TextView frmTV = (TextView)findViewById(R.id.textView1);
	    				    frmTV.post(new Runnable() {
	    				    	public void run() {
	    				    		frmTV.setText("Done.");
	    				    		}
	    				    });
	            		}
	            		catch (ClientProtocolException e)
	            		{
	    					e.printStackTrace();
	            		} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				} catch (URISyntaxException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
        			}
        		}).start();
        		
        	}
        });
    }
}