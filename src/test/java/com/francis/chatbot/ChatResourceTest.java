/**
 * 
 */
package com.francis.chatbot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import com.francis.chatbot.model.Message;
import com.francis.chatbot.model.TextMessage;
import com.francis.chatbot.resource.ChatResource;
import com.francis.chatbot.service.MessageService;

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
    public void givenUsername_whenPostToChat_thenReturnJsonMessage()
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
    }
	
}
