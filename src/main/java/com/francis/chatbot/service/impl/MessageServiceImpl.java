/**
 * 
 */
package com.francis.chatbot.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.francis.chatbot.constants.Constants;
import com.francis.chatbot.model.Message;
import com.francis.chatbot.model.TextMessage;
import com.francis.chatbot.service.MessageService;
import com.francis.chatbot.service.model.Coordinates;
import com.francis.chatbot.service.model.gmap.GeoResponse;
import com.francis.chatbot.service.model.gmap.GeoResponseParser;
import com.francis.chatbot.service.model.darksky.Weather;

/**
 * @author francisphiri
 *
 */
@Service
public class MessageServiceImpl implements MessageService{
	
	private final RestTemplate restTemplate;
	private final String errorMessage = "Place cannot be found";
	private final String controlMessage = "I don't know many things but you can ask me about the weather."
			+ "\n"
			+ " I can answer questions such as:" + "\n"
			+ " what's the weather in <Location>,"+"\n"+ "weather in <Location>,"+"\n"+ "<Location> weather"+"\n"
			+ " Note: <Location> can be any city or reference to a city such as SF or San Francisco, or 94100";
	
	public MessageServiceImpl(RestTemplateBuilder restTemplateBuilder){
		this.restTemplate = restTemplateBuilder.build();
	}

	/**
	 * Process the message given by a user
	 */
	@Override
    public Message processMessage(String message) {
		System.out.println(controlMessage);
	    TextMessage processedMessage = null;
	    if(!message.toLowerCase().contains("weather")){
	    	return new TextMessage(controlMessage);
	    }
	    String location = filterLocationFromMessage(message);
	    
	    Coordinates coords =  getAddressCoordinates(location);
	   
	    if(coords == null){
	    	processedMessage = new TextMessage(errorMessage);
	    }else{
	    	
	    	Weather w = getCurrentWeather(coords.getLatitude(), coords.getLongitude());
	    	String start = "Currently it's ";
	    	String end = "F. ";
	    	start += w.getCurrently().getTemperature();
	    	end += w.getCurrently().getSummary();
	    	processedMessage = new TextMessage(start+end);
	    	System.out.println(start);
	    }
	    return processedMessage;
    }
	
	/**
	 * Filtering a location from a given message
	 * @param message
	 * @return location
	 */
	private String filterLocationFromMessage(String message) {
		
		//map of helper words which are not a location
	    Map<String, String> mapOfHelperWords = new HashMap<String, String>();
	    
	    String location = "";
	    
	    mapOfHelperWords.put("weather", "weather");
	    mapOfHelperWords.put("in", "in");
	    mapOfHelperWords.put("the", "the");
	    mapOfHelperWords.put("like", "like");
	    mapOfHelperWords.put("forecast", "forecast");
	    mapOfHelperWords.put("now", "now");
	    mapOfHelperWords.put("current", "current");
	    mapOfHelperWords.put("currently", "currently");
	    mapOfHelperWords.put("what", "what");
	    mapOfHelperWords.put("what's", "what's");
	    mapOfHelperWords.put("what is", "what is");
	    
	    String[] words = message.split("\\s");
	    
	    for(String word : words){
	    	if(!mapOfHelperWords.containsKey(word.toLowerCase())){
	    		location += word;
	    	}
	    }
	    return location;
    }

	/**
	 * Get coordinates of a given address from the gmap api
	 * @param address
	 * @return Coordinates
	 */
	private Coordinates getAddressCoordinates(String address){
		
		String fullUrl = Constants.GMAPS_BASE_URL + "{address}" + "&key="+ Constants.GMAPS_API_KEY;
		
		String results = this.restTemplate.getForObject(fullUrl, String.class, address);
		
		return createCoordinates(results);
	}
	
	/**
	 * Get coordinates from a gmap api response
	 * @param json
	 * @return Coordinates
	 */
	private Coordinates createCoordinates(String json){
		
		GeoResponse response = new GeoResponseParser().parse(json);
		
		if(response.status.equals("OK")){
			double lat = response.results.get(0).geometry.location.lat;
			double lng = response.results.get(0).geometry.location.lng;
			return new Coordinates(lat,lng);
		}
		return null;
	}
	
	/**
	 * Get current weather from the darksky api given a latitude and a longitude
	 * @param latitude
	 * @param longitude
	 * @return Weather object
	 */
	private Weather getCurrentWeather(double latitude, double longitude){
			
		String fullUrl = Constants.DARK_SKY_BASE_URL +Constants.DARK_SKY_API_KEY + "/{latitude}," + "{longitude}";
		System.out.println("full url : "+fullUrl);
		Weather currentWeather = this.restTemplate.getForObject(fullUrl, Weather.class, latitude,longitude);
		System.out.println("Weather : "+currentWeather);
		return currentWeather;
	}
	
}
