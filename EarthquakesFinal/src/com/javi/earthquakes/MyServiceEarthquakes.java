package com.javi.earthquakes;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyServiceEarthquakes extends Service {
	public MyServiceEarthquakes() {
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				Log.d("MYSERVICE", " ejecucion de un nuevo thread SERVICIO");
				JSONObject json=MiJSON.realizarConsulta();
				//Log.d("JSON", json.toString());
				try {
				JSONArray arrayFeatures = json.getJSONArray("features");
				for(int i=0; i< arrayFeatures.length(); i++) {
					Quakes q = new Quakes();
						JSONObject earthquake = arrayFeatures.getJSONObject(i);
						JSONObject propiedades =  earthquake.getJSONObject("properties");
						JSONArray coordinates =  earthquake.getJSONObject("geometry").getJSONArray("coordinates");
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
						Log.d("MYSERVICE",""+q.toString());
						Date date = new Date();
						ContentValues newValues = new ContentValues();
					    newValues.put(MyContentProvider.ID_STR, q.getId_str());
					    newValues.put(MyContentProvider.PLACE, q.getPlace());
					    newValues.put(MyContentProvider.TIME, q.getTime());
					    newValues.put(MyContentProvider.DETAIL, q.getDetail());
					    newValues.put(MyContentProvider.MAGNITUDE, q.getMagnitude());
					    newValues.put(MyContentProvider.LAT, q.getLat());
					    newValues.put(MyContentProvider.LONG, q.getLongi());
					    newValues.put(MyContentProvider.URL, q.getUrl());
					    newValues.put(MyContentProvider.CREATED_AT, String.valueOf(date.getTime()));
					    newValues.put(MyContentProvider.UPDATED_AT, String.valueOf(date.getTime())); 

						ContentResolver cr = getApplicationContext().getContentResolver();
						cr.insert(MyContentProvider.CONTENT_URI, newValues);
				}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stopSelf();
				Log.d("MYSERVICE", "  stop thread SERVICIO");
			}
		});
		t.start();
	    return Service.START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
