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
import com.francis.chatbot.service.model.Coords;
import com.francis.chatbot.service.model.GeoResponse;
import com.francis.chatbot.service.model.GeoResponseParser;

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
	    Coords coords =  getAddressCoordinates(message);
	    if(coords == null){
	    	processedMessage = new TextMessage(errorMessage);
	    }else{
	    	processedMessage = new TextMessage("coords : "+coords.getLatitude() + " ," + coords.getLongitude());
	    }
	    return processedMessage;
    }
	
	private Coords getAddressCoordinates(String address){
		
		String fullUrl = Constants.GMAPS_BASE_URL + "{address}" + "&key="+ Constants.GMAPS_API_KEY;
		
		String results = this.restTemplate.getForObject(fullUrl, String.class, address);
		
		return createCoordinates(results);
	}
	
	private Coords createCoordinates(String json){
		
		GeoResponse response = new GeoResponseParser().parse(json);
		
		if(response.status.equals("OK")){
			double lat = response.results.get(0).geometry.location.lat;
			double lng = response.results.get(0).geometry.location.lng;
			return new Coords(lat,lng);
		}
		return null;
	}
	
}
