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

public class BActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		Button botonA=(Button) findViewById(R.id.button1);
		Button botonC=(Button) findViewById(R.id.button2);
		Button botonCerrar=(Button) findViewById(R.id.button3);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override 
	protected void onResume(){
		 super.onResume();
		 Log.d("BActivity", "BActivity ejecucion de onCreate");
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		Log.d("BActivity", "BActivity ejecucion de onPause");
	}
	
	@Override 
	protected void onStop(){
		super.onStop();
		 Log.d("BActivity", "BActivity ejecucion de onStop");
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		Log.d("BActivity", "BActivity ejecucion de onDestroy");
	}
	
	public void startA(View v){
		Intent intent= new Intent(this, MainActivity.class);
		startActivity(intent);
		
	}
	
	public void startC(View v){
		Intent intent= new Intent(this, CActivity.class);
		startActivity(intent);
	}
	
	public void cerrar(View v){
	finish();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.b, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_b, container,
					false);
			return rootView;
		}
	}

}
