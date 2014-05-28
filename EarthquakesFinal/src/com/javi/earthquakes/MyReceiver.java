package com.javi.earthquakes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
	public MyReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Uri data = intent.getData();
		Log.d("RECEIVER", "ACABABO de detectar algo");
		
	}
}
