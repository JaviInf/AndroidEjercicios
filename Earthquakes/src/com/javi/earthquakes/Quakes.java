package com.javi.earthquakes;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.R.integer;

public class Quakes {
	
	private int id;
	private String id_str;
	private String place;
	private long time;
	private String detail;
	private double magnitude;
	private double lat;
	private double longi;
	private String url;
	private long created_at;
	private long updated_at;
	
	public Quakes() {	
	}
	
	public Quakes(int id, String id_str, String place, long time, String detail,
			double magnitude, double lat, double longi, String url,
			long created_at, long updated_at) {
		
		super();
		this.id_str=id_str;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getId_str() {
		return id_str;
	}

	public void setId_str(String id_str) {
		this.id_str = id_str;
	}
	

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long l) {
		this.time = l;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(double d) {
		this.magnitude = d;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLongi() {
		return longi;
	}

	public void setLongi(double longi) {
		this.longi = longi;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long long1) {
		this.created_at = long1;
	}

	public long getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(long updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	  public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm", Locale.ENGLISH);
		String dateString = sdf.format(time);
	    return place + " "+dateString+": "+detail +" "+ magnitude + " ";
	  }
	 
	
}
