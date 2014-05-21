package com.javi.earthquakes;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EarthquakesDBOpenHelper extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME = "earthquakes.db";
	public static final String DATABASE_TABLE = "Earthquakes";
	public static final int DATABASE_VERSION = 1;
	
	public static final String ID = "_id";
	public static final String ID_STR = "id_str";
	public static final String PLACE = "place";
	public static final String TIME = "time";
	public static final String DETAIL = "detail";
	public static final String MAGNITUDE = "magnitude";
	public static final String  LAT = "lat";
	public static final String LONG = "long";
	public static final String URL = "url";
	public static final String CREATED_AT = "created_at";
	public static final String UPDATED_AT = "updated_at";
	
	private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+"("+ID+"  INTEGER PRIMARY KEY AUTOINCREMENT,"+
																								ID_STR + " TEXT UNIQUE,"+
																								PLACE+" TEXT,"+
																								TIME+" INTEGER,"+
																								DETAIL+" TEXT,"+
																								MAGNITUDE+" REAl,"+
																								LAT+" REAL,"+
																								LONG+" REAL,"+
																								URL+" TEXT,"+
																								CREATED_AT+" INTEGER,"+
																								UPDATED_AT+" INTEGER)";
	
	public EarthquakesDBOpenHelper(Context context, String name,
			CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Simplest case is to drop the old table and create a new one.
		  db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		  // Create a new one.
		  onCreate(db);
		
	}

}
