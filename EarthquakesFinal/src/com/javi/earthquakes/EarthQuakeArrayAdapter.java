package com.javi.earthquakes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EarthQuakeArrayAdapter extends ArrayAdapter<Quakes> {
	private final Context context;
	private final ArrayList<Quakes> lista;
	
	public EarthQuakeArrayAdapter(Context context, ArrayList<Quakes> lista) {
		
		super(context, R.layout.list_row_item, lista);

		this.context = context;
		this.lista = lista;
	}

	static class ViewHolder {
		public TextView magnitud;
		public TextView place;
		public TextView time;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.list_row_item, null);

			// Configure view holder
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.magnitud = (TextView) rowView.findViewById(R.id.magnitud);
			viewHolder.place = (TextView) rowView.findViewById(R.id.places);
			viewHolder.time = (TextView) rowView.findViewById(R.id.times);

			rowView.setTag(viewHolder);
		}

		// Fill data
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.magnitud.setText(String.valueOf(lista.get(position).getMagnitude()));
		holder.place.setText(String.valueOf(lista.get(position).getPlace()));
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);
		holder.time.setText(String.valueOf(sdf.format(lista.get(position).getTime())));

		return rowView;
	}

}
