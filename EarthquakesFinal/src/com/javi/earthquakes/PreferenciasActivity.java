package com.javi.earthquakes;



import java.util.List;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;

public class PreferenciasActivity extends PreferenceActivity implements
OnSharedPreferenceChangeListener {
	SharedPreferences prefs;
	  AlarmManager alarmManager;
	  PendingIntent alarmIntent;
//	public void onBuildHeaders(List<Header> target) {
//	      loadHeadersFromResource(R.xml.userpreferenceheaders, target);
//	      SharedPreferences prefs =
//	    	       PreferenceManager.getDefaultSharedPreferences(this);
//	    	   prefs.registerOnSharedPreferenceChangeListener(this);
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
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
		boolean autoresf=pref.getBoolean("checkboxPref", false);
		String internet=pref.getString("intervalo_refresco", "15");
		String mag=pref.getString("magnitud_terremotos", "5");
//	
//		FragmentList fragment = (FragmentList) getFragmentManager().findFragmentById(R.id.fragmentoLista);
//		fragment.setMagnitud(mag);
		Log.d("traza", "intervalo de internet"+internet);
		Log.d("traza", "mag de terremoto"+mag);

		if(autoresf){
			Log.d("traza", "boton de autorefesh ON");
			this.setInexactRepeatingAlarm(Long.valueOf(internet));
		}
		else {
			Log.d("traza", "boton de autorefesh OF");
			this.stopInexactRepeatingAlarm(alarmIntent);
		}
	//	int mag = pref.getInt("magnitud_terremotos", 5);
		
	}
	
	private void setInexactRepeatingAlarm(long time) {
		Log.d("ALARM", "setInexactRepeatingAlarm");
	    //Get a reference to the Alarm Manager
	    alarmManager =
	    (AlarmManager)getSystemService(Context.ALARM_SERVICE);
	    //Set the alarm to wake the device if sleeping.
	    int alarmType = AlarmManager.RTC;
	    //Schedule the alarm to repeat every half hour.
	 //  long time =prefs.getString(arg0, arg1) ;
	    //Create a Pending Intent that will broadcast and action
	    Intent intentToFire = new Intent(this, MyServiceEarthquakes.class);
	    alarmIntent = PendingIntent.getBroadcast(this, 0,
	     intentToFire, 0);
	    //Wake up the device to fire an alarm in half an hour, and every
	    //half-hour after that.
	    alarmManager.setInexactRepeating(alarmType,
	                              0,
	                              10000,
	                              alarmIntent);
	}
	private void stopInexactRepeatingAlarm(PendingIntent pendingIntent) {
		Log.d("ALARM", "stopInexactRepeatingAlarm");
		alarmManager.cancel(pendingIntent);
	   
	}
	
	}
