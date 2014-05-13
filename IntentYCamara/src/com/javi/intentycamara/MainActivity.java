package com.javi.intentycamara;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	
		public static final String MESSAGE="resultado";
	    private static final int RESULTADO = 0;
	    private static final int FORMULARIO = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

			Button botonForm= (Button) findViewById(R.id.form);
			final EditText cajaTexto= (EditText) findViewById(R.id.editText1);
			
			ImageView imagen= (ImageView) findViewById(R.id.imageView1);
			
			
			botonForm.setOnClickListener(new View.OnClickListener() {
				  public void onClick(View view) {
					  Intent intent=new Intent(MainActivity.this, FormActivity.class);
					  intent.putExtra(MESSAGE, cajaTexto.getText().toString());
					  startActivityForResult(intent, FORMULARIO);
					  cajaTexto.setText("");
				
				} });
	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    super.onActivityResult(requestCode, resultCode, data);
//	    TextView texto=(TextView) findViewById(R.id.cajamain);
//	    if (resultCode == Activity.RESULT_CANCELED) {
//	  
//	        
//	    } else if (resultCode==Activity.RESULT_OK) {
//	   
//	        String resultado = data.getExtras().getString("RESULTADO");
//	        switch (requestCode) {
//	        case FORMULARIO:
//	            texto.setText(resultado);
//	            break;       
//	        }
//	    }
//	}
	public void onActivityResult(int requestCode,
	        int resultCode,
	        Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	     
	    TextView texto=(TextView) findViewById(R.id.cajamain);
	     switch(requestCode) {
	     case(FORMULARIO):
	         if (resultCode == Activity.RESULT_OK)
	            {
	                String message = data.getStringExtra(FormActivity.MESSAGE);
	                texto.setText(message);
	            }
	         else if(resultCode == Activity.RESULT_CANCELED)
	            {
	              
	            }
	     break;
	      
	     }
	     
	}
}
