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

public class FragmentList extends ListFragment{
	private ArrayList<Quakes> listado;
	private ArrayAdapter<Quakes> adaptador;
	
	EarthQuakeBD bd;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		listado=new ArrayList<Quakes>();
		adaptador= new ArrayAdapter<Quakes>(inflater.getContext(), android.R.layout.simple_list_item_1, listado);
		setListAdapter(adaptador);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
//	public void addItem(String texto){
//		listado.add(0,texto);
//		adaptador.notifyDataSetChanged();
//	}
	
	
//	public void crearListaActualizadaTerremotos(EarthQuakeBD bd){
//		ArrayList<Quakes> list= new ArrayList<Quakes>();
//		list=bd.getTerremotos(0);
//		for( int i = 0 ; i < list.size() ; i++ ){
//			 this.addItem(list.get(i).toString());			  
//			  Log.d("LISTFRAGMENT",list.get(i).toString());
//			}		
//	}
	
	@Override
	public void onActivityCreated(Bundle inState) {
        super.onActivityCreated(inState);
        
        bd = new EarthQuakeBD(getActivity());
        listado.addAll(bd.getTerremotos(0));
        adaptador.notifyDataSetChanged();
        
        
        if (inState != null) {    		

        }
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

	}
}
