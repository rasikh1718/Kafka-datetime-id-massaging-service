package com.publisher.kafkaproject.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//to convert string to json using jackson dependencies
@Configuration
public class JsonConfig {
    
    @Bean
    public ObjectMapper objectMapper(){
        final ObjectMapper objectMapper =new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //register object mapper to java time module so that convert java date time to string 
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

        return objectMapper;

    }
       
}
