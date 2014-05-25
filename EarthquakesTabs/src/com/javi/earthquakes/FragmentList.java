package com.javi.earthquakes;

import java.util.ArrayList;
import java.util.Collection;

import android.R.integer;
import android.app.ListFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentList extends ListFragment implements InterfaceListFragmentAsyntask{
	private ArrayList<Quakes> listado;
	private EarthQuakeArrayAdapter adaptador;
	private double lastMagnitude=0;
	public final static String ID = "_id";
	
	private SharedPreferences prefs;

	
	EarthQuakeBD bd;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		listado=new ArrayList<Quakes>();
		adaptador= new EarthQuakeArrayAdapter(getActivity(), listado);
		setListAdapter(adaptador);
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		lastMagnitude =Double.parseDouble(prefs.getString("magnitud_terremotos", "5"));
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	
	@Override
	public void onActivityCreated(Bundle inState) {
        super.onActivityCreated(inState);
        
 //      bd = new EarthQuakeBD(getActivity());
  //      listado.addAll(bd.getTerremotos(lastMagnitude));
   //     setListAdapter(adaptador);
       

        queryTerremotosBDAsynTask();
      //  adaptador.notifyDataSetChanged();
       // DownloadTerremotosTask download= new DownloadTerremotosTask(getActivity(), this);
       // download.execute();
        queryTerremotosJSONAsynTask();
        
        if (inState != null) {    		

        }
	}
	
	public void actualizarListadoTerremotos(Quakes q){
		Log.d("LISTFRAGMENT", "Lista ha sido actualizado añadiendo : "+q.getPlace());
		listado.add(0,q);
		adaptador.notifyDataSetChanged();
	}
	
	public void resetearActualizarLista(ArrayList<Quakes> result) {
		listado.clear();
		listado.addAll(result);
		adaptador.notifyDataSetChanged();	
	}
	
	public void actualizarListadoTerremotos(ArrayList<Quakes> lista){
		for(Quakes q : lista) {
			if(q.getMagnitude()>=Double.parseDouble(prefs.getString("magnitud_terremotos", "5")))

					listado.add(0, q);		
				}		
				adaptador.notifyDataSetChanged();
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
 		double newMagnitude=Double.parseDouble(prefs.getString("magnitud_terremotos", "5"));
		if(lastMagnitude != newMagnitude) {
			lastMagnitude = newMagnitude;
			queryTerremotosBDAsynTask();
		}
  	}
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {

	}
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
        Intent detalle = new Intent(getActivity(), DetailActivity.class);
        detalle.putExtra(ID,listado.get(position).getId());
        startActivity(detalle);
    }

}
