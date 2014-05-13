package com.javi.intentycamara;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.MediaStore;

public class MainActivity extends Activity {

	public static final String MESSAGE = "resultado";
	private static final int RESULTADO = 0;
	private static final int FORMULARIO = 1;
	static final int REQUEST_IMAGE_CAPTURE = 2;
	protected static final int PICK_CONTACT_FROM_LIST = 3;
	protected static final int PICK_CONTACT=4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button botonForm = (Button) findViewById(R.id.form);
		Button botonCamara = (Button) findViewById(R.id.camara);
		Button botonContacto = (Button) findViewById(R.id.contacto);

		final EditText cajaTexto = (EditText) findViewById(R.id.editText1);

		

		botonForm.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,
						FormActivity.class);
				intent.putExtra(MESSAGE, cajaTexto.getText().toString());
				startActivityForResult(intent, FORMULARIO);
				cajaTexto.setText("");

			}
		});

		botonCamara.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takePictureIntent,
							REQUEST_IMAGE_CAPTURE);
				}
			}
		});
		
		botonContacto.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
//				Intent intent = new Intent();
//				intent.setComponent(new ComponentName(
//				     "com.android.contacts"
//				    ,"com.android.contacts.DialtactsContactsEntryActivity"));	        
//				startActivityForResult(intent,PICK_CONTACT_REQUEST); 
//			}
//				Intent i= new Intent(Intent.ACTION_PICK,People.CONTENT_URI); 
//				startActivityForResult(i,PICK_CONTACT_FROM_LIST); }
				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);}
		});

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		TextView texto = (TextView) findViewById(R.id.cajamain);
		ImageView imagen = (ImageView) findViewById(R.id.imageView1);
		switch (requestCode) {
		case (FORMULARIO):
			if (resultCode == Activity.RESULT_OK) {
				String message = data.getStringExtra(FormActivity.MESSAGE);
				texto.setText(message);
			} else if (resultCode == Activity.RESULT_CANCELED) {

			}
			break;
		case (REQUEST_IMAGE_CAPTURE):
			if (resultCode == RESULT_OK) {
		        Bundle extras = data.getExtras();
		        Bitmap imageBitmap = (Bitmap) extras.get("data");
		        imagen.setImageBitmap(imageBitmap);
		    }
			break;
			
		case (PICK_CONTACT):
			if (resultCode == RESULT_OK) {
				 Uri contactData = data.getData();
		            Cursor c =  managedQuery(contactData, null, null, null, null);
		            if (c.moveToFirst()) {
		              String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
		              texto.setText(name);
		            }
		    }
			break;
		}
	}
}
