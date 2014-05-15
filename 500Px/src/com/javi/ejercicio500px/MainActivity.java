package com.javi.ejercicio500px;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 Log.d("TAG", " ejecucion de on create");
		 
		Button boton = (Button) findViewById(R.id.consultaJson);
		boton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				 Log.d("TAG", " creacion de un nuevo thread");
				Thread t = new Thread(new Runnable() {
					public void run() {
						 Log.d("TAG", " ejecucion de un nuevo thread");
						realizarConsulta();
					}
				});
				t.start();
			}
		});
	}
	
	public void realizarConsulta() {
		 Log.d("TAG", "se esta realizando la consulta");
		String myFeed = "http://www.arkaitzgarro.com/android/photos.json";
		try {
		  URL url = new URL(myFeed);
		
		// Create a new HTTP URL connection
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection)connection;
		int responseCode = httpConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			Log.d("TAG", "CONSULTA SATISFACTORIA");
		  InputStream in = httpConnection.getInputStream();
		  procesarConsulta(in);
		  
		}
		}
		catch (MalformedURLException e) {
		  Log.d("TAG", "Malformed URL Exception.", e);
		}
		catch (IOException e) {
		  Log.d("TAG", "IO Exception.", e);
		}
		
	}

	private void procesarConsulta(InputStream in) {
		Log.d("TAG", "empieza procesamiento de respuesta");
		 BufferedReader br = new BufferedReader(new InputStreamReader(in));
         StringBuilder sb = new StringBuilder();
         String line = null;
          
         try {
			while ((line = br.readLine()) != null) {
			        sb.append(line + "\n");
			    }
		} catch (IOException e) {
			e.printStackTrace();
		}
          
         Log.d("TAG",sb.toString());
         try {
			JSONObject json= new JSONObject(sb.toString());
			procesareJSON(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void procesareJSON(JSONObject json) {
		 String[] imagenes;
		 try {
			JSONArray fotos=(JSONArray)json.getJSONArray("photos");
			//  Log.d("TAG","fotos.....:   "+fotos);
			imagenes= new String[fotos.length()];
			for(int i=0; i<fotos.length(); i++){
				JSONObject imagen= fotos.getJSONObject(i);
				 imagenes[i]=(String) imagen.get("image_url");
                 Log.i("TAG","imagen en la posicion array "+i+","+imagenes[i]);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
