package com.example.todolist;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class FragmentList extends ListFragment{
	private ArrayList<String> listado;
	private ArrayAdapter<String> adaptador;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		listado=new ArrayList<String>();
		adaptador= new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, listado);
		setListAdapter(adaptador);
		return super.onCreateView(inflater, container, savedInstanceState);
		
	}
	
	public void addItem(String texto){
		listado.add(0,texto);
		adaptador.notifyDataSetChanged();
	}
	
	@Override
	public void onActivityCreated(Bundle inState) {
        super.onActivityCreated(inState);
        if (inState != null) {    		
			// AÐadir a la lista
			listado.addAll(inState.getStringArrayList("listado"));
			adaptador.notifyDataSetChanged();
        }
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putStringArrayList("listado", listado);
		super.onSaveInstanceState(outState);
	}
}
