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
		
		public String time;
		public String summary;
		public String icon;
		public String nearestStormDistance;
		public String nearestStormBearing;
		public String precipIntensity;
		public String precipProbability;
		public String temperature;
		public String apparentTemperature;
		public String dewPoint;
		public String humidity;
		public String pressure;
		public String windSpeed;
		public String windGust;
		public String windBearing;
		public String cloudCover;
		public String uvIndex;
		public String visibility;
		public String ozone;
    }
}
