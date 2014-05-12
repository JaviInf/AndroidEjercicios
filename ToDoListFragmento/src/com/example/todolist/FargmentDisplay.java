package com.example.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FargmentDisplay extends Fragment {
	
	public interface InterfazDisplay {
        public void obtenerTexto(String pTexto);
    }
	
	private InterfazDisplay mainActivity;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
	  // Create, or inflate the Fragment's UI, and return it.
	  // If this Fragment has no UI then return null.
	 View v= inflater.inflate(R.layout.fragment_display, container, false);
	 
	  final EditText cajaTexto = (EditText) v.findViewById(R.id.editText1);
	  Button botonMas= (Button) v.findViewById(R.id.anadir);
	  
	  botonMas.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String nuevaEntrada=cajaTexto.getText().toString();
			mainActivity.obtenerTexto(nuevaEntrada);
			cajaTexto.setText("");
			Log.d("DISPLAY", "nuevo texto enviado al main:   "+nuevaEntrada);
		}
	});
	  
	 return v;
	}
	
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try {
            mainActivity = (InterfazDisplay) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "debe implementar InterfazDisplay");
        }
    }
		
	
}
