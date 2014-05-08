package com.javi.ejerciciocalculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView display = null;
	private String operador = "";
	// instancia de calculadora
	Calculadora calculadora = new Calculadora();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		display = (TextView) findViewById(R.id.editText1);
		
		display.setText("0.0");
	}

	public void obtenerOperando(View v) {
		Button boton = (Button) v;
		String numeroBoton = boton.getText().toString();
		calculadora.numeroPulsado(numeroBoton);
		display.setText(numeroBoton);
	}

	public void obtenerOperador(View v) {
		Button boton = (Button) v;
		this.operador = boton.getText().toString();
		calculadora.operacionPulsado(operador);
		display.setText(operador);
	}

	public void obtenerResultado(View v) {
		display.setText(calculadora.calcularOperacion());
	}

	// RESTORE VALUES ON LANDSCAPE
	// Called after onCreate has finished, use to restore UI state @Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore UI state from the savedInstanceState.
		// This bundle has also been passed to onCreate.
		calculadora.display = savedInstanceState.getString("display");
		calculadora.operador = savedInstanceState.getString("operador");
		calculadora.operando1 = savedInstanceState.getDouble("numero");
	}

	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save UI state changes to the savedInstanceState.
		// This bundle will be passed to onCreate and
		// onRestoreInstanceState if the process is
		// killed and restarted by the run time.
		savedInstanceState.putDouble("numero", calculadora.operando1);
		savedInstanceState.putString("operador", calculadora.operador);
		savedInstanceState.putString("display", calculadora.display);
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		display.setText(calculadora.display);
	}

}
