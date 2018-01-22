/**
 * 
 */
package com.francis.chatbot.service.model.gmap;

import com.google.gson.Gson;

/**
 * @author francisphiri
 *
 */
public class GeoResponseParser {
	
	Gson gson = new Gson();
	
	public GeoResponse parse(String responseString){
		return gson.fromJson(responseString, GeoResponse.class);
	}
}
