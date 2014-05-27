  package com.javi.earthquakes;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {

	public static final Uri CONTENT_URI = Uri
			.parse("content://com.javi.earthquakes.skeletondatabaseprovider/elements");
	private static final int ALLROWS = 1;
	private static final int SINGLE_ROW = 2;
	private static final UriMatcher uriMatcher;

	private EarthquakesDBOpenHelper myOpenHelper;

	public static final String ID = "_id";
	public static final String ID_STR = "id_str";
	public static final String PLACE = "place";
	public static final String TIME = "time";
	public static final String DETAIL = "detail";
	public static final String MAGNITUDE = "magnitude";
	public static final String LAT = "lat";
	public static final String LONG = "long";
	public static final String URL = "url";
	public static final String CREATED_AT = "created_at";
	public static final String UPDATED_AT = "updated_at";

	// Populate the UriMatcher object, where a URI ending in
	// 'elements' will correspond to a request for all items,
	// and 'elements/[rowID]' represents a single row.
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.javi.earthquakes.skeletondatabaseprovider",
				"elements", ALLROWS);
		uriMatcher.addURI("com.javi.earthquakes.skeletondatabaseprovider",
				"elements/#", SINGLE_ROW);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Open a read / write database to support the transaction.
		SQLiteDatabase db = myOpenHelper.getWritableDatabase();
		// If this is a row URI, limit the deletion to the specified row.
		switch (uriMatcher.match(uri)) {
		case SINGLE_ROW:
			String rowID = uri.getPathSegments().get(1);
			selection = ID
					+ "="
					+ rowID
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
		default:
			break;
		}
		// To delete all rows and return a value pass in "1".
		if (selection == null)
			selection = "1";
		// Perform the deletion.
		int deleteCount = db.delete(EarthquakesDBOpenHelper.DATABASE_TABLE,
				selection, selectionArgs);
		// Notify any observers of the change in the data set.
		getContext().getContentResolver().notifyChange(uri, null);
		// Return the number of deleted items.
		return deleteCount;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case ALLROWS:
			return "vnd.android.cursor.dir/vnd.paad.provider.elemental";
		case SINGLE_ROW:
			return "vnd.android.cursor.it!em/vnd.paad.provider.elemental";
		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = myOpenHelper.getWritableDatabase();
		// To add empty rows to your database by passing in an empty
		// Content Values object you must use the null column hack
		// parameter to specify the name of the column that can be
		// set to null.
		String nullColumnHack = null;
		// Insert the values into the table
		long rowID = db.insert(EarthquakesDBOpenHelper.DATABASE_TABLE, "quake",
				values);
		// Construct and return the URI of the newly inserted row.
		if (rowID > 0) {
			// Construct and return the URI of the newly inserted row.
			Uri insertedId = ContentUris.withAppendedId(CONTENT_URI, rowID);
			// Notify any observers of the change in the data set.
			getContext().getContentResolver().notifyChange(insertedId, null);
			return insertedId;
		} else

			return null;
	}

	@Override
	public boolean onCreate() {
		myOpenHelper = new EarthquakesDBOpenHelper(getContext(),
				EarthquakesDBOpenHelper.DATABASE_NAME, null,
				EarthquakesDBOpenHelper.DATABASE_VERSION, null);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db;
		try {
			db = myOpenHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			db = myOpenHelper.getReadableDatabase();
		}
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		// If this is a row query, limit the result set to the passed in row.
		switch (uriMatcher.match(uri)) {
		case SINGLE_ROW:
			String rowID = uri.getPathSegments().get(1);
			queryBuilder.appendWhere(ID + "=" + rowID);
		default:
			break;
		}
		// from table
		queryBuilder.setTables(EarthquakesDBOpenHelper.DATABASE_TABLE);
		// Execute the query.
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// Open a read / write database to support the transaction.
		SQLiteDatabase db = myOpenHelper.getWritableDatabase();
		// If this is a row URI, limit the deletion to the specified row.
		switch (uriMatcher.match(uri)) {
		case SINGLE_ROW:
			String rowID = uri.getPathSegments().get(1);
			selection = ID
					+ "="
					+ rowID
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection
							+ ')' : "");
		default:
			break;
		}
		// Perform the update.
		int updateCount = db.update(EarthquakesDBOpenHelper.DATABASE_TABLE,
				values, selection, selectionArgs);
		// Notify any observers of the change in the data set.
		getContext().getContentResolver().notifyChange(uri, null);
		return updateCount;
	}

	private class EarthquakesDBOpenHelper extends SQLiteOpenHelper {

		public static final String DATABASE_NAME = "earthquakes.db";
		public static final String DATABASE_TABLE = "Earthquakes";
		public static final int DATABASE_VERSION = 1;

		private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "
				+ DATABASE_TABLE
				+ "("
				+ ID
				+ "  INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ ID_STR
				+ " TEXT UNIQUE,"
				+ PLACE
				+ " TEXT,"
				+ TIME
				+ " INTEGER,"
				+ DETAIL
				+ " TEXT,"
				+ MAGNITUDE
				+ " REAl,"
				+ LAT
				+ " REAL,"
				+ LONG
				+ " REAL,"
				+ URL
				+ " TEXT,"
				+ CREATED_AT
				+ " INTEGER,"
				+ UPDATED_AT + " INTEGER)";

		public EarthquakesDBOpenHelper(Context context, String name,
				CursorFactory factory, int version,
				DatabaseErrorHandler errorHandler) {
			super(context, name, factory, version, errorHandler);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);

		}

	}

}
