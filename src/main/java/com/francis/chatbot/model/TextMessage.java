package com.francis.chatbot.model;

/**
 * @author francisphiri
 *	Text message class
 */
public class TextMessage implements Message {
	
	private String type;
	private String text;
	
	public TextMessage(String text) {
	    super();
	    this.type = "text";
	    this.text = text;
    }

	public String getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
}
