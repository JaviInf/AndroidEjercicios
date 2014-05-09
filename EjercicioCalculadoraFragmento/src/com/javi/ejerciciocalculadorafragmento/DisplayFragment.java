package com.javi.ejerciciocalculadorafragmento;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;


public class DisplayFragment extends Fragment {
	
	TextView display;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view=inflater.inflate(R.layout.fragment_display, container, false);
		display= (TextView) view.findViewById(R.id.editText1);
		return  view;
	}

	

}
