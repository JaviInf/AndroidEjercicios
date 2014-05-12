package com.javi.calculadora;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentoTeclado extends Fragment {

	public interface InterfazTeclado {
		 public void numeroPulsado(String digito);
		 public void operacionPulsado(String operadorNuevo);
		 public String calcularOperacion();
	}

	private InterfazTeclado actividad;
	private int numeros[]={R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9};
	private int operadores[]={R.id.borrar, R.id.oppor, R.id.oppunto, R.id.opmenos, R.id.opmas, R.id.opdiv};
	private int igual=R.id.opigual;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v =  inflater.inflate(R.layout.fragmento_teclado, container, false);
		
		for(int i=0; i<numeros.length;i++)
		{
			final Button boton = (Button) v.findViewById(numeros[i]);
			boton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String numeroBoton = boton.getText().toString();
					Log.d("DEBUG", "Pulsado numero"+numeroBoton);
					actividad.numeroPulsado(numeroBoton);	
				}
			});
		}


		//listener para los botones de operaciones
		for(int i=0; i<operadores.length;i++)
		{
			final Button boton = (Button) v.findViewById(operadores[i]);
			boton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String operadorBoton = boton.getText().toString();
					Log.d("DEBUG", "Pulsado operacion"+operadorBoton);

					actividad.operacionPulsado(operadorBoton);
				}
			});	
		}
		
		final Button boton=(Button) v.findViewById(igual);
		boton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("DEBUG", "Pulsado igual");

				actividad.calcularOperacion();
			}
		});
		return v;
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
