package com.javi.ejerciciocalculadora;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText display = null;
	
	// instancia de calculadora
	
	Calculadora calculadora=new Calculadora();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 display=(EditText)findViewById(R.id.editText1);
	}

	
	public void obtenerOperando(View v){
		Button boton= (Button) v;
		String numero=boton.getText().toString();
		calculadora.numeroPulsado(numero);
		
		// display
		display.setText(numero);
	}
	
	public void obtenerOperador(View v){
		Button boton=(Button) v;
		String operador= boton.getText().toString();
		calculadora.operacionPulsado(operador);

		display.setText(operador);
	}
	
	public void obtenerResultado(View v){
		display.setText(calculadora.calcularOperacion());
		
	}
	
	
	// RESTORE VALUES ON LANDSCAPE
	// Called after onCreate has finished, use to restore UI state @Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	     super.onRestoreInstanceState(savedInstanceState);
	     // Restore UI state from the savedInstanceState.
	     // This bundle has also been passed to onCreate.
	}
	
	public void onSaveInstanceState(Bundle savedInstanceState) {
	     // Save UI state changes to the savedInstanceState.
	     // This bundle will be passed to onCreate and
	     // onRestoreInstanceState if the process is
	     // killed and restarted by the run time.
	     super.onSaveInstanceState(savedInstanceState);
	}

}
