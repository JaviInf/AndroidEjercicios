package com.javi.earthquakes;

import java.util.ArrayList;

import android.os.AsyncTask;

public class DownloadTerremotosTask extends AsyncTask<String, Void, ArrayList<Quakes>> {

	@Override
	protected ArrayList<Quakes> doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	protected void onPostExecute(Long result) {
		
	
	}
	
	protected void onProgressUpdate(Integer... progress) {
	
	}

}
