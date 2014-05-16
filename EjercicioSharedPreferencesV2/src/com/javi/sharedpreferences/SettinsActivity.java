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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ToggleButton;
import android.os.Build;

public class SettinsActivity extends Activity {
	
	private SharedPreferences mySharedPreferences;
	private ToggleButton togleAutorefresh;
	private Spinner spinnerInternet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settins);
		
		mySharedPreferences = getSharedPreferences(MainActivity.MY_PREFS,
                Activity.MODE_PRIVATE);
	
		 togleAutorefresh= (ToggleButton) findViewById(R.id.autorefresh);
		 spinnerInternet=(Spinner) findViewById(R.id.internetSpinner);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.tiposInternet, android.R.layout.simple_spinner_item);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerInternet.setAdapter(adapter);
		
		 SharedPreferences.Editor editor = mySharedPreferences.edit();
		
		togleAutorefresh.setChecked(mySharedPreferences.getBoolean(MainActivity.AUTOREFESH, false));
	
				
		
	}

	@Override
	  protected void onPause() {
	         super.onPause();
	         SharedPreferences.Editor editor = mySharedPreferences.edit();
	         editor.putBoolean(MainActivity.AUTOREFESH, togleAutorefresh.isChecked());
	     //   editor.putString(MainActivity.INTERNET, spinnerInternet.)
	         editor.apply();
	     }

}
