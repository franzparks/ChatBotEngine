/**
 * 
 */
package com.francis.chatbot.service.model;

/**
 * @author francisphiri
 *
 */
public class Coords {
	
	private double latitude;
	private double longitude;
	
	public Coords(double latitude, double longitude) {
	    super();
	    this.latitude = latitude;
	    this.longitude = longitude;
    }
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	
	
}
