package com.javi.earthquakes;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentList extends ListFragment implements
		LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter adaptadorCursor;
	public final static String ID = "_id";
	public static final int LOADER_ID = 0;
	private Cursor cursor = null;
	private String[] result_columns = new String[] {
			MyContentProvider.MAGNITUDE, MyContentProvider.PLACE,
			MyContentProvider.TIME, MyContentProvider.ID };
	private int[] to_columns = new int[] { R.id.magnitud, R.id.places,
			R.id.times };
	String nuevaMag;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		adaptadorCursor = new SimpleCursorAdapter(getActivity(),
				R.layout.list_row_item, null, result_columns, to_columns, 0);
		adaptadorCursor.setViewBinder(new EarthquakeViewBinder());
		setListAdapter(adaptadorCursor);
//		 getLoaderManager().initLoader(LOADER_ID, null, this);
		 LoaderManager lm = getLoaderManager();
		    lm.initLoader(LOADER_ID, null, this);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle inState) {
		super.onActivityCreated(inState);
		getActivity().startService(new Intent(getActivity(),MyServiceEarthquakes.class));
		Toast.makeText(getActivity(),"Se acaba de actualizar la lista !",Toast.LENGTH_SHORT).show();
		
	}
	
	public void refrescarTerremotosService(){
		getActivity().startService(new Intent(getActivity(),MyServiceEarthquakes.class));
		Toast.makeText(getActivity(),"Se acaba de actualizar la lista!",Toast.LENGTH_SHORT).show();
}

	public void queryTerremotosJSONAsynTask() {
		DownloadTerremotosTask download = new DownloadTerremotosTask(
				getActivity(), this);
		download.execute();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getLoaderManager().restartLoader(LOADER_ID, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		SharedPreferences prefs = PreferenceManager
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
		adaptadorCursor.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> cursor) {
		adaptadorCursor.swapCursor(null);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent detalle = new Intent(getActivity(), DetailActivity.class);
		detalle.putExtra(ID, id);
		startActivity(detalle);
	}
}
