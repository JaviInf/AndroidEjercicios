package com.javi.earthquakes;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentList extends ListFragment implements InterfaceListFragmentAsyntask{
//	private ArrayList<Quakes> listado;
	private SimpleCursorAdapter adaptadorCursor;
	private ArrayList<Quakes> listadoCursor;
//	private EarthQuakeArrayAdapter adaptador;
	private double lastMagnitude=0;
	public final static String ID = "_id";
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
	
	private EarthQuakeBD bd;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	//	listado=new ArrayList<Quakes>();
//		adaptador= new EarthQuakeArrayAdapter(getActivity(), listado);
//		setListAdapter(adaptador);
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		lastMagnitude =Double.parseDouble(prefs.getString("magnitud_terremotos", "5"));
		adaptadorCursor= new SimpleCursorAdapter(getActivity(), R.layout.list_row_item, cursor, result_columns, to_columns, 0);
		adaptadorCursor.setViewBinder(new EarthquakeViewBinder());
		setListAdapter(adaptadorCursor);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	
	@Override
	public void onActivityCreated(Bundle inState) {
        super.onActivityCreated(inState);        
 //      bd = new EarthQuakeBD(getActivity());
  //      listado.addAll(bd.getTerremotos(lastMagnitude));
   //     setListAdapter(adaptador);
       //////// queryTerremotosBDAsynTask();       
      //  adaptador.notifyDataSetChanged();
        queryTerremotosJSONAsynTask();
	}
	
	public void actualizarListadoTerremotos(Quakes q){
		Log.d("LISTFRAGMENT", "Lista ha sido actualizado añadiendo : "+q.getPlace());
//		listado.add(0,q);
//		adaptador.notifyDataSetChanged();
	}
	
	public void resetearActualizarLista(ArrayList<Quakes> result) {
//		listado.clear();
//		listado.addAll(result);
//		adaptador.notifyDataSetChanged();	
	}
	
	public void actualizarListadoTerremotos(ArrayList<Quakes> lista){
//		for(Quakes q : lista) {
//			if(q.getMagnitude()>=Double.parseDouble(prefs.getString("magnitud_terremotos", "5")))
////
//					listado.add(0, q);		
//			}	
			//	adaptadorCursor.notifyDataSetChanged();
		ContentResolver cr =getActivity().getContentResolver();
 		String where=MyContentProvider.MAGNITUDE+">=?";
 		String whereArgs[]={prefs.getString("magnitud_terremotos", "5")};
 		cursor = cr.query(MyContentProvider.CONTENT_URI, result_columns,
 		             where, whereArgs, MyContentProvider.TIME);
 		adaptadorCursor.swapCursor(cursor);
 		Log.d("FRAGMENLIST", "estoy ejecutando actualizarListadoTerremotos que viene desde el asyntask");
	}
	
	public void queryTerremotosBDAsynTask() {
		QueryEarthQuakesTask q = new QueryEarthQuakesTask(this, getActivity());
		q.execute();
	}
	
	public void queryTerremotosJSONAsynTask() {
		DownloadTerremotosTask download= new DownloadTerremotosTask(getActivity(), this);
        download.execute();
	}

	
	@Override
  	public void onResume() {
  		super.onResume();
 		//double newMagnitude=Double.parseDouble(prefs.getString("magnitud_terremotos", "5"));
 		ContentResolver cr =getActivity().getContentResolver();
 		String where=MyContentProvider.MAGNITUDE+">=?";
 		String whereArgs[]={prefs.getString("magnitud_terremotos", "5")};
 		cursor = cr.query(MyContentProvider.CONTENT_URI, result_columns,
 		             where, whereArgs, MyContentProvider.TIME);
 		adaptadorCursor.swapCursor(cursor);
  	}
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {

	}
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent detalle = new Intent(getActivity(), DetailActivity.class);
        detalle.putExtra(ID,id);
        startActivity(detalle);
    }

}
