/**
 * 
 */
package com.francis.chatbot.service.model;

/**
 * @author francisphiri
 *
 */
public class Weather {
	
	private String latitude;
	private String longitude;
	private String timezone;
	private Currently currently;
	
	
	public Weather(){}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
	public Currently getCurrently() {
		return currently;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	public void setCurrently(Currently currently) {
		this.currently = currently;
	}

	@Override
    public String toString() {
	    return "Weather [latitude=" + latitude + ", longitude=" + longitude
	            + ", timezone=" + timezone + ", currently=" + currently + "]";
    }
	
	
	
	
}
