package com.javi.intentycamara;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FormActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);

		EditText cajaTexto=(EditText) findViewById(R.id.cajaTexto);
		TextView texto=(TextView) findViewById(R.id.texto);
		Button botonAceptar = (Button) findViewById(R.id.aceptar);
		Button botonCancelar = (Button) findViewById(R.id.cancelar);
		
		Bundle bundle = getIntent().getExtras();
		texto.setText(bundle.getString(MainActivity.entrada));
		
		
	}

	
}
