package com.javi.ejerciciocalculadorafragmento;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class NumerosFragment extends Fragment {
	
	public interface InterfazTeclado {
		 public void numeroPulsado(String digito);
		 public void operacionPulsado(String operadorNuevo);
		 public String calcularOperacion();
	}
	
	private InterfazTeclado actividad;
	private int numeros[]={R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9};
	private int operadores[]={R.id.opBorrar, R.id.opX, R.id.opPunto, R.id.opMenos, R.id.opMas, R.id.opDiv};
	private int igual=R.id.opIgual;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view=inflater.inflate(R.layout.fragment_numeros, container, false);
		
		for(int i=0; i<numeros.length;i++)
		{
			final Button boton = (Button) view.findViewById(numeros[i]);
			boton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String numeroBoton = boton.getText().toString();
					actividad.numeroPulsado(numeroBoton);	
				}
			});
		}
		
		
		//listener para los botones de operaciones
		for(int i=0; i<operadores.length;i++)
		{
			final Button boton = (Button) view.findViewById(operadores[i]);
			boton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String operadorBoton = boton.getText().toString();
					actividad.operacionPulsado(operadorBoton);
				}
			});	
		}
		final Button boton=(Button) view.findViewById(igual);
		boton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				actividad.calcularOperacion();
			}
		});
		return view;
	}
	
	@Override
	public void onAttach(Activity activity) {
	  super.onAttach(activity);
	try {
	    actividad=(InterfazTeclado)activity;
	  } catch (ClassCastException e) {
	    throw new ClassCastException(activity.toString() +
	              " must implement InterfazTeclado");
	} }

	
}
