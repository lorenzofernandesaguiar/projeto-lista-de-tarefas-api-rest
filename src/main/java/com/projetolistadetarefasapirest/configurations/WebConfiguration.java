package com.projetolistadetarefasapirest.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/tarefas").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST");
				registry.addMapping("/tarefas/**").allowedOrigins("http://localhost:4200").allowedMethods("DELETE", "GET", "PUT");
			}
			
		};
		
	}
	
}