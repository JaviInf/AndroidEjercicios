package com.javi.earthquakes;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



public class MainActivity extends Activity {
	
	private static final int SHOW_PREFERENCES = 0;
	EarthQuakeBD bd;
	FragmentList pestaña1frag;
    Fragment pestaña2frag;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		FragmentManager fragmentManager = getFragmentManager();
//		fragmentManager.beginTransaction().add(R.id.container, new FragmentList(), "list").commit();
	ActionBar actionBar = getActionBar();
			
			//Establecemos el modo de navegacionn por pestañas
		    actionBar.setNavigationMode(
		    		ActionBar.NAVIGATION_MODE_TABS);
		    
		    //Ocultamos el titulo de la actividad
		    //abar.setDisplayShowTitleEnabled(false);
		    
		    //Creamos las pestañas
		    ActionBar.Tab pestaña1 = 
		    		actionBar.newTab().setIcon(R.drawable.list);
		    
	        ActionBar.Tab pestaña2 = 
	        		actionBar.newTab().setIcon(R.drawable.mapa2);
	        
	        //Creamos los fragments de cada pestaña
	         pestaña1frag = new FragmentList();
	 
	         pestaña2frag = new Fragment();
	        
	        //Asociamos los listener a las pestañas
	        pestaña1.setTabListener(new MiTabListener(pestaña1frag));
	  
	        pestaña2.setTabListener(new MiTabListener(pestaña2frag));
	        
	        //Añadimos las pestañas a la action bar
	        actionBar.addTab(pestaña1);
	        actionBar.addTab(pestaña2);
	
	
//	// Get the Content Resolver.
//	ContentResolver cr = getContentResolver();
//	// Specify the result column projection. Return the minimum set
//	// of columns required to satisfy your requirements.
//	String[] result_columns = new String[] {
//	    MyContentProvider.ID,
//	    MyContentProvider.PLACE };
//	// Append a row ID to the URI to address a specific row.
////	Uri rowAddress =
////	ContentUris.withAppendedId(MyContentProvider.CONTENT_URI);
//	// Replace these with valid SQL statements as necessary.
//	String where = null;
//	String whereArgs[] = null;
//	String order = null;
//	// Return the specified rows.
//	Cursor cursor = cr.query(MyContentProvider.CONTENT_URI, result_columns,
//	                               where, whereArgs, order);
//	
//	  while (cursor.moveToNext()) {
//		    
//     	 int QUAKE_ID_COLUMN_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.ID);
//         Integer idQuake = cursor.getInt(QUAKE_ID_COLUMN_INDEX); 
//   
//          int QUAKE_PLACE_COLUMN_INDEX = cursor.getColumnIndexOrThrow(EarthquakesDBOpenHelper.PLACE);
//          String placeQuake = cursor.getString(QUAKE_PLACE_COLUMN_INDEX);
//          Log.d("ContentProvider", "Prueba content:"+placeQuake);
//   
//         }
//        cursor.close();
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(MainActivity.this, PreferenciasActivity.class);
			startActivityForResult(intent, SHOW_PREFERENCES);
			return true;
		}
		else if (id == R.id.action_reload) {
//			DownloadTerremotosTask download= new DownloadTerremotosTask(this, (FragmentList) pestaña1frag);
//	        download.execute();
//			pestaña1frag.queryTerremotosJSONAsynTask();
//			CharSequence text = "Se acaba de actualizar la lista de terremotos!";
//			int duration = Toast.LENGTH_SHORT;
//			Toast toast = Toast.makeText(this, text, duration);
//			toast.show();
//			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
