package com.publisher.kafkaproject;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publisher.kafkaproject.config.KafkaConfigProps;
import com.publisher.kafkaproject.domain.customerVisitEvent;



@SpringBootApplication
public class KafkaProjectApplication {

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProjectApplication.class, args);
	}
                                                   //need to pass genrics here <kafka uses key ,payload in> and where need to call here kafka tamplate
	@Bean											   
	public ApplicationRunner runner(final KafkaTemplate<String , String> kafkaTemplate,final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException{
		final customerVisitEvent event= customerVisitEvent.builder()
		 .customerId(UUID.randomUUID().toString())
		 .dateTime(LocalDateTime.now())
		 .build();

		 final String payload = objectMapper.writeValueAsString(event);
		 return args ->{
			kafkaTemplate.send(kafkaConfigProps.getTopic(),payload);
		 };
  
	

	}
	@KafkaListener(topics="customer.visit")
	@Profile("!test")
	public String listens(final String in){
	   System.out.println(in);
	   return in ;

	}
}
