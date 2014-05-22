package com.javi.earthquakes;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadTerremotosTask extends AsyncTask<URL, ArrayList<Quakes>, ArrayList<Quakes>> {
	
	private Context contexto;
	private FragmentList listFragment;
	private EarthQuakeBD bd;
	
	public DownloadTerremotosTask(Context contexto, FragmentList listFragment) {
		super();
		this.contexto=contexto;
		this.listFragment=listFragment;
	}

	@Override
	protected ArrayList<Quakes> doInBackground(URL... arg0) {
		 bd= new EarthQuakeBD(contexto);
		 ArrayList<Quakes> listadoNuevo = new ArrayList<Quakes>();;
		Log.d("ASYNTASK ", "Json en ejecucion");
		JSONObject json=MiJSON.realizarConsulta();
		//Log.d("JSON", json.toString());
		try {
		JSONArray arrayFeatures = json.getJSONArray("features");
		for(int i=0; i< arrayFeatures.length(); i++) {
			Quakes q = new Quakes();
				JSONObject earthquake = arrayFeatures.getJSONObject(i);
			//	Log.d("JSON  earthquake", earthquake.toString());
				JSONObject propiedades =  earthquake.getJSONObject("properties");
			//	Log.d("JSON  propiedades", propiedades.toString());
				JSONArray coordinates =  earthquake.getJSONObject("geometry").getJSONArray("coordinates");
			//	Log.d("JSON  coordinates", coordinates.toString());
				
				// Crear Quake
				q.setId_str(earthquake.getString("id"));
				q.setPlace(propiedades.getString("place"));
				q.setTime(propiedades.getLong("time"));
				q.setDetail(propiedades.getString("detail"));
				q.setMagnitude(propiedades.getDouble("mag"));
				q.setLat(coordinates.getDouble(1));
				q.setLongi(coordinates.getDouble(0));
				q.setUrl(propiedades.getString("url"));
				q.setCreated_at(Long.valueOf((new Date().getTime())));
				q.setUpdated_at(Long.valueOf((new Date().getTime())));
				
				//bd.insert(q);
				//bd.close();
				listadoNuevo.add(q);
			}
			return listadoNuevo;
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected void onPostExecute(ArrayList<Quakes> listadoNuevo) {
	
	
		for( int i = 0 ; i < listadoNuevo.size() ; i++ ){
			Log.d("ASYNTASK y BD    antessss",""+ bd.getTerremotos(0).size());
			bd.insert(listadoNuevo.get(i));
			Log.d("ASYNTASK y BD    despues",""+ bd.getTerremotos(0).size());
			Log.d("ASYNTASK", "A„ADIDO nuevos terremotos a LA BD");
			listFragment.actualizarListadoTerremotos(listadoNuevo.get(i));
			}
		Log.d("ASYNTASK", "PROCESO FINALIZADO");
	}
	
}
