package com.javi.earthquakes;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EarthquakesDBOpenHelper extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME = "earthquakes.db";
	private static final String DATABASE_TABLE = "Earthquakes";
	public static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+"(_id PRIMARY KEY,"+
																								"place TEXT,"+
																								"time INTEGER,"+
																								"detail TEXT,"+
																								"magnitude REAl,"+
																								"lat REAL,"+
																								"long REAL,"+
																								"url TEXT,"+
																								"created_at INTEGER,"+
																								"updated_at INTEGER)";
	
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
