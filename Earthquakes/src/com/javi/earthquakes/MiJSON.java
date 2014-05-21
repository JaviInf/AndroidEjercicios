package com.javi.earthquakes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public  abstract class MiJSON {
	
	public static JSONObject realizarConsulta() {
//		Log.d("TAG", "se esta realizando la consulta");
		String myFeed = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
		try {
			URL url = new URL(myFeed);

			// Create a new HTTP URL connection
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
//				Log.d("TAG", "CONSULTA SATISFACTORIA");
				InputStream in = httpConnection.getInputStream();
				return procesarConsulta(in);

			}
		} catch (MalformedURLException e) {
			Log.d("TAG", "Malformed URL Exception.", e);
		} catch (IOException e) {
			Log.d("TAG", "IO Exception.", e);
		}
		return null;

	}

	private static JSONObject procesarConsulta(InputStream in) {
//		Log.d("TAG", "empieza procesamiento de respuesta");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Log.d("TAG", sb.toString());
		try {
			JSONObject json = new JSONObject(sb.toString());
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
}
