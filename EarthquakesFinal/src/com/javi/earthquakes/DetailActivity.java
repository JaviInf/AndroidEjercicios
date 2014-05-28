package com.javi.earthquakes;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends Activity implements
LoaderCallbacks<Cursor>{
	private static final int ident1 = 3;
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
	 long ident;
	 
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
		
		detalleDetalle.setOnClickListener(new View.OnClickListener() {

	        @Override
			public void onClick(View view) {

	         Log.d("DETAIL ACTIVITY", "WebView");
	         Intent intent = new Intent(DetailActivity.this, WebViewActivity.class);
	         intent.putExtra("URL", quakeDetails);
				startActivity(intent);
	        }

	    });

		Bundle extras = getIntent().getExtras();		
		ident = extras.getLong(FragmentList.ID);
		Log.d("FRAGMENTLIST", " "+ident);
		getLoaderManager().initLoader(ident1, null, this);
	
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
//			Intent intent = new Intent(DetailActivity.this, PreferenciasActivity.class);
//			startActivity(intent);

			return true;
		} else if(id == android.R.id.home) {
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
        
		return super.onOptionsItemSelected(item);
	}
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Uri rowAddress =ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, ident);
		String[] result_columns = new String[] {
			    MyContentProvider.PLACE,
			    MyContentProvider.MAGNITUDE,
			    MyContentProvider.TIME,
			    MyContentProvider.LAT,
			    MyContentProvider.LONG,
			    MyContentProvider.DETAIL,
			    MyContentProvider.ID
			   };
			String where = null;
			String whereArgs[] = null;
			String order = null;
		CursorLoader loader = new CursorLoader(this,
				rowAddress, result_columns, where,
				whereArgs, null);
		return loader;
	}
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		 while (data.moveToNext()) {
		   
		 int QUAKE_ID_COLUMN_INDEX = data.getColumnIndexOrThrow(MyContentProvider.ID);
        idQuake = data.getInt(QUAKE_ID_COLUMN_INDEX); 
       int QUAKE_DATE_COLUMN_INDEX = data.getColumnIndexOrThrow(MyContentProvider.TIME);
        timeQuake = data.getLong(QUAKE_DATE_COLUMN_INDEX);   
       int QUAKE_PLACE_COLUMN_INDEX = data.getColumnIndexOrThrow(MyContentProvider.PLACE);
       placeQuake = data.getString(QUAKE_PLACE_COLUMN_INDEX);		        
       int QUAKE_DETAILS_COLUMN_INDEX = data.getColumnIndexOrThrow(MyContentProvider.DETAIL);   
       quakeDetails= data.getString(QUAKE_DETAILS_COLUMN_INDEX);    
       int QUAKE_LOCATION_LAT_INDEX = data.getColumnIndexOrThrow(MyContentProvider.LAT);         
       LatQuake = data.getFloat(QUAKE_LOCATION_LAT_INDEX);         
       int QUAKE_LOCATION_LONG_INDEX = data.getColumnIndexOrThrow(MyContentProvider.LONG);             
       LongQuake= data.getFloat(QUAKE_LOCATION_LONG_INDEX);        
       int QUAKE_MAGNITUDE_INDEX = data.getColumnIndexOrThrow(MyContentProvider.MAGNITUDE);
       magnitudeQuake= data.getDouble(QUAKE_MAGNITUDE_INDEX);

	}
	    data.close();
	//Rellenar datos
	placeDetalle.setText(placeQuake);
	fechaDetalle.setText(String.valueOf("  "+String.valueOf(sdf.format(timeQuake))));
	mangitudDetalle.setText(String.format("%.2f",magnitudeQuake));
	String lati=String.valueOf(LatQuake);
	String longi=String.valueOf(LongQuake);
	localizacionDetalle.setText("Localizacion: "+lati+" "+longi);	
	profundidadDetalle.setText("Profundidad: ");
	detalleDetalle.setText(quakeDetails);
	magDetalle.setText(String.valueOf("Magnitud: "+magnitudeQuake));
	fecha2Detalle.setText("Fecha: "+String.valueOf(sdf2.format(timeQuake)));
		
		
	}
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		
	}
}
