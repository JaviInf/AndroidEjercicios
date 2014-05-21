package com.javi.earthquakes;



import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends Activity {
	
	private static final int SHOW_PREFERENCES = 0;
	EarthQuakeBD bd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseErrorHandler errorHandler = null;
		
		

		bd=new EarthQuakeBD(this);
		bd.open();
		this.getEarthQuakes();
		Log.d("TAGAGGAG", "lklkl–kl–kl–kl–");
		bd.getTerremotos(0);

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
			Intent intent = new Intent(MainActivity.this, PreferenciasActivity.class);
			startActivityForResult(intent, SHOW_PREFERENCES);
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
	
	private void getEarthQuakes() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Log.d("JSON", "Json en ejecucion");
				JSONObject json=MiJSON.realizarConsulta();
				Log.d("JSON", json.toString());
				try {
				JSONArray arrayFeatures = json.getJSONArray("features");
				for(int i=0; i< arrayFeatures.length(); i++) {
					Quakes q = new Quakes();
						JSONObject earthquake = arrayFeatures.getJSONObject(i);
					//	Log.d("JSON  earthquake", earthquake.toString());
						JSONObject propiedades =  earthquake.getJSONObject("properties");
					//	Log.d("JSON  propiedades", propiedades.toString());
						JSONArray coordinates =  earthquake.getJSONObject("geometry").getJSONArray("coordinates");
					//	Log.d("JSON  coordinates", coordinates.toString());
						
						// Crear Quake
						q.setId_str(earthquake.getString("id"));
						q.setPlace(propiedades.getString("place"));
						q.setTime(propiedades.getLong("time"));
						q.setDetail(propiedades.getString("detail"));
						q.setMagnitude(propiedades.getDouble("mag"));
						q.setLat(coordinates.getDouble(1));
						q.setLongi(coordinates.getDouble(0));
						q.setUrl(propiedades.getString("url"));
						q.setCreated_at(Long.valueOf((new Date().getTime())));
						q.setUpdated_at(Long.valueOf((new Date().getTime())));
						
						bd.insert(q);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

}
