/**
 * 
 */
package com.francis.chatbot.service.impl;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.francis.chatbot.model.Message;
import com.francis.chatbot.service.MessageService;

/**
 * @author francisphiri
 *
 */
@Service
public class MessageServiceImpl implements MessageService{
	
	private final RestTemplate restTemplate;
	
	public MessageServiceImpl(RestTemplateBuilder restTemplateBuilder){
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
    public Message processMessage(String message) {
	    
	    return null;
    }
	
	private String callGoogleMapsApi(String address){
		return "";
	}
	
}
