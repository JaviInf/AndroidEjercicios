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
import android.widget.ListView;


public class MainActivity extends Activity implements InterfazDisplay{
	
	private ArrayList<String> listado;
	private ArrayAdapter<String> adaptador;
	private EditText cajaTexto;
	private ListView listadoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//FragmentManager
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.containerDisplay, new FargmentDisplay());
		fragmentTransaction.commit();
		
		listado= new ArrayList<String>();
		adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
		
	
		
//			listadoView=(ListView) findViewById(R.id.lista);
//			listadoView.setAdapter(adaptador);			
//			cajaTexto.setOnKeyListener(new View.OnKeyListener() {
//				@Override
//				public boolean onKey(View v, int keyCode, KeyEvent event) {
//					// TODO Auto-generated method stub
//					if(event.getAction()==KeyEvent.ACTION_DOWN){
//						if(event.getAction()==KeyEvent.KEYCODE_DPAD_CENTER || (keyCode==KeyEvent.KEYCODE_ENTER)){
//							listado.add(0, cajaTexto.getText().toString());
//							adaptador.notifyDataSetChanged();
//							cajaTexto.setText("");
//							return true;
//						}
//					}
//					return false;
//				}
//			});
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


	@Override
	public void obtenerTexto(String nuevaEntrada) {
		// TODO Auto-generated method stub
		listado.add(nuevaEntrada);
	     adaptador.notifyDataSetChanged();
	}

}
