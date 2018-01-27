/**
 * 
 */
package com.francis.chatbot;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.francis.chatbot.model.Message;
import com.francis.chatbot.model.TextMessage;
import com.francis.chatbot.resource.ChatResource;
import com.francis.chatbot.service.MessageService;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
/**
 * @author francisphiri
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ChatResource.class)
public class ChatResourceTest {
	
	@Autowired
    private MockMvc mvc;
	 
    @MockBean
    private MessageService service;
    
    @Test
    public void givenUsername_whenPostToChat_thenReturnJsonMessageGreeting()
      throws Exception {
         
        String userName = "alex";
     
        Message message = new TextMessage(userName);
        given(service.processMessage(userName)).willReturn(message);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/chat/messages")
				.contentType("multipart/form-data")
				.param("user_id", "1234567")
				.param("action", "join")
				.param("name", userName);
        
        mvc.perform(requestBuilder)
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.messages", hasSize(1)))
          .andExpect(jsonPath("$.messages[0].text", is("Hello, "+userName + "!")));
        reset(service);
    }
    
    @Test
    public void givenMessage_whenPostToChatWithMissingWeatherKeyWord_thenReturnJsonControlMessage()
      throws Exception {
         
        String location = "Sao Paulo";
        String controlMessage = "I don't know many things but you can ask me about the weather."
    			+ "\n"
    			+ " I can answer questions such as:" + "\n"
    			+ " what's the weather in <Location>,"+"\n"+ "weather in <Location>,"+"\n"+ "<Location> weather"+"\n"
    			+ " Note: <Location> can be any city or reference to a city such as SF or San Francisco, or 94100";
    	
        
        Message message = new TextMessage(controlMessage);
        given(service.processMessage(location)).willReturn(message);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/chat/messages")
				.contentType("multipart/form-data")
				.param("user_id", "1234567")
				.param("action", "message")
				.param("text", location);
        
        mvc.perform(requestBuilder)
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.messages", hasSize(1)))
          .andExpect(jsonPath("$.messages[0].text", is(controlMessage)));
        reset(service);
    }
	
}
