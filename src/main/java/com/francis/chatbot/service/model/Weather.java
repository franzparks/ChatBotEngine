/**
 * 
 */
package com.francis.chatbot.service.model;

/**
 * @author francisphiri
 *
 */
public class Weather {
	
	public String latitude;
	public String longitude;
	public String timezone;
	
	public static class Currently {
		
		
    }
	
	public Weather(){}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
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
	
	
}
