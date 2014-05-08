package com.example.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity {
	
	private ArrayList<String> listado;
	private ArrayAdapter<String> adaptador;
	private EditText cajaTexto;
	private ListView listadoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
			listado= new ArrayList<String>();
			adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
			cajaTexto=(EditText) findViewById(R.id.cajaTexto);
			listadoView=(ListView) findViewById(R.id.lista);
			listadoView.setAdapter(adaptador);			
	}

	
	public void nuevoElemento(View v){
		Log.d("DEBUG", "boton mas");
		listado.add(cajaTexto.getText().toString());
		adaptador.notifyDataSetChanged();
		cajaTexto.setText("");
	}

	
	public void onSaveInstanceState(Bundle savedInstanceState) {
	     // Save UI state changes to the savedInstanceState.
	     // This bundle will be passed to onCreate and
	     // onRestoreInstanceState if the process is
	     // killed and restarted by the run time.
		savedInstanceState.putStringArrayList("LISTADO", listado);
	     super.onSaveInstanceState(savedInstanceState);
	}

	// Called after onCreate has finished, use to restore UI state @Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	     super.onRestoreInstanceState(savedInstanceState);
	     // Restore UI state from the savedInstanceState.
	     // This bundle has also been passed to onCreate.
	     listado.addAll(savedInstanceState.getStringArrayList("LISTADO"));
	}

}
