package com.javi.earthquakes;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment extends MapFragment  implements
LoaderCallbacks<Cursor>{
	
	private static final int LOADER_ID1 = 5;
	private GoogleMap map;
	private String[] result_columns = new String[] {
			MyContentProvider.LAT, MyContentProvider.LONG, MyContentProvider.ID };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		map = this.getMap();
		 LoaderManager lm = getLoaderManager();
		    lm.initLoader(LOADER_ID1, null, this);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		SharedPreferences prefs=PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		String where = MyContentProvider.MAGNITUDE + ">=?";
		String whereArgs[] = { prefs.getString("magnitud_terremotos", "5") };
		CursorLoader loader = new CursorLoader(getActivity(),
				MyContentProvider.CONTENT_URI, result_columns, where,
				whereArgs, MyContentProvider.TIME + " DESC");
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		   while (cursor.moveToNext()) {
			    
	        
	             
	             int QUAKE_LOCATION_LAT_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.LAT);         
	             float latQuake = cursor.getFloat(QUAKE_LOCATION_LAT_INDEX);  
	             
	             int QUAKE_LOCATION_LONG_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.LONG);             
	             float longQuake= cursor.getFloat(QUAKE_LOCATION_LONG_INDEX);
	             Log.d("MyMapFragmnet Lat", ""+latQuake);
	             Log.d("MyMapFragmnet Long", ""+longQuake);
            
	          //	 this.crearMarcador(LatQuake, LongQuake);
		   }
		   }
		

	private void crearMarcador(float latQuake, float longQuake) {
		MarkerOptions marker = new MarkerOptions()
		   .position(new LatLng(latQuake, longQuake));
		map.addMarker(marker);	
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
