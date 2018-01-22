/**
 * 
 */
package com.francis.chatbot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author francisphiri
 *	Messages class
 */
public class Messages {
	
	private List<Message> messages;
	
	public Messages(){
		this.messages = new ArrayList<Message>();
	}
	
	public void addMessage(Message message){
		this.messages.add(message);
	}
	
	public List<Message> getMessages(){
		return this.messages;
	}
	
	public void setMessages(List<Message> messages){
		this.messages = messages;
	}
	
}
