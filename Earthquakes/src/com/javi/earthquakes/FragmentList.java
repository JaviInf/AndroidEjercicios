package com.javi.earthquakes;

import java.util.ArrayList;
import java.util.Collection;

import android.R.integer;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentList extends ListFragment implements InterfaceListFragmentAsyntask{
	private ArrayList<Quakes> listado;
	private EarthQuakeArrayAdapter adaptador;

	public final static String ID = "_id";

	
	EarthQuakeBD bd;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		listado=new ArrayList<Quakes>();
		adaptador= new EarthQuakeArrayAdapter(getActivity(), listado);

		setListAdapter(adaptador);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	
	@Override
	public void onActivityCreated(Bundle inState) {
        super.onActivityCreated(inState);
        
        bd = new EarthQuakeBD(getActivity());
        listado.addAll(bd.getTerremotos(0));
        adaptador.notifyDataSetChanged();
        DownloadTerremotosTask download= new DownloadTerremotosTask(getActivity(), this);
        download.execute();
        
        if (inState != null) {    		

        }
	}
	
	public void actualizarListadoTerremotos(Quakes q){
		Log.d("LISTFRAGMENT", "Lista ha sido actualizado añadiendo : "+q.getPlace());
		listado.add(0,q);
		adaptador.notifyDataSetChanged();
	
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
