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

public class MiJSON {

	public static JSONObject realizarConsulta(String json) {
		try {
			URL url = new URL(json);

			// Create a new HTTP URL connection
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				Log.d("JSON", "CONSULTA SATISFACTORIA");
				InputStream in = httpConnection.getInputStream();
				return procesarConsulta(in);

			}
		} catch (MalformedURLException e) {
			Log.d("JSON", "Malformed URL Exception.", e);
		} catch (IOException e) {
			Log.d("JSON", "IO Exception.", e);
		}
		return null;

	}

	private static JSONObject procesarConsulta(InputStream in) {
		// TODO Auto-generated method stub
		Log.d("JSON", "empieza procesamiento de respuesta");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		String inputStr;
		try {
			while ((inputStr = br.readLine()) != null) {
				sb.append(inputStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return (new JSONObject(sb.toString()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
