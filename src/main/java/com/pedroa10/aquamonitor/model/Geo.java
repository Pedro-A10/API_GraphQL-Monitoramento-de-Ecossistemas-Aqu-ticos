package com.pedroa10.aquamonitor.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Geo {
	private double latitude;
	private double longitude;

	public Geo() {
	}
	
	public Geo(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
