package com.javi.mapasandroid;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyLocationListenerw implements LocationListener{
public String miPunto="";
	@Override
	public void onLocationChanged(Location location) {
		if (location != null) {
	        double lat = location.getLatitude();
	        double lon = location.getLongitude();
	        String coordenadas = "Se ha movido a: " + "Latitud = " + lat + "Longitud = " + lon;	
	        Log.d("LOCATIONLISTENER", coordenadas);
	        miPunto=coordenadas;      
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
