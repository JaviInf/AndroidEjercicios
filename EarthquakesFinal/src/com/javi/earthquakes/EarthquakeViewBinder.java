package com.javi.earthquakes;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorTreeAdapter.ViewBinder;
import android.widget.TextView;

public class EarthquakeViewBinder implements android.support.v4.widget.SimpleCursorAdapter.ViewBinder{

   public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
       int date = cursor.getColumnIndex(MyContentProvider.TIME);

       if (view.getId()== R.id.times) {
    	   SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM  yyyy HH:mm:ss zzz", Locale.ENGLISH);
    	    int time = cursor.getColumnIndex(MyContentProvider.TIME);
    	    Long timeLong = cursor.getLong(time);
    	    String dateString = sdf.format(timeLong);
    	    ((TextView) view).setText(dateString);
         return true;
       }
   	if (view.getId()==R.id.magnitud){
   	    int mag = cursor.getColumnIndex(MyContentProvider.MAGNITUDE);
   	    double magnitudValor= cursor.getDouble(mag);
   	    ((TextView) view).setText(	 String.format( "%.2f",magnitudValor));

   	 if (magnitudValor>=5)   ((TextView) view).setBackgroundResource(R.drawable.rounded_edittext_red);
	    else if (magnitudValor>3)   ((TextView) view).setBackgroundResource(R.drawable.rounded_edittext_green);
	    else if (magnitudValor>2)   ((TextView) view).setBackgroundResource(R.drawable.ounded_edittext_yellow);
	    else  ((TextView) view).setBackgroundResource(R.drawable.rounded_edittext);

   	    
	   return true;
   }
      
return false;

}

}