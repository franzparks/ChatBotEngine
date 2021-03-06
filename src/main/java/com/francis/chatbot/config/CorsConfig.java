/**
 * 
 */
package com.francis.chatbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.francis.chatbot.constants.Constants;

/**
 * @author francisphiri
 *	Config class for configuring cors
 */

@Configuration
public class CorsConfig {
	
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurerAdapter(){
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**")
				.allowedOrigins(Constants.CLIENT_URL)
				.allowedMethods("POST", "GET", "OPTIONS");
			}
		};
	}
	
}
