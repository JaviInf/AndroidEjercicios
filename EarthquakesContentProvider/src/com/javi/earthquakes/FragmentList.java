package com.javi.earthquakes;


import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;

public class FragmentList extends ListFragment implements LoaderCallbacks<Cursor>{

	private SimpleCursorAdapter adaptadorCursor;
	public final static String ID = "_id";
	private static final int id = 0;
	private Cursor cursor=null;
	private SharedPreferences prefs;
	
	private String[] result_columns = new String[] {
		    MyContentProvider.MAGNITUDE,
		    MyContentProvider.PLACE,
		    MyContentProvider.TIME,
		    MyContentProvider.ID};
	private int[] to_columns = new int[] {
			R.id.magnitud,
			R.id.places,
			R.id.times };

	@Override
	public void onActivityCreated(Bundle inState) {
        super.onActivityCreated(inState);        

       prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//		double lastMagnitude = Double.parseDouble(prefs.getString("magnitud_terremotos", "5"));
     //  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
     
		adaptadorCursor= new SimpleCursorAdapter(getActivity(), R.layout.list_row_item, cursor, result_columns, to_columns, 0);
		adaptadorCursor.setViewBinder(new EarthquakeViewBinder());
		setListAdapter(adaptadorCursor);
		getLoaderManager().initLoader(id, null, this);
      //  queryTerremotosJSONAsynTask();
	}
	

	
	public void queryTerremotosJSONAsynTask() {
		DownloadTerremotosTask download= new DownloadTerremotosTask(getActivity(), this);
        download.execute();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		
		//String lastMagnitude = prefs.getString("magnitud_terremotos", "5");
		String where=MyContentProvider.MAGNITUDE+">=?";
 		String whereArgs[]={prefs.getString("magnitud_terremotos", "5")};
		CursorLoader loader = new CursorLoader(getActivity(),
			    MyContentProvider.CONTENT_URI, result_columns, where, whereArgs, MyContentProvider.TIME+ " DESC");
		
			  return loader;
	}


	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adaptadorCursor.swapCursor(cursor);
	}


	@Override
	public void onLoaderReset(Loader<Cursor> cursor) {
		adaptadorCursor.swapCursor(null);
	}

}
