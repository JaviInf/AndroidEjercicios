package com.javi.sharedpreferences;




import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class PreferenciasActivity extends PreferenceActivity implements
OnSharedPreferenceChangeListener {
	
	public void onBuildHeaders(List<Header> target) {
	      loadHeadersFromResource(R.xml.userpreferenceheaders, target);
	      SharedPreferences prefs =
	    	       PreferenceManager.getDefaultSharedPreferences(this);
	    	   prefs.registerOnSharedPreferenceChangeListener(this);
	}
//public class PreferenciasActivity extends Activity {
//	public void onCreate(Bundle savedInstanceState) { 
//        super.onCreate(savedInstanceState); addPreferencesFromResource(R.xml.userpreferences);
//    }

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
