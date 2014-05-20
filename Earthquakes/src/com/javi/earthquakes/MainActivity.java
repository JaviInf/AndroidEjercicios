package com.javi.earthquakes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseErrorHandler errorHandler = null;
		
		
//		EarthquakesDBOpenHelper bd= new EarthquakesDBOpenHelper(this, EarthquakesDBOpenHelper.DATABASE_NAME, null, EarthquakesDBOpenHelper.DATABASE_VERSION, errorHandler);
//		bd.getWritableDatabase();
//		
		EarthQuakeBD bd=new EarthQuakeBD(this);
		bd.open();
		bd.insert(1, "prueba", 2, "detalle prueba", 4.34, 2.23,1.2, "web", 33, 11);
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				Log.d("TAG", " ejecucion de un nuevo thread");
				realizarConsulta();
			}

			private void realizarConsulta() {
				Log.d("TAG", "se esta realizando la consulta");
				String myFeed = "http://www.arkaitzgarro.com/android/photos.json";
				try {
					URL url = new URL(myFeed);

					// Create a new HTTP URL connection
					URLConnection connection = url.openConnection();
					HttpURLConnection httpConnection = (HttpURLConnection) connection;
					int responseCode = httpConnection.getResponseCode();
					if (responseCode == HttpURLConnection.HTTP_OK) {
						Log.d("JSON", "CONSULTA SATISFACTORIA");
						InputStream in = httpConnection.getInputStream();
						procesarConsulta(in);

					}
				} catch (MalformedURLException e) {
					Log.d("TAG", "Malformed URL Exception.", e);
				} catch (IOException e) {
					Log.d("TAG", "IO Exception.", e);
				}

				
			}

			private void procesarConsulta(InputStream in) {
				// TODO Auto-generated method stub
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

				Log.d("TAG", sb.toString());
				try {
					JSONObject json = new JSONObject(sb.toString());
					procesareJSON(json);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void procesareJSON(JSONObject json) {
				String[] imagenes;
				try {
					JSONArray fotos = json.getJSONArray("photos");
					// Log.d("TAG","fotos.....:   "+fotos);
					imagenes = new String[fotos.length()];
					for (int i = 0; i < fotos.length(); i++) {
						JSONObject imagen = fotos.getJSONObject(i);
						imagenes[i] = imagen.getString("image_url");
						Log.i("TAG", "imagen en la posicion array " + i + ","
								+ imagenes[i]);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		t.start();
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
