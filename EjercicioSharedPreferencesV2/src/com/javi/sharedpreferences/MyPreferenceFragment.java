package com.javi.sharedpreferences;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.preference.PreferenceFragment;

public class MyPreferenceFragment  extends PreferenceFragment { 
	public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); addPreferencesFromResource(R.xml.userpreferenceheaders);
    }
	
}
