package com.javi.earthquakes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


public class QueryEarthQuakesTask extends
		AsyncTask<Void, Void, ArrayList<Quakes>> {

	private FragmentList fragmento;
	private EarthQuakeBD bd;
	private Context contexto;
	private SharedPreferences prefs;
	private double minMag;


	public QueryEarthQuakesTask(FragmentList fragment, Context context) {
		super();
		this.fragmento = fragment;
		this.contexto = context;
		this.bd= new EarthQuakeBD(contexto);
	}

	@Override
	protected ArrayList<Quakes> doInBackground(Void... params) {
		
		String mag ="0";
		prefs = PreferenceManager.getDefaultSharedPreferences(contexto);
//		String magi=prefs.getString("magnitud_terremotos", "5");
//		prefs.getInt("magnitud_terremotos", 0);
//		
//
		
		ArrayList<Quakes> list =  new ArrayList<Quakes>();	
		list=bd.getTerremotos(Double.parseDouble(prefs.getString("magnitud_terremotos", "5")));
		return list;
	}

	protected void onPostExecute(ArrayList<Quakes> result) {
	//	fragmento.resetearActualizarLista(result);
//		CharSequence text = "Se acaba de actualizar la lista de desde la BD";
//		int duration = Toast.LENGTH_SHORT;
//		 
//		Toast toast = Toast.makeText(contexto, text, duration);
//		toast.show();

	}

}