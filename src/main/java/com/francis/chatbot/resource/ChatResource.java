/**
 * 
 */
package com.francis.chatbot.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author francisphiri
 *
 */

@RestController
@RequestMapping("/chat")
public class ChatResource {
	
	@RequestMapping(value= "/messages", method = RequestMethod.POST)
	public String chatMessageHandler(){
		
		return "";
	}
}
