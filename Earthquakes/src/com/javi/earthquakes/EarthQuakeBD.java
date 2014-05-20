package com.javi.earthquakes;
import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class EarthQuakeBD {
	private EarthquakesDBOpenHelper earthQuakesBDOpenHelper;
	 private SQLiteDatabase database;
	 
	public EarthQuakeBD(Context context) {
		super();
		DatabaseErrorHandler errorHandler = null;
		earthQuakesBDOpenHelper= new  EarthquakesDBOpenHelper(context,
				 EarthquakesDBOpenHelper.DATABASE_NAME, null,
				 EarthquakesDBOpenHelper.DATABASE_VERSION, errorHandler);
	}

	public void open() {
		 database= earthQuakesBDOpenHelper.getWritableDatabase();
	}

	public void close() {
		database.close();
	}

	public void query() {
	

	}

	public void insert(int id, String place, Integer time, String detail, double magnitude, double lat, double lng, String url, Integer created_at, Integer updated_at ) {

		 ContentValues nuevosValores = new ContentValues();
		 nuevosValores.put(EarthquakesDBOpenHelper.ID, id);
		 nuevosValores.put(EarthquakesDBOpenHelper.PLACE, place);
		 nuevosValores.put(EarthquakesDBOpenHelper.TIME, time);
		 nuevosValores.put(EarthquakesDBOpenHelper.DETAIL, detail);
		 nuevosValores.put(EarthquakesDBOpenHelper.MAGNITUDE,magnitude);
		 nuevosValores.put(EarthquakesDBOpenHelper.LAT, lat);
		 nuevosValores.put(EarthquakesDBOpenHelper.LONG, lng);
		 nuevosValores.put(EarthquakesDBOpenHelper.URL, url);
		 nuevosValores.put(EarthquakesDBOpenHelper.CREATED_AT, created_at);
		 nuevosValores.put(EarthquakesDBOpenHelper.UPDATED_AT, updated_at);
		 
		 long ok=database.insert(EarthquakesDBOpenHelper.DATABASE_TABLE, null, nuevosValores);
		 Log.d("EARTHQUAKEBD","INSERT en la BD con estado: " + String.valueOf(ok));
		 
		 


	}

	public void update() {

	}

	public void delete() {

	}
}
