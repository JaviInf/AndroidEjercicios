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
import android.content.Intent;
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
	
	private static final int SHOW_PREFERENCES = 0;
	
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
		
		//JSONObject json =  ResourceParser.getJSONObject("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");

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
				JSONObject json =  MiJSON.realizarConsulta("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");
				try {
					JSONArray arrayFeatures = json.getJSONArray("features");
					for(int i=0; i< arrayFeatures.length(); i++) {
						JSONObject eq = arrayFeatures.getJSONObject(i);
						JSONObject props =  eq.getJSONObject("properties");
						JSONArray coordinates =  eq.getJSONObject("geometry").getJSONArray("coordinates");
						
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
