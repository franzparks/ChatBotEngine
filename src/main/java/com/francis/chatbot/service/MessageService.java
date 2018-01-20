/**
 * 
 */
package com.francis.chatbot.service;

import com.francis.chatbot.model.Message;

/**
 * @author francisphiri
 *
 */
public interface MessageService {
	
	public Message processMessage(String message);
	
}
