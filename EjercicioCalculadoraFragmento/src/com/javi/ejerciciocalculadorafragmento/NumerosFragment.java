package com.javi.ejerciciocalculadorafragmento;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NumerosFragment extends Fragment {
	
	public interface InterfazTeclado {
		 public void numeroPulsado(String digito);
		 public void operacionPulsado(String operadorNuevo);
		 public String calcularOperacion();
		 
	}
	
	private InterfazTeclado interfazTeclado;
	private int numeros[]={R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9};
	private int operadores[]={R.id.opBorrar, R.id.opX, R.id.opPunto, R.id.opMenos, R.id.opMas, R.id.opIgual, R.id.opDiv};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view=inflater.inflate(R.layout.fragment_numeros, container, false);
		
		return view;
	}
	
	@Override
	public void onAttach(Activity activity) {
	  super.onAttach(activity);
	try {
	    interfazTeclado=(InterfazTeclado)activity;
	  } catch (ClassCastException e) {
	    throw new ClassCastException(activity.toString() +
	              " must implement OnSeasonSelectedListener");
	} }

	
}
