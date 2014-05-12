package com.example.todolist;

import java.util.ArrayList;

import com.example.todolist.FargmentDisplay.InterfazDisplay;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ArrayAdapter;




public class MainActivity extends Activity implements InterfazDisplay{
	
	private ArrayList<String> listado;
	private ArrayAdapter<String> adaptador;
	
	 FragmentList fragmentList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//	FragmentList fragmento=new FragmentList();
		
		//FragmentManager
		if(savedInstanceState==null){
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.containerDisplay, new FargmentDisplay());
		fragmentTransaction.add(R.id.containerList,new FragmentList(), "list");
		fragmentTransaction.commit();
		}
		
//		listado= new ArrayList<String>();
//		adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
//		fragmento.setListAdapter(adaptador);
		
	}


	@Override
	public void a–adirItem(String nuevaEntrada) {
		// TODO Auto-generated method stub
//		 listado.add(nuevaEntrada);
//	     adaptador.notifyDataSetChanged();
		((FragmentList)getFragmentManager().findFragmentByTag("list")).addItem(nuevaEntrada);
	}

}
