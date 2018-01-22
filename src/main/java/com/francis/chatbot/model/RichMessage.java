/**
 * 
 */
package com.francis.chatbot.model;

/**
 * @author francisphiri
 *	Rich message class
 */
public class RichMessage implements Message {
	
	private String type;
	private String html;
	
	public RichMessage(String type, String html) {
	    super();
	    this.type = "rich";
	    this.html = html;
    }

	public String getType() {
		return type;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	
	
	
}
