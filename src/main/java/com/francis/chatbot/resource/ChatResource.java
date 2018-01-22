/**
 * 
 */
package com.francis.chatbot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.francis.chatbot.model.Message;
import com.francis.chatbot.model.Messages;
import com.francis.chatbot.model.TextMessage;
import com.francis.chatbot.service.MessageService;

/**
 * @author francisphiri
 *	Controller for handling requests to the /chat path
 */

@RestController
@RequestMapping("/chat")
public class ChatResource {
	
	private static final String welcomeMessageTemplate = "Hello, %s!";
	
	private String currentUsername;
	private String currentUserId;
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/messages", method = RequestMethod.POST,
			consumes = "multipart/form-data")
	public Messages chatMessageHandler(
			@RequestParam(name = "user_id") String userId,
			@RequestParam(name = "action") String action,
			@RequestParam(name = "name", required = false) String userName,
			@RequestParam(name = "text", required = false) String userMessage
		){
		
		Messages messagesList = new Messages();
		
		if(action.equals("join")){
			//welcome user
			this.currentUserId = userId;
			this.currentUsername = userName;
			String welcomeMessage = String.format(welcomeMessageTemplate, this.currentUsername); 
			messagesList.addMessage(new TextMessage(welcomeMessage));
		}else if(userId.equals(this.currentUserId) && action.equals("message")){
			//process message
			Message processedMessage = messageService.processMessage(userMessage);
			messagesList.addMessage(processedMessage);
		}
		
		return messagesList;
	}
}
