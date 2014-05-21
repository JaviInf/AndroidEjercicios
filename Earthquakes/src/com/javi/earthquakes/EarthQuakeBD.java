package com.javi.earthquakes;

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
			Log.d("EARTHQUAKEBD","CREACION INSTANCIA EarthQuakeDBOpenHelper");
	}

	public void open() {
		database = earthQuakesBDOpenHelper.getWritableDatabase();
		Log.d("EARTHQUAKEBD","OPEN de la BD");
	}

	public void close() {
		database.close();
		Log.d("EARTHQUAKEBD","CLOSE de la BD");
	}

	public void insert(int id, String idstr, String place, Integer time, String detail,
			double magnitude, double lat, double lng, String url,
			Integer created_at, Integer updated_at) {

		ContentValues nuevosValores = new ContentValues();
		//nuevosValores.put(EarthquakesDBOpenHelper.ID, id);
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
		//nuevosValores.put(EarthquakesDBOpenHelper.ID, quake.getId());
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
		// Specify a where clause that determines which row(s) to delete.
		// Specify where arguments as necessary.
		// Delete the rows that match the where clause.
		database.delete(EarthquakesDBOpenHelper.DATABASE_TABLE, where,
				whereArgs);
		Log.d("EARTHQUAKEBD", "DELETE en la BD de la tupla : "+where+" ="+whereArgs);
	}
	
	public Cursor queryTerremotos(String[] columns, String groupBy, String[] whereArgs, String order, String having, String where, String[] result_columns){
		Cursor cursor=database.query(EarthquakesDBOpenHelper.DATABASE_TABLE,result_columns, where,whereArgs, groupBy, having, order);
		Log.d("EARTHQUAKEBD", "QUERY de terremotos");
		return cursor;
	}

	public void update(int id, String columns[], String []values) {
		// Create the updated row Content Values.
		ContentValues valoresActualizados = new ContentValues();
		// Specify a where clause the defines which rows should be // updated.
		// Specify where arguments as necessary.
		String where = EarthquakesDBOpenHelper.ID + "=?";
		String whereArgs[]={String.valueOf(id)};
		
		for (int i = 0; i < columns.length; i++) {
			valoresActualizados.put(columns[i], values[i]);
		}
		
		// Update the row with the specified index with the new values.
		database.update(EarthquakesDBOpenHelper.DATABASE_TABLE,
				valoresActualizados, where, whereArgs);

	}

	
}
