package com.javi.intentycamara;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.provider.ContactsContract;
import android.provider.MediaStore;

public class MainActivity extends Activity {

	public static final String MESSAGE = "resultado";
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
//				
//				File photoFile=null;
//				try{
//					photoFile=createImageFile();
//				}
//				catch(IOException ex){
//					
//				}
//				
//				if(photoFile!=null){
//					
//				}
			}
		});
		
		botonContacto.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

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
			String cNumber = null;
			if (resultCode == RESULT_OK) {
				 Uri contactData = data.getData();
		            Cursor c =  managedQuery(contactData, null, null, null, null);
		         
		            	if (c.moveToFirst()) {
		                    String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
		                    String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

		                      if (hasPhone.equalsIgnoreCase("1")) {
		                     Cursor phones = getContentResolver().query( 
		                                  ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
		                                  ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
		                                  null, null);
		                        phones.moveToFirst();
		                        cNumber = phones.getString(phones.getColumnIndex("data1"));
		                      }
		                    String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		                    texto.setText("NUMERO: "+cNumber+" NOMBRE: "+name);
		            	}}
			break;
		}
	}
}
