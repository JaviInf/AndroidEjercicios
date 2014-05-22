package com.javi.earthquakes;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EarthQuakeBD {
	
	private EarthquakesDBOpenHelper earthQuakesBDOpenHelper;
	private SQLiteDatabase database;
	
	public EarthQuakeBD(Context context) {
		super();
		DatabaseErrorHandler errorHandler = null;
		earthQuakesBDOpenHelper = new EarthquakesDBOpenHelper(context,
				EarthquakesDBOpenHelper.DATABASE_NAME, null,
				EarthquakesDBOpenHelper.DATABASE_VERSION, errorHandler);
		//	Log.d("EARTHQUAKEBD","CREACION INSTANCIA EarthQuakeDBOpenHelper");
		
		this.open();
	}

	public void open() {
		database = earthQuakesBDOpenHelper.getWritableDatabase();
		//Log.d("EARTHQUAKEBD","OPEN de la BD");
	}

	public void close() {
		database.close();
	//	Log.d("EARTHQUAKEBD","CLOSE de la BD");
	}

	public void insert(int id, String idstr, String place, Integer time, String detail,
			double magnitude, double lat, double lng, String url,
			Integer created_at, Integer updated_at) {

		ContentValues nuevosValores = new ContentValues();
		nuevosValores.put(EarthquakesDBOpenHelper.ID_STR, idstr);
		nuevosValores.put(EarthquakesDBOpenHelper.PLACE, place);
		nuevosValores.put(EarthquakesDBOpenHelper.TIME, time);
		nuevosValores.put(EarthquakesDBOpenHelper.DETAIL, detail);
		nuevosValores.put(EarthquakesDBOpenHelper.MAGNITUDE, magnitude);
		nuevosValores.put(EarthquakesDBOpenHelper.LAT, lat);
		nuevosValores.put(EarthquakesDBOpenHelper.LONG, lng);
		nuevosValores.put(EarthquakesDBOpenHelper.URL, url);
		nuevosValores.put(EarthquakesDBOpenHelper.CREATED_AT, created_at);
		nuevosValores.put(EarthquakesDBOpenHelper.UPDATED_AT, updated_at);

		long ok = database.insert(EarthquakesDBOpenHelper.DATABASE_TABLE, null,
				nuevosValores);
		Log.d("EARTHQUAKEBD",
				"INSERT en la BD con estado: " + String.valueOf(ok));
	}
	public void insert(Quakes quake) {

		ContentValues nuevosValores = new ContentValues();
		nuevosValores.put(EarthquakesDBOpenHelper.ID_STR, quake.getId_str());
		nuevosValores.put(EarthquakesDBOpenHelper.PLACE, quake.getPlace());
		nuevosValores.put(EarthquakesDBOpenHelper.TIME, quake.getTime());
		nuevosValores.put(EarthquakesDBOpenHelper.DETAIL, quake.getDetail());
		nuevosValores.put(EarthquakesDBOpenHelper.MAGNITUDE, quake.getMagnitude());
		nuevosValores.put(EarthquakesDBOpenHelper.LAT, quake.getLat());
		nuevosValores.put(EarthquakesDBOpenHelper.LONG, quake.getLongi());
		nuevosValores.put(EarthquakesDBOpenHelper.URL, quake.getUrl());
		nuevosValores.put(EarthquakesDBOpenHelper.CREATED_AT, quake.getCreated_at());
		nuevosValores.put(EarthquakesDBOpenHelper.UPDATED_AT, quake.getUpdated_at());

		long ok = database.insert(EarthquakesDBOpenHelper.DATABASE_TABLE, null,
				nuevosValores);
		Log.d("EARTHQUAKEBD",
				"INSERT en la BD con estado: " + String.valueOf(ok));
	}
	
	
	public void delete(String where, String[] whereArgs) {
		database.delete(EarthquakesDBOpenHelper.DATABASE_TABLE, where,
				whereArgs);
		//Log.d("EARTHQUAKEBD", "DELETE en la BD de la tupla : "+where+" ="+whereArgs);
	}
	
	public Cursor queryTerremotos(String[] columns, String groupBy, String[] whereArgs, String order, String having, String where, String[] result_columns){
		Cursor cursor=database.query(EarthquakesDBOpenHelper.DATABASE_TABLE,result_columns, where,whereArgs, groupBy, having, order);
		return cursor;
	}
	
	public ArrayList<Quakes> getTerremotos(double magnitude){
		ArrayList<Quakes> list= new ArrayList<Quakes>();
		String whereArgs[]={String.valueOf(magnitude)};
		String where = EarthquakesDBOpenHelper.MAGNITUDE + ">=?";
        String groupBy = null;
        String having = null;

         Cursor cursor = database.query(EarthquakesDBOpenHelper.DATABASE_TABLE,null, where,whereArgs, groupBy, having, EarthquakesDBOpenHelper.TIME+ " DESC");
         while (cursor.moveToNext()) {
     
             int QUAKE_DATE_COLUMN_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.TIME);
             Long timeQuake = cursor.getLong(QUAKE_DATE_COLUMN_INDEX);  
             
             int QUAKE_PLACE_COLUMN_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.PLACE);
             String placeQuake = cursor.getString(QUAKE_PLACE_COLUMN_INDEX);
             
             int QUAKE_DETAILS_COLUMN_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.DETAIL);   
             String quakeDetails= cursor.getString(QUAKE_DETAILS_COLUMN_INDEX);  
             
             int QUAKE_LOCATION_LAT_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.LAT);         
             float LatQuake = cursor.getFloat(QUAKE_LOCATION_LAT_INDEX);  
             
             int QUAKE_LOCATION_LONG_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.LONG);             
             float LongQuake= cursor.getFloat(QUAKE_LOCATION_LONG_INDEX);
             
             int QUAKE_MAGNITUDE_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.MAGNITUDE);
             double magnitudeQuake= cursor.getDouble(QUAKE_MAGNITUDE_INDEX);
             
             int QUAKE_DIRECCION_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.URL);     
             String directionQuake= cursor.getString(QUAKE_DIRECCION_INDEX);
             
             Quakes quake = new Quakes();
             quake.setTime(timeQuake);
             quake.setPlace(placeQuake);
             quake.setDetail(quakeDetails);
             quake.setLat(LatQuake);
             quake.setLongi(LongQuake);
             quake.setMagnitude(magnitudeQuake);
             quake.setUrl(directionQuake);
             
             list.add(quake);    
            }
           cursor.close();
        return list;
	}

	public void update(int id, String columns[], String []values) {
		ContentValues valoresActualizados = new ContentValues();
		String where = EarthquakesDBOpenHelper.ID + "=?";
		String whereArgs[]={String.valueOf(id)};
		
		for (int i = 0; i < columns.length; i++) {
			valoresActualizados.put(columns[i], values[i]);
		}
		
		database.update(EarthquakesDBOpenHelper.DATABASE_TABLE,
				valoresActualizados, where, whereArgs);

	}

	
}
