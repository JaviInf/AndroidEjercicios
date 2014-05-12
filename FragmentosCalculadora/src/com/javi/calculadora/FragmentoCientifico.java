package com.javi.calculadora;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentoCientifico  extends Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v =  inflater.inflate(R.layout.fragmento_cientifico, container, false);
		return v;
	}
}
