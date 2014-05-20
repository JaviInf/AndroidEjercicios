package com.javi.earthquakes;

import android.R.integer;

public class Quakes {
	
	private integer id;
	private String place;
	private integer time;
	private String detail;
	private Float magnitude;
	private Float lat;
	private Float longi;
	private String url;
	private integer created_at;
	private integer updated_at;
	
	public Quakes(integer id, String place, integer time, String detail,
			Float magnitude, Float lat, Float longi, String url,
			integer created_at, integer updated_at) {
		
		super();
		this.id = id;
		this.place = place;
		this.time = time;
		this.detail = detail;
		this.magnitude = magnitude;
		this.lat = lat;
		this.longi = longi;
		this.url = url;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	
	
}
