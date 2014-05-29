package com.javi.mapasandroid;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	Location location;
	LocationManager locationManager;
	MyLocationListener mylocation;
	MyLocationListener miLocation;

	TextView lat;
	TextView longi;
	TextView alt;
	Button boton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		
		 lat= (TextView) findViewById(R.id.latitud);
		 longi= (TextView) findViewById(R.id.longitud);
		 alt= (TextView) findViewById(R.id.altitud);
		 boton= (Button)findViewById(R.id.button1);
		
		String serviceString = Context.LOCATION_SERVICE;
		locationManager = (LocationManager)getSystemService(serviceString);
		miLocation=new MyLocationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, miLocation);
		
		boton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
			miLocation.onLocationChanged(location);
			Log.d("LOCATION",miLocation.miPunto);
			
			}
		});
		
	    if(location==null){
	    	Log.d("LOCATION", " no hay posicion");
	    }
	    else
	    {
	    	lat.setText(String.valueOf(location.getLatitude()));
			longi.setText(String.valueOf(location.getLongitude()));
			lat.setText(String.valueOf(location.getAltitude()));
	    	}
	
		
	}
	
	public void actualizaPunto(String latit){
		lat.setText(latit);
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
	public class MyLocationListener implements LocationListener{
		public String miPunto="";
			@Override
			public void onLocationChanged(Location location) {
				if (location != null) {
			        double lat = location.getLatitude();
			        double lon = location.getLongitude();
			        String coordenadas = "Latitud = " + lat + "Longitud = " + lon;
			        actualizaPunto(coordenadas);
				}
			        
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
		}
}
