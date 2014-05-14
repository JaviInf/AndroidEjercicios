package com.javi.intentycamara;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyRecibidor extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();

		if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {
			Log.d("RECIVER", "SMS entrante en el telefono");

		} else if (action.equals("android.intent.action.PHONE_STATE")) {
			Log.d("RECIVER", "LLamada entrante en el telefono");
		}
	 else if (action.equals("android.intent.action.AIRPLANE_MODE")) {
		Log.d("RECIVER", "Modo avion detectado");
	}
	}

}
