package com.javi.calculadorafraudulenta;

import com.example.calculadorafraudulenta.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TecladoFragment extends Fragment{
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                             Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        return inflater.inflate(R.layout.teclado_fragment, container, false);
	    }
}
