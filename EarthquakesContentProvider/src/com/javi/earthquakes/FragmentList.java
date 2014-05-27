package com.javi.earthquakes;

import android.app.ListFragment;
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

public class FragmentList extends ListFragment implements
		LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter adaptadorCursor;
	public final static String ID = "_id";
	public static final int id = 0;
	private Cursor cursor = null;
	// private SharedPreferences prefs;

	private String[] result_columns = new String[] {
			MyContentProvider.MAGNITUDE, MyContentProvider.PLACE,
			MyContentProvider.TIME, MyContentProvider.ID };
	private int[] to_columns = new int[] { R.id.magnitud, R.id.places,
			R.id.times };
	String nuevaMag;
	private double magnitud = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		adaptadorCursor = new SimpleCursorAdapter(getActivity(),
				R.layout.list_row_item, cursor, result_columns, to_columns, 0);
		adaptadorCursor.setViewBinder(new EarthquakeViewBinder());
		setListAdapter(adaptadorCursor);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle inState) {
		super.onActivityCreated(inState);

		// prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		// nuevaMag=prefs.getString("magnitud_terremotos", "5");
		// String whereArgs[]={prefs.getString("magnitud_terremotos", "5")};
		// double lastMagnitude =
		// Double.parseDouble(prefs.getString("magnitud_terremotos", "5"));
		// SharedPreferences prefs =
		// PreferenceManager.getDefaultSharedPreferences(getActivity());

		// adaptadorCursor= new SimpleCursorAdapter(getActivity(),
		// R.layout.list_row_item, cursor, result_columns, to_columns, 0);
		// adaptadorCursor.setViewBinder(new EarthquakeViewBinder());
		// setListAdapter(adaptadorCursor);
		getLoaderManager().initLoader(id, null, this);
		// queryTerremotosJSONAsynTask();
	}

	public void setMagnitud(String pMagnitud) {
		nuevaMag = pMagnitud;
		getLoaderManager().restartLoader(id, null, this);

	}

	public void queryTerremotosJSONAsynTask() {
		DownloadTerremotosTask download = new DownloadTerremotosTask(
				getActivity(), this);
		download.execute();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		getLoaderManager().restartLoader(id, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		// String lastMagnitude = prefs.getString("magnitud_terremotos", "5");
		String where = MyContentProvider.MAGNITUDE + ">=?";
		String whereArgs[] = { prefs.getString("magnitud_terremotos", "5") };
		// String whereArgs[]={nuevaMag};
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
