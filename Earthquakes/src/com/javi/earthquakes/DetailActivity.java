package com.javi.earthquakes;



import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailActivity extends Activity {
	private static final int SHOW_PREFERENCES = 0;

	TextView placeDetalle,fechaDetalle,mangitudDetalle,localizacionDetalle,
	profundidadDetalle,detalleDetalle, fecha2Detalle, magDetalle;
	SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
	SimpleDateFormat sdf2 = new SimpleDateFormat("EEE dd MMM  yyyy HH:mm:ss zzz", Locale.ENGLISH);

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		placeDetalle=(TextView)findViewById(R.id.placedetalle);
		fechaDetalle=(TextView)findViewById(R.id.fecha);
		mangitudDetalle=(TextView)findViewById(R.id.magnitudDetalle);
		localizacionDetalle=(TextView)findViewById(R.id.locationDetalle);
		profundidadDetalle=(TextView)findViewById(R.id.profundidadetalle);
		detalleDetalle=(TextView)findViewById(R.id.detalledetalle);
		magDetalle=(TextView)findViewById(R.id.mag);
		fecha2Detalle=(TextView)findViewById(R.id.fecha2);
		
		Bundle extras = getIntent().getExtras();
		
		Integer ident = extras.getInt(FragmentList.ID);
		EarthQuakeBD bd= new EarthQuakeBD(getApplicationContext());
		
		Quakes q=bd.getTerremoto(ident);
		
		//Rellenar datos
		placeDetalle.setText(q.getPlace());
		fechaDetalle.setText(String.valueOf(String.valueOf(sdf.format(q.getTime()))));
		mangitudDetalle.setText(String.valueOf(q.getMagnitude()));
		String lati=String.valueOf(+q.getLat()).substring(0,7);
		String longi=String.valueOf(+q.getLongi()).substring(0,7);
		localizacionDetalle.setText("Localizacion: "+lati+" "+longi);	
		profundidadDetalle.setText("Profundidad: ");
		detalleDetalle.setText(q.getDetail());
		magDetalle.setText(String.valueOf("Magnitud: "+q.getMagnitude()));
		fecha2Detalle.setText("Fecha: "+String.valueOf(sdf2.format(q.getTime())));
		
		
		
	}
	public void cargarDatosTerremoto(Quakes q, TextView ...v){
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(DetailActivity.this, PreferenciasActivity.class);
			startActivityForResult(intent, SHOW_PREFERENCES);

			return true;
		} else if(id == android.R.id.home) {
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
        
		return super.onOptionsItemSelected(item);
	}

	

	

}
