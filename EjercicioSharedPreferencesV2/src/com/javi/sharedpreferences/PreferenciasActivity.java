package com.javi.sharedpreferences;


import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenciasActivity extends PreferenceActivity {
	public void onBuildHeaders(List<Header> target) {
	      loadHeadersFromResource(R.xml.userpreferenceheaders, target);

	}
	}