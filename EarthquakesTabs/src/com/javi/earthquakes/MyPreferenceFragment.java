package com.javi.earthquakes;


import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MyPreferenceFragment  extends PreferenceFragment { 
	public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); addPreferencesFromResource(R.xml.userpreferences);
    }
	
}
