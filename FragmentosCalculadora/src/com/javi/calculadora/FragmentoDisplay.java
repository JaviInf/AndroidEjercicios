package com.javi.calculadora;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentoDisplay extends Fragment{
	
	TextView output;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v =  inflater.inflate(R.layout.fragmento_display, container, false);
		output = (TextView) v.findViewById(R.id.textView1);
		return v;
	}
	 public void modificarTexto(String texto)
	    {
	         
	    output.setText(texto);
	         
	     
	    }
	     
}
