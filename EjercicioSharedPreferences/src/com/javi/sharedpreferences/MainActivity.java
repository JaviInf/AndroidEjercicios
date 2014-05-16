package com.javi.sharedpreferences;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	private static final String INTERNET = "internet";
	private static final String AUTOREFESH ="autorefresh";
	private String MY_PREFS;
	TextView autorefres;
	SharedPreferences mySharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		autorefres=(TextView)findViewById(R.id.textView1);
		TextView internet=(TextView) findViewById(R.id.textView2);
		mySharedPreferences = getSharedPreferences(MY_PREFS,
                Activity.MODE_PRIVATE);
		
			autorefres.setText("AutoRefesh:"+mySharedPreferences.getBoolean(AUTOREFESH, false));
			


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(MainActivity.this, SettinsActivity.class);
            startActivity(intent);
          //  finish(); // finaliza la actividad "Adios"
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume(){
		super.onResume();
		autorefres.setText("AutoRefesh:"+mySharedPreferences.getBoolean(AUTOREFESH, false));
		
	}
}
