/**
 * 
 */
package com.francis.chatbot.resource;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.francis.chatbot.model.Message;
import com.francis.chatbot.model.Messages;
import com.francis.chatbot.model.TextMessage;

/**
 * @author francisphiri
 *
 */

@RestController
@RequestMapping("/chat")
public class ChatResource {
	
	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public Messages chatMessageHandler(){
		Messages messagesList = new Messages();
		messagesList.addMessage(new TextMessage("Hello User!"));
		return messagesList;
	}
}
