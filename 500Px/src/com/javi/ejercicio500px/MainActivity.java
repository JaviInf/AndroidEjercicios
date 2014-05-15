package com.javi.ejercicio500px;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button boton = (Button) findViewById(R.id.consultaJson);
		boton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						realizarConsulta();
					}

				});
				t.start();
			}
		});
	}
	
	public void realizarConsulta() {
		String myFeed = "http://www.arkaitzgarro.com/android/photos.json";
		try {
		  URL url = new URL(myFeed);
		
		// Create a new HTTP URL connection
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection)connection;
		int responseCode = httpConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
		  InputStream in = httpConnection.getInputStream();
		  processStream(in);
		}
		}
		catch (MalformedURLException e) {
		  Log.d("TAG", "Malformed URL Exception.", e);
		}
		catch (IOException e) {
		  Log.d("TAG", "IO Exception.", e);
		}
		
	}

	private void processStream(InputStream in) {
		// TODO Auto-generated method stub
		
	}

}
