package com.javi.earthquakes;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends Activity {
	private static final int SHOW_PREFERENCES = 0;
	private ActionBar actionBar;

	private TextView placeDetalle,fechaDetalle,mangitudDetalle,localizacionDetalle,
	profundidadDetalle,detalleDetalle, fecha2Detalle, magDetalle;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
	private SimpleDateFormat sdf2 = new SimpleDateFormat("EEE dd MMM  yyyy HH:mm:ss zzz", Locale.ENGLISH);
	
	 private Integer idQuake;
	 private Long timeQuake;
	 private String placeQuake;
	 private String quakeDetails;
	 private float LatQuake;
	 private float LongQuake;
	 private double magnitudeQuake;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		placeDetalle=(TextView)findViewById(R.id.placedetalle);
		fechaDetalle=(TextView)findViewById(R.id.fecha);
		mangitudDetalle=(TextView)findViewById(R.id.magnitudDetalle);
		localizacionDetalle=(TextView)findViewById(R.id.locationDetalle);
		profundidadDetalle=(TextView)findViewById(R.id.profundidadetalle);
		detalleDetalle=(TextView)findViewById(R.id.detalledetalle);
		magDetalle=(TextView)findViewById(R.id.mag);
		fecha2Detalle=(TextView)findViewById(R.id.fecha2);
		
		Bundle extras = getIntent().getExtras();		
		long ident = extras.getLong(FragmentList.ID);
		Log.d("FRAGMENTLIST", " "+ident);
		this.obtenerDatosTerremoto(ident);
	
	}
	private void obtenerDatosTerremoto(long ident) {
				ContentResolver cr = getContentResolver();
				String[] result_columns = new String[] {
				    MyContentProvider.PLACE,
				    MyContentProvider.MAGNITUDE,
				    MyContentProvider.TIME,
				    MyContentProvider.LAT,
				    MyContentProvider.LONG,
				    MyContentProvider.DETAIL,
				    MyContentProvider.ID
				   };
				Uri rowAddress =
				ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, ident);
				String where = null;
				String whereArgs[] = null;
				String order = null;
				// Return the specified rows.
				Cursor cursor = cr.query(rowAddress, result_columns,
				                               where, whereArgs, order);
				 while (cursor.moveToNext()) {
//					   
					 int QUAKE_ID_COLUMN_INDEX = cursor.getColumnIndexOrThrow(MyContentProvider.ID);
		              idQuake = cursor.getInt(QUAKE_ID_COLUMN_INDEX); 
		             int QUAKE_DATE_COLUMN_INDEX = cursor.getColumnIndexOrThrow(MyContentProvider.TIME);
		              timeQuake = cursor.getLong(QUAKE_DATE_COLUMN_INDEX);   
		             int QUAKE_PLACE_COLUMN_INDEX = cursor.getColumnIndexOrThrow(MyContentProvider.PLACE);
		             placeQuake = cursor.getString(QUAKE_PLACE_COLUMN_INDEX);		        
		             int QUAKE_DETAILS_COLUMN_INDEX = cursor.getColumnIndexOrThrow(MyContentProvider.DETAIL);   
		             quakeDetails= cursor.getString(QUAKE_DETAILS_COLUMN_INDEX);    
		             int QUAKE_LOCATION_LAT_INDEX = cursor.getColumnIndexOrThrow(MyContentProvider.LAT);         
		             LatQuake = cursor.getFloat(QUAKE_LOCATION_LAT_INDEX);         
		             int QUAKE_LOCATION_LONG_INDEX = cursor.getColumnIndexOrThrow(MyContentProvider.LONG);             
		             LongQuake= cursor.getFloat(QUAKE_LOCATION_LONG_INDEX);        
		             int QUAKE_MAGNITUDE_INDEX = cursor.getColumnIndexOrThrow(MyContentProvider.MAGNITUDE);
		             magnitudeQuake= cursor.getDouble(QUAKE_MAGNITUDE_INDEX);

				}
				    cursor.close();
//				//Rellenar datos
				placeDetalle.setText(placeQuake);
				fechaDetalle.setText(String.valueOf(String.valueOf(sdf.format(timeQuake))));
				mangitudDetalle.setText(String.valueOf(magnitudeQuake));
				String lati=String.valueOf(LatQuake);
				String longi=String.valueOf(LongQuake);
				localizacionDetalle.setText("Localizacion: "+lati+" "+longi);	
				profundidadDetalle.setText("Profundidad: ");
				detalleDetalle.setText(quakeDetails);
				magDetalle.setText(String.valueOf("Magnitud: "+magnitudeQuake));
				fecha2Detalle.setText("Fecha: "+String.valueOf(sdf2.format(timeQuake)));
		
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
