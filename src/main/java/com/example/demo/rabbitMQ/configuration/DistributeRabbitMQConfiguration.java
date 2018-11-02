package com.example.demo.rabbitMQ.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DistributeRabbitMQConfiguration {
	
	private static final String DISTRIBUTE_QUEUE_NAME = "distribute_queue_name";
	
	@Bean
	public Queue queue() {
		return new Queue(DISTRIBUTE_QUEUE_NAME, false, false, false, null);
	}
}
