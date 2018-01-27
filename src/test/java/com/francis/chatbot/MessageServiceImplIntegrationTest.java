/**
 * 
 */
package com.francis.chatbot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringRunner;


import com.francis.chatbot.model.TextMessage;
import com.francis.chatbot.service.MessageService;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @author francisphiri
 *
 */
@RunWith(SpringRunner.class)
public class MessageServiceImplIntegrationTest {

	@MockBean
    private MessageService messageService;
    
    @Before
    public void setUp() {
        
    	String message = "What's the weather in San Francisco, CA?";
    	
        Mockito.when(messageService.processMessage(message))
            .thenReturn(new TextMessage("Currently it's 48F. Cloudy"));
        
        Mockito.when(messageService.processMessage("No Place"))
        .thenReturn(new TextMessage("Place cannot be found"));
       
    }
    
    @Test
    public void whenValidQuery_thenForcastShouldBeGiven() {
        String weather = "What's the weather in San Francisco, CA?";
       
        TextMessage message = (TextMessage) messageService.processMessage(weather);
        
        assertThat(message.getText()).isEqualTo("Currently it's 48F. Cloudy");
        
    }
    
    @Test
    public void whenInValidQuery_thenForcastShouldNotBeGiven() {
    	
        String weather = "No Place";
       
        TextMessage message = (TextMessage) messageService.processMessage(weather);
        
        assertThat(message.getText()).isEqualTo("Place cannot be found");
        
    }
	
}
