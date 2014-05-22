package com.javi.earthquakes;

import java.util.ArrayList;
import java.util.Collection;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class FragmentList extends ListFragment implements InterfaceListFragmentAsyntask{
	private ArrayList<Quakes> listado;
	private ArrayAdapter<Quakes> adaptador;
	
	EarthQuakeBD bd;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		listado=new ArrayList<Quakes>();
		adaptador= new ArrayAdapter<Quakes>(inflater.getContext(), android.R.layout.simple_list_item_1, listado);
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
		Log.d("LISTFRAGMENT", "Lista ha sido actualizado a–adiendo : "+q.getPlace());
		listado.add(0,q);
		adaptador.notifyDataSetChanged();
	
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {

	}
}
