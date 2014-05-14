package com.javi.intentycamara;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FormActivity extends Activity {
public static final String MESSAGE="resultado";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);

		final EditText cajaTexto=(EditText) findViewById(R.id.cajaTexto);
		final TextView texto=(TextView) findViewById(R.id.texto);
		Button botonAceptar = (Button) findViewById(R.id.aceptar);
		Button botonCancelar = (Button) findViewById(R.id.cancelar);
		
		final Intent intent=getIntent();
		Bundle bundle = intent.getExtras();
		texto.setText(bundle.getString(MESSAGE));
		
		botonAceptar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				  intent.putExtra(MESSAGE,cajaTexto.getText().toString());
				  setResult(Activity.RESULT_OK, intent);
				  finish();
			}
		});
		
		botonCancelar.setOnClickListener(new OnClickListener() {
			 
	        @Override
	        public void onClick(View v) {
	           setResult(RESULT_CANCELED);
	           finish();
	        }
	    });
		
		
	}

	
}
