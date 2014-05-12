package com.javi.calculadora;

import com.javi.calculadora.FragmentoTeclado.InterfazTeclado;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity implements InterfazTeclado{
	
	Calculadora calculadora;
	FragmentoDisplay miFragmentoTeclado;
	FragmentManager fragmentManager;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		calculadora= new Calculadora();
		fragmentManager = getFragmentManager();
        
        miFragmentoTeclado = (FragmentoDisplay)fragmentManager.findFragmentById(R.id.fragment1);
		miFragmentoTeclado.modificarTexto("prueba");
	}

	@Override
	public void numeroPulsado(String digito) {
		// TODO Auto-generated method stub
		calculadora.numeroPulsado(digito);
		miFragmentoTeclado.modificarTexto(digito);
		Log.d("DEBUG", "Pulsado numero recibido por main activity");
//		display.modificarTexto(calcularOperacion());	
	}

	@Override
	public void operacionPulsado(String operadorNuevo) {
		// TODO Auto-generated method stub
		calculadora.operacionPulsado(operadorNuevo);
		miFragmentoTeclado.modificarTexto(operadorNuevo);
		Log.d("DEBUG", "Pulsado operador recibido por main activity");
	//	display.modificarTexto(operadorNuevo);
		
	}

	@Override
	public String calcularOperacion() {
		// TODO Auto-generated method stub
		
		Log.d("DEBUG", "Pulsado calcular operacion por main activity");
		miFragmentoTeclado.modificarTexto(calculadora.calcularOperacion());
		return calculadora.calcularOperacion();
	}

	
}
