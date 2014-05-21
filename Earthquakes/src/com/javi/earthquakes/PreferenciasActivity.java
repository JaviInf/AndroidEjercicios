package com.javi.earthquakes;



import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;

public class PreferenciasActivity extends PreferenceActivity implements
OnSharedPreferenceChangeListener {
	
//	public void onBuildHeaders(List<Header> target) {
//	      loadHeadersFromResource(R.xml.userpreferenceheaders, target);
//	      SharedPreferences prefs =
//	    	       PreferenceManager.getDefaultSharedPreferences(this);
//	    	   prefs.registerOnSharedPreferenceChangeListener(this);
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);

		// Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPreferenceFragment())
                .commit();
	}

	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences pref, String key) {
		// TODO Auto-generated method stub
		//boolean autoresf=pref.getBoolean("checkboxPref", true);
		boolean autoresf=pref.getBoolean("checkboxPref", true);
		String internet=pref.getString("intervalo_refresco", "15");
		if(autoresf){
			Log.d("traza", "boton de autorefesh ON");
		}
		else {
			Log.d("traza", "boton de autorefesh OF");
		}
		Log.d("traza", internet);
		
		
	}
	
	
	}
