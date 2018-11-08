package com.example.demo.rabbitMQ.comsumer;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@RabbitListener(queues = "simple_queue_name")
@Component
public class SimpleRabbitMQReceiver {
	
	@RabbitHandler
	public void process(String message) {
		System.out.println("[SimpleRabbitMQReceiver] receive message:" + message + " at " + new Date());
	}
}
