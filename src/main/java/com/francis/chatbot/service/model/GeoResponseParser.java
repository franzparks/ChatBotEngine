/**
 * 
 */
package com.francis.chatbot.service.model;

import com.google.gson.Gson;

/**
 * @author francisphiri
 *
 */
public class GeoResponseParser {
	Gson gson = new Gson();
	
	GeoResponse parse(String responseString){
		return gson.fromJson(responseString, GeoResponse.class);
	}
}
