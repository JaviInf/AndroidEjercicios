package com.javi.ejerciciocalculadorafragmento;

import com.javi.ejerciciocalculadorafragmento.NumerosFragment.InterfazTeclado;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity implements InterfazTeclado {
	Calculadora calculadora;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		calculadora= new Calculadora();
	}

	@Override
	public void numeroPulsado(String digito) {
		// TODO Auto-generated method stub
		calculadora.numeroPulsado(digito);
		
	}

	@Override
	public void operacionPulsado(String operadorNuevo) {
		// TODO Auto-generated method stub
		calculadora.operacionPulsado(operadorNuevo);
		
	}

	@Override
	public String calcularOperacion() {
		// TODO Auto-generated method stub
		return calculadora.calcularOperacion();
	}


}
