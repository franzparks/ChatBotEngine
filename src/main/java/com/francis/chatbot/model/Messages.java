/**
 * 
 */
package com.francis.chatbot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author francisphiri
 *
 */
public class Messages {
	
	private List<Message> messages;
	
	public Messages(){
		this.messages = new ArrayList<Message>();
	}
	
	public void addMessage(Message message){
		this.messages.add(message);
	}
	
}
