package com.example.ciclodevida;

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
import android.os.Build;

public class CActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_c);
		Button botonA=(Button) findViewById(R.id.button1);
		Button botonB=(Button) findViewById(R.id.button2);
		Button botonCerrar=(Button) findViewById(R.id.button3);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override 
	protected void onResume(){
		 super.onResume();
		 Log.d("CActivity", "CActivity ejecucion de onCreate");
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		Log.d("CActivity", "CActivity ejecucion de onPause");
	}
	
	@Override 
	protected void onStop(){
		super.onStop();
		 Log.d("CActivity", "CActivity ejecucion de onStop");
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		Log.d("CActivity", "CActivity ejecucion de onDestroy");
	}
	
	public void startB(View v){
		Intent intent= new Intent(this, BActivity.class);
		startActivity(intent);
		
	}
	
	public void startA(View v){
		Intent intent= new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void cerrar(View v){
	finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.c, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_c, container,
					false);
			return rootView;
		}
	}

}
