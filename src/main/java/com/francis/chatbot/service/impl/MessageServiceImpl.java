/**
 * 
 */
package com.francis.chatbot.service.impl;

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
	
	public MessageServiceImpl(RestTemplateBuilder restTemplateBuilder){
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
    public Message processMessage(String message) {
	    TextMessage processedMessage = null;
	    Coordinates coords =  getAddressCoordinates(message);
	   
	    if(coords == null){
	    	processedMessage = new TextMessage(errorMessage);
	    }else{
	    	//processedMessage = new TextMessage("coords : "+coords.getLatitude() + " ," + coords.getLongitude());
	    	
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
	
	private Coordinates getAddressCoordinates(String address){
		
		String fullUrl = Constants.GMAPS_BASE_URL + "{address}" + "&key="+ Constants.GMAPS_API_KEY;
		
		String results = this.restTemplate.getForObject(fullUrl, String.class, address);
		
		return createCoordinates(results);
	}
	
	private Coordinates createCoordinates(String json){
		
		GeoResponse response = new GeoResponseParser().parse(json);
		
		if(response.status.equals("OK")){
			double lat = response.results.get(0).geometry.location.lat;
			double lng = response.results.get(0).geometry.location.lng;
			return new Coordinates(lat,lng);
		}
		return null;
	}
	
	private Weather getCurrentWeather(double latitude, double longitude){
			
		String fullUrl = Constants.DARK_SKY_BASE_URL +Constants.DARK_SKY_API_KEY + "/{latitude}," + "{longitude}";
		System.out.println("full url : "+fullUrl);
		Weather currentWeather = this.restTemplate.getForObject(fullUrl, Weather.class, latitude,longitude);
		System.out.println("Weather : "+currentWeather);
		return currentWeather;
	}
	
}
