package com.javi.intentycamara;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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
	
	public static final String entrada="entrada";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

			Button botonForm= (Button) findViewById(R.id.form);
			final EditText cajaTexto= (EditText) findViewById(R.id.editText1);
			TextView texto=(TextView) findViewById(R.id.cajamain);
			ImageView imagen= (ImageView) findViewById(R.id.imageView1);
			
			
			botonForm.setOnClickListener(new View.OnClickListener() {
				  public void onClick(View view) {
					  Intent intent=new Intent(MainActivity.this, FormActivity.class);
					  intent.putExtra(entrada, cajaTexto.getText().toString());
					  startActivity(intent);
				finish();
				} });
		
	}

	
}
