package com.example.todolist;

import java.util.ArrayList;

import com.example.todolist.FargmentDisplay.InterfazDisplay;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;



public class MainActivity extends Activity implements InterfazDisplay{
	
	private ArrayList<String> listado;
	private ArrayAdapter<String> adaptador;
	private EditText cajaTexto;
	
	 FragmentList fragmentList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listado= new ArrayList<String>();
		adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
		
		//FragmentManager
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.containerDisplay, new FargmentDisplay());
		fragmentTransaction.add(R.id.containerList, new FragmentList());
		fragmentTransaction.commit();
	
		
		fragmentList = (FragmentList)fragmentManager.findFragmentById(R.id.containerList);
		//fragmentList.setListAdapter(adaptador);
	}


	@Override
	public void obtenerTexto(String nuevaEntrada) {
		// TODO Auto-generated method stub
		 listado.add(nuevaEntrada);
	     adaptador.notifyDataSetChanged();
	}

}
