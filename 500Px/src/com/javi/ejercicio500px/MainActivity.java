package com.javi.ejercicio500px;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("TAG", " ejecucion de on create");

		TextView informacion=(TextView)findViewById(R.id.informacion);
		
		Button boton = (Button) findViewById(R.id.consultaJson);
		boton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("TAG", " creacion de un nuevo thread");
				Thread t = new Thread(new Runnable() {
					public void run() {
						Log.d("TAG", " ejecucion de un nuevo thread");
						realizarConsulta();
					}
				});
				t.start();
			}
		});

		Button botonDescarga = (Button) findViewById(R.id.descarga);

		botonDescarga.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				realizarDescarga();
			}
		});

	}

	protected void realizarDescarga() {
		// TODO Auto-generated method stub
		String serviceString = Context.DOWNLOAD_SERVICE;
		DownloadManager downloadManager;
		downloadManager = (DownloadManager) getSystemService(serviceString);
		Uri uri = Uri
				.parse("http://developer.android.com/shareables/icon_templates-v4.0.zip");
		DownloadManager.Request request = new Request(uri);
		request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		request.setDestinationInExternalFilesDir(this,
				Environment.DIRECTORY_DOWNLOADS,
				"DescargaEjercicioInternet.zip");
		final long reference = downloadManager.enqueue(request);
		// registrar el receiver
		registrarRecibidor(reference, downloadManager);

	}

	private void registrarRecibidor(final Long referencia, final DownloadManager manager) {
		IntentFilter filter = new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		BroadcastReceiver receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				long referenceNueva = intent.getLongExtra(
						DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				if (referencia == referenceNueva) {
					
					Query completadoQuery=new Query();
					completadoQuery.setFilterById(referencia);
					
					Cursor completadoCursor= manager.query(completadoQuery);
					int identificadorNombre=completadoCursor.getColumnIndex(manager.COLUMN_LOCAL_FILENAME);
					int identificadorTama–o=completadoCursor.getColumnIndex(manager.COLUMN_TOTAL_SIZE_BYTES);
					String nombre = null;
					String tama–o = null;
					while (completadoCursor.moveToNext()) {
					   nombre = completadoCursor.getString(identificadorNombre);
					   tama–o = completadoCursor.getString(identificadorTama–o);
					   
					}
					completadoCursor.close();
					
					TextView informacion=(TextView)findViewById(R.id.informacion);
					informacion.setText("NOMBRE: "+nombre+"  TAMA„O: "+tama–o);					
					
				}
			}
		};
		registerReceiver(receiver, filter);

	}

	public void realizarConsulta() {
		Log.d("TAG", "se esta realizando la consulta");
		String myFeed = "http://www.arkaitzgarro.com/android/photos.json";
		try {
			URL url = new URL(myFeed);

			// Create a new HTTP URL connection
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				Log.d("TAG", "CONSULTA SATISFACTORIA");
				InputStream in = httpConnection.getInputStream();
				procesarConsulta(in);

			}
		} catch (MalformedURLException e) {
			Log.d("TAG", "Malformed URL Exception.", e);
		} catch (IOException e) {
			Log.d("TAG", "IO Exception.", e);
		}

	}

	private void procesarConsulta(InputStream in) {
		Log.d("TAG", "empieza procesamiento de respuesta");
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
			procesareJSON(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void procesareJSON(JSONObject json) {
		String[] imagenes;
		try {
			JSONArray fotos = json.getJSONArray("photos");
			// Log.d("TAG","fotos.....:   "+fotos);
			imagenes = new String[fotos.length()];
			for (int i = 0; i < fotos.length(); i++) {
				JSONObject imagen = fotos.getJSONObject(i);
				imagenes[i] = imagen.getString("image_url");
				Log.i("TAG", "imagen en la posicion array " + i + ","
						+ imagenes[i]);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
